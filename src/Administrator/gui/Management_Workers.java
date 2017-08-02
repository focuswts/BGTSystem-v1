/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrator.gui;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author focuswts
 */
public class Management_Workers extends javax.swing.JInternalFrame {

    /**
     * Creates new form Management_Workers
     */
    private Connection con = lib.connection.connect();
    public Management_Workers() {
        initComponents();
    preenche_Jtable();
    }

      private void preenche_Jtable() {
        DefaultTableModel aModel = (DefaultTableModel) this.tb_Workers.getModel();
        try {
            int numlinhas = aModel.getRowCount();
            for (int i = 0; i < numlinhas; i++) {
                aModel.removeRow(0);
            }
            String sql = "SELECT funcionarios.idfuncionario,funcionarios.nomeFuncionario,funcionarios.cpfFuncionario,usuarios.login FROM funcionarios INNER JOIN usuarios ON funcionarios.idUsuario = usuarios.id";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
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
            JOptionPane.showMessageDialog(this, "Falha Na Conexao");
        }

    }

      private void buscar() {
        try {
            String nomeFuncionario = txt_searchField.getText();
            String sql = "SELECT * FROM clientes WHERE nomeFuncionario LIKE ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, "%" + nomeFuncionario + "%");
            ResultSet rs = st.executeQuery();
            DefaultTableModel aModel = (DefaultTableModel) this.tb_Workers.getModel();
            int numlinhas = aModel.getRowCount();
            for (int i = 0; i < numlinhas; i++) {
                aModel.removeRow(0);
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Workers = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_searchField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gerenciamento De Funcionários");

        tb_Workers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "CPF", "Login"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Workers.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tb_Workers);

        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Pesquisar");

        txt_searchField.setText("Insira O Nome ");
        txt_searchField.setToolTipText("Insira O Nome");
        txt_searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_searchFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_searchFieldFocusLost(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon("/home/focuswts/Icons For Development/Aesthetica Icons Set/png/24x24/search.png")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txt_searchField))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 106, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(106, 106, 106))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        txt_searchField.getAccessibleContext().setAccessibleName("Insira O Nome");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_searchFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFieldFocusGained
txt_searchField.setText("");
    }//GEN-LAST:event_txt_searchFieldFocusGained

    private void txt_searchFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFieldFocusLost
txt_searchField.setText("Insira O Nome");
    }//GEN-LAST:event_txt_searchFieldFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
buscar();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_Workers;
    private javax.swing.JTextField txt_searchField;
    // End of variables declaration//GEN-END:variables
}