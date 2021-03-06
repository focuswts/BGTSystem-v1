/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendasGUI;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author focuswts
 */
public class searchClientGUI extends javax.swing.JInternalFrame {

    /**
     * Creates new form searchClientGUI
     */
    private Connection con = lib.connection.connect();

    public searchClientGUI() {
        initComponents();
    }

    public void preenche_grid() {
        DefaultTableModel aModel = (DefaultTableModel) this.tb_Clients.getModel();
        try {

            int numlinhas = aModel.getRowCount();
            for (int i = 0; i < numlinhas; i++) {
                aModel.removeRow(0);
            }
            String filtro = txt_SearchField.getText();

            //Verifica Qual Método Será Utilizado A Partir Dos Botoes//
            String sql = "";
            String stType = "";
            if (rb_Cpf.isSelected() == true) {
                sql = "SELECT clientes.idCliente,clientes.nomeCliente,clientes.cpfCliente,clientes.emailCliente,clientes.telCliente from clientes where cpfCliente LIKE ?";
                stType = "cpf";
            }
            if (rb_Nome.isSelected() == true) {

                sql = "SELECT clientes.idCliente,clientes.nomeCliente,clientes.cpfCliente,clientes.emailCliente,clientes.telCliente from clientes where nomeCliente LIKE ?";
                stType = "nome";
            }
            if (rb_Email.isSelected() == true) {

                sql = "SELECT clientes.idCliente,clientes.nomeCliente,clientes.cpfCliente,clientes.emailCliente,clientes.telCliente from clientes where emailCliente LIKE ?";
                stType = "email";
            }
///////////////////////////////////Fim Verificação Botoes//////////////////////
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ////////////////Definiçao Dos Statements/////////////
            if (stType.equals("cpf")) {
                String cpf = txt_SearchField.getText();
                st.setString(1, "%" + cpf + "%");
            }
            if (stType.equals("nome")) {
                String nome = txt_SearchField.getText();
                st.setString(1, "%" + nome + "%");
            }
            if (stType.equals("email")) {
                String email = txt_SearchField.getText();
                st.setString(1, "%" + email + "%");
            }
//////////////////////////////////////////////////////////////////
            ResultSet rs = st.executeQuery();
            ResultSetMetaData rsmt = rs.getMetaData();
            int numcol = rsmt.getColumnCount();
            while (rs.next()) {
                Object[] objects = new Object[numcol];
                for (int i = 0; i < numcol; i++) {
                    objects[i] = rs.getObject(i + 1);
                }
                aModel.addRow(objects);

            }
        } catch (SQLException e) {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        rb_Nome = new javax.swing.JRadioButton();
        rb_Cpf = new javax.swing.JRadioButton();
        rb_Email = new javax.swing.JRadioButton();
        txt_SearchField = new javax.swing.JTextField();
        btn_Buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Clients = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_NomeCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_EmailCliente = new javax.swing.JTextField();
        Ftxt_CpfCliente = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_idCliente = new javax.swing.JTextField();
        btn_Confirmar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Busca De Clientes");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel2.setText("Pesquisar Cliente Por:");

        buttonGroup1.add(rb_Nome);
        rb_Nome.setSelected(true);
        rb_Nome.setText("Nome");
        rb_Nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_NomeActionPerformed(evt);
            }
        });

        buttonGroup1.add(rb_Cpf);
        rb_Cpf.setText("CPF");

        buttonGroup1.add(rb_Email);
        rb_Email.setText("Email");

        txt_SearchField.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        btn_Buscar.setIcon(new javax.swing.ImageIcon("/home/focuswts/Icons For Development/Aesthetica Icons Set/png/24x24/search_add.png")); // NOI18N
        btn_Buscar.setText("Buscar");
        btn_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BuscarActionPerformed(evt);
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
                        .addComponent(txt_SearchField)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Buscar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(rb_Nome)
                        .addGap(18, 18, 18)
                        .addComponent(rb_Cpf)
                        .addGap(18, 18, 18)
                        .addComponent(rb_Email)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(rb_Nome)
                    .addComponent(rb_Cpf)
                    .addComponent(rb_Email))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Buscar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tb_Clients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "CPF", "Email", "Telefone"
            }
        ));
        tb_Clients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_ClientsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Clients);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Do Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel1.setText("Nome:");

        txt_NomeCliente.setEditable(false);
        txt_NomeCliente.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        txt_NomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NomeClienteActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel3.setText("CPF:");

        jLabel4.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel4.setText("Email:");

        txt_EmailCliente.setEditable(false);
        txt_EmailCliente.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        Ftxt_CpfCliente.setEditable(false);
        try {
            Ftxt_CpfCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        Ftxt_CpfCliente.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("ID:");

        txt_idCliente.setEditable(false);
        txt_idCliente.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_EmailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ftxt_CpfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_NomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Ftxt_CpfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_EmailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        btn_Confirmar.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        btn_Confirmar.setIcon(new javax.swing.ImageIcon("/home/focuswts/Icons For Development/Aesthetica Icons Set/png/24x24/accept.png")); // NOI18N
        btn_Confirmar.setText("Confirmar");
        btn_Confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(btn_Confirmar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Confirmar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rb_NomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_NomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_NomeActionPerformed

    private void btn_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BuscarActionPerformed
        preenche_grid();
    }//GEN-LAST:event_btn_BuscarActionPerformed

    private void tb_ClientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ClientsMouseClicked
        String xId = this.tb_Clients.getValueAt(tb_Clients.getSelectedRow(), 0).toString();
        String xNome = this.tb_Clients.getValueAt(tb_Clients.getSelectedRow(), 1).toString();
        String xCpf = this.tb_Clients.getValueAt(tb_Clients.getSelectedRow(), 2).toString();
        String xEmail = this.tb_Clients.getValueAt(tb_Clients.getSelectedRow(), 3).toString();

        txt_idCliente.setText(xId);
        txt_NomeCliente.setText(xNome);
        Ftxt_CpfCliente.setText(xCpf);
        txt_EmailCliente.setText(xEmail);
    }//GEN-LAST:event_tb_ClientsMouseClicked

    private void btn_ConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConfirmarActionPerformed
        String nome = this.txt_NomeCliente.getText();
        PaymentGUI.setTxt_NomeCliente(nome);
        int id_Cliente = Integer.valueOf(this.txt_idCliente.getText());
        PaymentGUI.setIdCliente(id_Cliente);
        this.dispose();
    }//GEN-LAST:event_btn_ConfirmarActionPerformed

    private void txt_NomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NomeClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NomeClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField Ftxt_CpfCliente;
    private javax.swing.JButton btn_Buscar;
    private javax.swing.JButton btn_Confirmar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rb_Cpf;
    private javax.swing.JRadioButton rb_Email;
    private javax.swing.JRadioButton rb_Nome;
    private javax.swing.JTable tb_Clients;
    private javax.swing.JTextField txt_EmailCliente;
    private javax.swing.JTextField txt_NomeCliente;
    private javax.swing.JTextField txt_SearchField;
    private javax.swing.JTextField txt_idCliente;
    // End of variables declaration//GEN-END:variables
}
