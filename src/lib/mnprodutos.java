package lib;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
@SuppressWarnings("serial")

public class mnprodutos extends JFrame
{
  private JLabel lidprod, ldesprod, lquantidade, lvalunit, lcategoria, lultmov;
  private JTextField tidprod, tdesprod, tquantidade, tvalunit;
  private JDateChooser tultmov;
  private JComboBox tlistacateg;
  private JButton bincluir, balterar, bgravar, bexcluir;
  private JScrollPane js;
  private Connection conex;  
  private DefaultTableModel aModel;
  private JTable aTable;
  private int g_idcateg;
  private String operacao;
  
  
  String normal = "dd/MM/yyyy";
  SimpleDateFormat nformat = new SimpleDateFormat(normal);
  java.util.Date date = new java.util.Date();
  String datahoje = nformat.format(date);
  String anocorrente = datahoje.substring(6,10);
  int mescorrente = Integer.parseInt(datahoje.substring(3,5));
  String diacorrente = datahoje.substring(0,2);
  String mes_extenso[] = {"","janeiro","fevereiro","março","abril","maio","junho","julho","agosto","setembro","outubro","novembro","dezembro"};
  String dataemissao = "Londrina, "+diacorrente+" de "+mes_extenso[mescorrente]+" de "+anocorrente+".";

     
  public mnprodutos()
  {
     super("Manutenção de Produtos");
     Container ct = getContentPane();
     ct.setLayout(null);
  
     try {Class.forName("com.mysql.jdbc.Driver");} catch (ClassNotFoundException e){System.out.println("Driver nao encontrado!");}
     try
     {
         String host    = "localhost";
         String dbmysql = "zanete";
         String usuario = "root";
         String senha   = "1337";
         conex = DriverManager.getConnection("jdbc:mysql://"+host+"/"+dbmysql,usuario,senha);
     } catch (Exception sqlEx) {System.out.println("Conexao falhou, verifique os parametros!");}

     // Instancia o objeto JTable
     aTable = new JTable();
     String[] tableColumnsName = {"Código","Descrição","Quantidade","Valor","Ult.Mov.","Categoria","idcateg"};
     aModel = (DefaultTableModel) aTable.getModel();
     aModel.setColumnIdentifiers(tableColumnsName);

     // Oculta a coluna idcateg
     aTable.getColumnModel().getColumn(6).setMaxWidth(0);  
     aTable.getColumnModel().getColumn(6).setMinWidth(0);  
     aTable.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);  
     aTable.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);   

     js = new JScrollPane(aTable);
     js.setBounds(10,350,720,200);
     preenche_grid();
     ct.add(js);

     lidprod = new JLabel("ID Produto");
     ldesprod = new JLabel("Descrição");
     lquantidade = new JLabel("Quantidade");
     lvalunit = new JLabel("Valor unitário");
     lcategoria = new JLabel("Categoria");
     lultmov = new JLabel("Data ult. mov.");
     
     lidprod.setBounds(10,10,150,30);
     ldesprod.setBounds(10,50,150,30);
     lquantidade.setBounds(10,100,150,30);
     lvalunit.setBounds(10,150,150,30);
     lcategoria.setBounds(10,200,150,30);
     lultmov.setBounds(10,250,150,30);
   
     ct.add(lidprod);
     ct.add(ldesprod);
     ct.add(lquantidade);
     ct.add(lvalunit);
     ct.add(lcategoria);
     ct.add(lultmov);
     
     tidprod = new JTextField();
     tdesprod = new JTextField();
     tquantidade = new JTextField();
     tvalunit = new JTextField();
     tlistacateg = new JComboBox();
     tultmov = new JDateChooser("dd/MM/yyyy", "##/##/####",'_');
    
     tidprod.setBounds(130,10,50,30);
     tdesprod.setBounds(130,50,250,30);
     tquantidade.setBounds(130,100,50,30);
     tvalunit.setBounds(130,150,50,30);
     tlistacateg.setBounds(130,200,300,30);
     tultmov.setBounds(130,250,150,30);
 
     ct.add(tidprod);
     ct.add(tdesprod);
     ct.add(tquantidade);
     ct.add(tvalunit);
     ct.add(tlistacateg);
     ct.add(tultmov);
          
     EventoCombo evc = new EventoCombo();
     EventoB eb = new EventoB();
     tlistacateg.addItemListener(evc);
     preenche_tlistacateg();
    
     bexcluir = new JButton("Excluir");
     balterar = new JButton("Editar");
     bgravar = new JButton("Gravar");
     bincluir = new JButton("Novo");
     
     bexcluir.setBounds(50,300,100,40);
     balterar.setBounds(150,300,100,40);
     bgravar.setBounds(250,300,100,40);
     bincluir.setBounds(350,300,100,40);
     
     bexcluir.addActionListener(eb);
     balterar.addActionListener(eb);
     bgravar.addActionListener(eb);
     bincluir.addActionListener(eb);
   
     ct.add(bexcluir);
     ct.add(balterar);
     ct.add(bgravar);
     ct.add(bincluir);
     
     limpaBase();
     bloqueia();
     
     setSize(750,600);
     setVisible(true);
     setLocationRelativeTo(null);
  }

  
  private class EventoB implements ActionListener
  {
     public void actionPerformed(ActionEvent event)
     {
        // Executa este if se for clicado o botao Alterar
        if (event.getSource()==balterar)
        {
           operacao="edicao";	
           int linha = aTable.getSelectedRow();
           if (linha>=0)
           {
        	  desbloqueia();
              String idprod = aTable.getValueAt(linha,0).toString();
              String desprod = aTable.getValueAt(linha,1).toString();
              String quantidade = aTable.getValueAt(linha,2).toString();
              String valunit = aTable.getValueAt(linha,3).toString();
              String ultmov = aTable.getValueAt(linha,4).toString();
              String categoria = aTable.getValueAt(linha,5).toString();
              String idcateg = aTable.getValueAt(linha,6).toString();
              tidprod.setText(idprod);
              tidprod.setEnabled(false);
              tdesprod.setText(desprod);
              tquantidade.setText(quantidade);
              tvalunit.setText(valunit);
              tultmov.setDate(dma_to_amd(ultmov));
              tlistacateg.setSelectedItem(categoria);
              g_idcateg = Integer.parseInt(idcateg);
           }
           else
           {
         	  JOptionPane.showMessageDialog(null,"Selecione na tabela o registro que deseja editar");
           }
        }
        
           	 
        // Executa este if se for clicado o botao Gravar
        if (event.getSource()==bgravar)
        {
           try
           {
        	     int idprod = Integer.parseInt(tidprod.getText());
        	     
        	     if (idprod>0 && "edicao".equals(operacao))
        	     { 	 
        	        PreparedStatement st = conex.prepareStatement("update produtos set desprod=?,quantidade=?,valunit=?,codcateg=?, ultmov=? where idprod=?");
                    st.setString(1,tdesprod.getText());
                    st.setDouble(2,Double.parseDouble(tquantidade.getText()));
                    st.setDouble(3,Double.parseDouble(tvalunit.getText()));
                    st.setInt(4,g_idcateg);
                    st.setDate(5,new java.sql.Date(tultmov.getDate().getTime())); 
                    st.setInt(6,Integer.parseInt(tidprod.getText()));
                    st.executeUpdate();
        	     }
 
           	     if (idprod > 0 && "novo".equals(operacao))
        	     { 	 
                     PreparedStatement st = conex.prepareStatement("select idprod from produtos where idprod = ?");
                     st.setInt(1,Integer.parseInt(tidprod.getText()));
                     ResultSet rs = st.executeQuery();
                     if (rs.first())
                     {
                         JOptionPane.showMessageDialog(null,"Este codigo ja existe!");
                     }
                     else
                     {
                        String sql = "insert into produtos (idprod,desprod,quantidade,valunit,codcateg,ultmov) values (?,?,?,?,?,?)";
                        PreparedStatement vs = conex.prepareStatement(sql);
                        vs.setInt(1,Integer.parseInt(tidprod.getText()));
                        vs.setString(2,tdesprod.getText());
                        vs.setDouble(3,Double.parseDouble(tquantidade.getText()));
                        vs.setDouble(4,Double.parseDouble(tvalunit.getText()));
                        vs.setInt(5,g_idcateg);
                        vs.setDate(6,new java.sql.Date(tultmov.getDate().getTime()));
                        vs.executeUpdate();
                     }
                     rs.close();
         	     }
           	     
           } catch (Exception sqlEx) {JOptionPane.showMessageDialog(null,"erro");}
           preenche_grid();
           limpaBase();
           bloqueia();
        }
        
        // Executa este if se for clicado o botao Excluir
        if (event.getSource()==bexcluir)
        {
           int linha = aTable.getSelectedRow();
           if (linha>=0)
           {
        	  int resp = JOptionPane.showOptionDialog(null,"Confirma exclusão","Atenção",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
        	  if (resp==0)
        	  {
                 String idprod = aTable.getValueAt(linha,0).toString();
                 try
                 {
                    PreparedStatement st = conex.prepareStatement("delete from produtos where idprod = ?");
                    st.setInt(1,Integer.parseInt(idprod));
                    st.executeUpdate();
                 } catch (Exception sqlEx) {}
                 preenche_grid();
         	  }
           }
           else
           {
         	  JOptionPane.showMessageDialog(null,"Selecione na tabela o registro que deseja excluir");
           }

        }
        
        // Executa este if se for clicado o botao Incluir
        if (event.getSource()==bincluir)
        {
        	operacao="novo";	
            try
            {
            	limpaBase();
            	desbloqueia();
            } catch (Exception sqlEx) {}
       }
        
        
        
     }
  }
  
   private class EventoCombo implements ItemListener
   {
      public void itemStateChanged(ItemEvent event)
      {
         try
         {
        	PreparedStatement st = conex.prepareStatement("select idcateg,descateg from categoria where descateg=?");
            st.setString(1,tlistacateg.getSelectedItem().toString());
            ResultSet rs = st.executeQuery();
            if (rs.first()) {g_idcateg = rs.getInt("idcateg");}
         } catch (Exception sqlEx) {}
      }
   }
  
 
   protected void limpaBase()
   {
      tidprod.setText("0");
      tidprod.setEnabled(false);
      tdesprod.setText("");
      tquantidade.setText("");
      tvalunit.setText("");
      tultmov.setDate(date);
      tlistacateg.setSelectedIndex(0);
      g_idcateg=0;

   }
  

  public void preenche_grid()
  {
     try
     {
    	 
         int numlinhas = aModel.getRowCount();
         for (int i=0;i<numlinhas;i++) {aModel.removeRow(0);}
        // if(numlinhas>0)
        // {
        //    for(int i=numlinhas-1;i>=0;i--) {aModel.removeRow(i);}
        // }
    	     	 
         PreparedStatement st = conex.prepareStatement("select produtos.idprod, produtos.desprod, produtos.quantidade,produtos.valunit, date_format(produtos.ultmov, '%d/%m/%Y'), categoria.descateg, categoria.idcateg from produtos, categoria where produtos.codcateg=categoria.idcateg order by produtos.desprod");
         ResultSet rs = st.executeQuery();
         ResultSetMetaData rsmd = rs.getMetaData();
         int numcol = rsmd.getColumnCount();
         while(rs.next())
         {
            Object[] objects = new Object[numcol];
            for(int i=0;i<numcol;i++)
            {
                objects[i]=rs.getObject(i+1);
            }
            aModel.addRow(objects);
         }
         
     } catch (Exception sqlEx) {}
  }
 

  public void bloqueia()
  {
     try
     {
        tidprod.setEnabled(false);
        tdesprod.setEnabled(false);
        tquantidade.setEnabled(false);
        tvalunit.setEnabled(false);
        tlistacateg.setEnabled(false);
        tultmov.setEnabled(false);
        bgravar.setEnabled(false);
     } catch (Exception sqlEx) {}
  }
  
  public void desbloqueia()
  {
     try
     {
        tidprod.setEnabled(true);
        tdesprod.setEnabled(true);
        tquantidade.setEnabled(true);
        tvalunit.setEnabled(true);
        tlistacateg.setEnabled(true);
        tultmov.setEnabled(true);
        bgravar.setEnabled(true);
 
     } catch (Exception sqlEx) {}
  }
  
  public void preenche_tlistacateg()
  {
     try
     {
    	tlistacateg.removeAllItems();
        PreparedStatement st = conex.prepareStatement("select idcateg,descateg from categoria order by descateg");
        ResultSet rs = st.executeQuery();
        tlistacateg.insertItemAt("-- Selecione --",0); 
        int x = 1;
        while (rs.next())
        {
           tlistacateg.insertItemAt(rs.getString("descateg"),x); 
           x++;
        }
        tlistacateg.setSelectedIndex(0);
     } catch (Exception sqlEx) {}
  }
 
  public static void main(String args[])
  {
     mnprodutos f = new mnprodutos();
     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  /*********************************** Fim do metodo principal *********************************/

  public String amd_to_dma(java.sql.Date d)
  {
       String resp = "";
       if (d!=null)
       {
          String sdat = d.toString();
          String sano = sdat.substring(0,4);
          String smes = sdat.substring(5,7);
          String sdia = sdat.substring(8,10);
          resp = sdia+"/"+smes+"/"+sano;
       }
       return resp;
  }

  public java.sql.Date dma_to_amd(String d)
  {
       java.sql.Date dataresp = null;
       int tam = d.length();
       if (d!=null && tam==10)
       {
          String sdia = d.substring(0,2);
          String smes = d.substring(3,5);
          String sano = d.substring(6,10);
          dataresp = java.sql.Date.valueOf(sano+"-"+smes+"-"+sdia);
       }
       return dataresp;
  }

} // fim do programa
