/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendasGUI;

import Administrator.MenuAdmin;
import InternalGUI.workerConfirm;
import com.mysql.jdbc.PreparedStatement;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author focuswts
 */
public class sellGUI extends javax.swing.JInternalFrame {

    public static int getIdFuncionario() {
        return idFuncionario;
    }

    public static void setIdFuncionario(int aIdFuncionario) {
        idFuncionario = aIdFuncionario;
    }

    public static String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public static void setNomeFuncionario(String aNomeFuncionario) {
        nomeFuncionario = aNomeFuncionario;
    }

    /**
     * Creates new form sellGUI
     */
    private Connection con = lib.connection.connect();
    private int NumeroPedido;
    private ArrayList<cartItems> item;
    private String TotalValue;
    private Date data = MenuAdmin.getDate();
    private static int idFuncionario;
    private static String nomeFuncionario;
//////////Variáveis Acesso Para JTextFields//////////
/////////////////////////////////////////////////////    

    public sellGUI() {
        initComponents();
        txt_Data.setText(formatarData());
        block();
    }

    private String formatarData() {
//Formatar Data//
        String dataFormatada = "";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try {
            dataFormatada = df.format(data);
        } catch (Exception e) {
            System.out.println(e);
        }
/////////////////
        return dataFormatada;
    }

    public static void exportarComponentes(sellItemsClass modelVendas) {
        txt_codProduto.setText(modelVendas.getCodProduto());
        txt_descProduto.setText(modelVendas.getDesc_Produto());
        txt_qtdProduto.setText(modelVendas.getQtd_Produto());
        txt_valUnitario.setText(modelVendas.getVal_Unitario());
        txt_qtdProduto.setEditable(modelVendas.isEnabled());
    }

    private void block() {
        txt_codProduto.setEnabled(false);
        txt_codigoVenda.setEnabled(false);
        txt_descProduto.setEnabled(false);
        txt_qtdProduto.setEnabled(false);
        txt_valUnitario.setEnabled(false);
        txt_Data.setEnabled(false);
        Ftxt_valTotalCompra.setEnabled(false);
        btn_AddCart.setEnabled(false);
        btn_CancelarVenda.setEnabled(false);
        btn_FinalizarVenda.setEnabled(false);
        btn_buscarProduto.setEnabled(false);

    }

    private void unblock() {
        txt_codProduto.setEnabled(true);
        txt_codigoVenda.setEnabled(false);
        txt_descProduto.setEnabled(false);
        txt_qtdProduto.setEnabled(true);
        txt_qtdProduto.setEditable(false);
        txt_valUnitario.setEnabled(false);
        txt_Data.setEnabled(false);
        Ftxt_valTotalCompra.setEnabled(false);
        btn_AddCart.setEnabled(true);
        btn_CancelarVenda.setEnabled(true);
        btn_FinalizarVenda.setEnabled(true);
        btn_buscarProduto.setEnabled(true);
    }

    public void AddToCart() {
        ////Definicao Variáveis////
        String Codigo = txt_codProduto.getText();
        String Descricao = txt_descProduto.getText();
        String Quantidade = txt_qtdProduto.getText();
        String ValorUnitario = txt_valUnitario.getText();
        BigDecimal valorUnitario = new BigDecimal(ValorUnitario);
        BigDecimal QTD = new BigDecimal(Quantidade);
        BigDecimal ValorTOTAL = valorUnitario.multiply(QTD);
        String ValorTotal = String.valueOf(ValorTOTAL);
        ///////////////////////////
        if (Codigo.trim().equals("") || Quantidade.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Selecione Um Produto E Informe Sua Quantidade!!");
        }
//Verificar Se O Codigo Do Produto Equivale Ao Mesmo Do Banco//       
        try {
            String sql = "SELECT * FROM produtos where idprod = ?";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            st.setInt(1, Integer.valueOf(Codigo));
            ResultSet rs = st.executeQuery();
            if (!rs.first()) {
                JOptionPane.showMessageDialog(this, "Código Do Produto Inexistente,Insira Corretamente!!");
            } else {
                //Definir TableModel E Adicionar Row Com Os Valores//
                DefaultTableModel modelCart = (DefaultTableModel) this.tb_Carrinho.getModel();
                Object produto[] = new Object[3];
                //  produto[4] = Codigo;
                produto[0] = Descricao;
                produto[1] = Quantidade;
                produto[2] = ValorTOTAL;
                //JOptionPane.showMessageDialog(this, rs.first());

                if (txt_codProduto.getText().equals(rs.getString("idprod")) && checkFields() == true) {
                    modelCart.addRow(produto);
                } else {
                    JOptionPane.showMessageDialog(this, "Código Inválido Ou Campos Faltando,Insira O Produto Novamente!!");
                }
                /////////////////////////////////////////////////////
            }
            st.close();
        } catch (SQLException e) {
        }
/////////////////////////////////////////////////////////////
        Limpar();
    }

    private void confirmFuncionario() {
        workerConfirm viewWorker = new workerConfirm();
        MenuAdmin.dp_DesktopPane.add(viewWorker);
        viewWorker.setVisible(true);
    }

    private void Limpar() {
        txt_codProduto.setText("");
        txt_descProduto.setText("");
        txt_qtdProduto.setText("");
        txt_valUnitario.setText("");
    }

    public void RemoveFromCart() {
        //Definir TableModel E Adicionar Row Com Os Valores//
        DefaultTableModel modelCart = (DefaultTableModel) this.tb_Carrinho.getModel();
        modelCart.removeRow(tb_Carrinho.getSelectedRow());

    }

    public void FinalizarCompra() {

        DefaultTableModel modelCart = (DefaultTableModel) this.tb_Carrinho.getModel();
        int numCol = modelCart.getColumnCount();
        int numRow = modelCart.getRowCount();
        item = new ArrayList<cartItems>();
        for (int i = 0; i < numRow; i++) {
            String idProduto = "";
            String nome_Prod = modelCart.getValueAt(i, 0).toString();
            String qtd_Prod = modelCart.getValueAt(i, 1).toString();
            String val_UnitTotal = modelCart.getValueAt(i, 2).toString();
            //Pegar ID Produto//
            try {
                String sql = "SELECT idprod from produtos where desprod = ?";
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                st.setString(1, nome_Prod);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    idProduto = rs.getString("idprod");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Falha Pegar Id Produto");
            }
            item.add(new cartItems(idProduto, nome_Prod, qtd_Prod, val_UnitTotal));
        }
        //DESKTOP PANE INSTANCIATION//
        PaymentGUI viewPayment = new PaymentGUI();
        MenuAdmin.dp_DesktopPane.add(viewPayment);
        viewPayment.setCodigoVenda(NumeroPedido);
        viewPayment.setDate(data);
        viewPayment.setItem(item);
        viewPayment.setValorTotal(TotalValue);
        viewPayment.setVisible(true);
        //////////////////////////////
        this.dispose();
    }

    public boolean checkFields() {
        boolean check = true;
        String codigo = txt_codProduto.getText();
        String descricao = txt_descProduto.getText();
        String quantidade = txt_qtdProduto.getText();
        String valUnitario = txt_valUnitario.getText();
        if (codigo.equals("") || descricao.equals("") || quantidade.equals("") || valUnitario.equals("")) {
            check = false;
        }
        return check;
    }

    public void SomaProdutos() {
        BigDecimal ValorTotal = new BigDecimal(0);
        try {
            for (int i = 0; i < tb_Carrinho.getRowCount(); i++) {
                ValorTotal = ValorTotal.add(BigDecimal.valueOf(Double.valueOf(tb_Carrinho.getValueAt(i, 2).toString())));
            }
            Ftxt_valTotalCompra.setText(ValorTotal.toString());
            TotalValue = ValorTotal.toString();
        } catch (Exception e) {
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_AbrirVenda = new javax.swing.JButton();
        btn_FinalizarVenda = new javax.swing.JButton();
        btn_CancelarVenda = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_codigoVenda = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_descProduto = new javax.swing.JTextField();
        btn_buscarProduto = new javax.swing.JButton();
        txt_qtdProduto = new javax.swing.JTextField();
        txt_valUnitario = new javax.swing.JTextField();
        btn_AddCart = new javax.swing.JButton();
        txt_Data = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_codProduto = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Carrinho = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        Ftxt_valTotalCompra = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_AbrirVenda.setText("Abrir Venda");
        btn_AbrirVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AbrirVendaActionPerformed(evt);
            }
        });

        btn_FinalizarVenda.setText("Finalizar Venda");
        btn_FinalizarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FinalizarVendaActionPerformed(evt);
            }
        });

        btn_CancelarVenda.setText("Cancelar Venda");
        btn_CancelarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelarVendaActionPerformed(evt);
            }
        });

        jLabel1.setText("Código Venda:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_AbrirVenda)
                .addGap(18, 18, 18)
                .addComponent(btn_FinalizarVenda)
                .addGap(18, 18, 18)
                .addComponent(btn_CancelarVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_codigoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_AbrirVenda)
                    .addComponent(btn_FinalizarVenda)
                    .addComponent(btn_CancelarVenda))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_codigoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Código Produto");

        jLabel3.setText("Quantidade:");

        jLabel4.setText("Valor Unitário");

        jLabel5.setText("Descrição Produto");

        txt_descProduto.setEditable(false);

        btn_buscarProduto.setIcon(new javax.swing.ImageIcon("/home/focuswts/Icons For Development/Aesthetica Icons Set/png/24x24/search.png")); // NOI18N
        btn_buscarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarProdutoActionPerformed(evt);
            }
        });

        txt_qtdProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_qtdProdutoKeyTyped(evt);
            }
        });

        txt_valUnitario.setEditable(false);

        btn_AddCart.setIcon(new javax.swing.ImageIcon("/home/focuswts/Icons For Development/Aesthetica Icons Set/png/24x24/shopping_cart_up.png")); // NOI18N
        btn_AddCart.setText("Adicionar Ao Carrinho");
        btn_AddCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddCartActionPerformed(evt);
            }
        });

        txt_Data.setEditable(false);
        txt_Data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_DataActionPerformed(evt);
            }
        });

        jLabel8.setText("Data:");

        txt_codProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_codProdutoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_codProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_buscarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txt_descProduto)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txt_qtdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_valUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txt_Data, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_AddCart)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_buscarProduto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_descProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel8))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_qtdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_valUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_AddCart)
                    .addComponent(txt_Data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_AddCart, btn_buscarProduto, txt_Data, txt_codProduto, txt_descProduto, txt_qtdProduto, txt_valUnitario});

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Carrinho");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tb_Carrinho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Quantidade", "Valor Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Carrinho.getTableHeader().setReorderingAllowed(false);
        tb_Carrinho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_CarrinhoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Carrinho);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel7.setText("Valor Total Da Compra");

        Ftxt_valTotalCompra.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel7)
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Ftxt_valTotalCompra)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(Ftxt_valTotalCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setIcon(new javax.swing.ImageIcon("/home/focuswts/Icons For Development/Aesthetica Icons Set/png/24x24/shopping_cart_remove.png")); // NOI18N
        jButton1.setText("Remover Produto Selecionado");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(26, 26, 26))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AbrirVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AbrirVendaActionPerformed
        try {
            String sql = "SELECT MAX(id) from pedidos";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NumeroPedido = rs.getInt(1) + 1;
            }
            txt_codigoVenda.setText(String.valueOf(NumeroPedido));
            st.close();
        } catch (SQLException e) {
        }
        confirmFuncionario();
        unblock();
    }//GEN-LAST:event_btn_AbrirVendaActionPerformed

    private void btn_CancelarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarVendaActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_CancelarVendaActionPerformed

    private void btn_buscarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarProdutoActionPerformed
        searchProductGUI viewBusca = new searchProductGUI();
        JDesktopPane desktopPane = this.getDesktopPane();
        desktopPane.add(viewBusca);
        viewBusca.setVisible(true);
    }//GEN-LAST:event_btn_buscarProdutoActionPerformed

    private void btn_AddCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddCartActionPerformed
        AddToCart();
        SomaProdutos();
// Ftxt_valTotalCompra.setText(String.valueOf(SomaProdutos()));
    }//GEN-LAST:event_btn_AddCartActionPerformed

    private void tb_CarrinhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_CarrinhoMouseClicked
    }//GEN-LAST:event_tb_CarrinhoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            RemoveFromCart();
            SomaProdutos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Selecione O Produto A Ser Excluido");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_FinalizarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FinalizarVendaActionPerformed
        FinalizarCompra();
    }//GEN-LAST:event_btn_FinalizarVendaActionPerformed

    private void txt_codProdutoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codProdutoKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE) {
            evt.consume();
        }

    }//GEN-LAST:event_txt_codProdutoKeyTyped

    private void txt_qtdProdutoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qtdProdutoKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE) {
            evt.consume();
        }

    }//GEN-LAST:event_txt_qtdProdutoKeyTyped

    private void txt_DataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_DataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_DataActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField Ftxt_valTotalCompra;
    private javax.swing.JButton btn_AbrirVenda;
    private javax.swing.JButton btn_AddCart;
    private javax.swing.JButton btn_CancelarVenda;
    private javax.swing.JButton btn_FinalizarVenda;
    private javax.swing.JButton btn_buscarProduto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_Carrinho;
    private javax.swing.JTextField txt_Data;
    public static javax.swing.JTextField txt_codProduto;
    private javax.swing.JTextField txt_codigoVenda;
    private static javax.swing.JTextField txt_descProduto;
    private static javax.swing.JTextField txt_qtdProduto;
    private static javax.swing.JTextField txt_valUnitario;
    // End of variables declaration//GEN-END:variables

}
