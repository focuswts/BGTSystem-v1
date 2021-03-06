/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendasGUI;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author focuswts
 */
public class PaymentGUI extends javax.swing.JInternalFrame {

    /**
     * Creates new form PaymentGUI
     */
    //CONEXAO DATABASE//
    private Connection con = lib.connection.connect();
    private String valorTotal;
    private int codigoVenda;
    private ArrayList<cartItems> item;
    private Date data;
    public static int idCliente;
    private int idFuncionario = sellGUI.getIdFuncionario();
    private int idFormaPagamento;
    private BigDecimal valorDesconto;
    private BigDecimal valorTotalDescontado;
    int contador = 1;
//////////////////////////

    public PaymentGUI() {
        initComponents();
        preencheCBMetodos();
        lb_CheckClient.setVisible(false);
        Ftxt_desconto.setText("0");
        Ftxt_troco.setEditable(false);
        txt_nomeVendedor.setText(sellGUI.getNomeFuncionario());
    }

    public PaymentGUI(ArrayList<cartItems> item, String valorTotal, int codigoVenda, Date date) {
        this.valorTotal = valorTotal;
        this.codigoVenda = codigoVenda;
        this.item = item;
        this.data = date;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_NomeCliente = new javax.swing.JTextField();
        btn_Pesquisar = new javax.swing.JButton();
        lb_CheckClient = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_nomeVendedor = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Ftxt_totalValue = new javax.swing.JFormattedTextField();
        Ftxt_desconto = new javax.swing.JFormattedTextField();
        Ftxt_subTotal = new javax.swing.JFormattedTextField();
        Ftxt_receivedValue = new javax.swing.JFormattedTextField();
        Ftxt_troco = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cb_paymentForms = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        lb_codVenda = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lb_totalValue = new javax.swing.JLabel();
        btn_updateValues = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btn_concluirVenda = new javax.swing.JButton();

        setClosable(true);
        setTitle("Pagamento");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel1.setText("Cliente:");

        txt_NomeCliente.setEditable(false);
        txt_NomeCliente.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        txt_NomeCliente.setEnabled(false);
        txt_NomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NomeClienteActionPerformed(evt);
            }
        });

        btn_Pesquisar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        btn_Pesquisar.setIcon(new javax.swing.ImageIcon("/home/focuswts/Icons For Development/Aesthetica Icons Set/png/16x16/search.png")); // NOI18N
        btn_Pesquisar.setText("Pesquisar");
        btn_Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PesquisarActionPerformed(evt);
            }
        });

        lb_CheckClient.setIcon(new javax.swing.ImageIcon("/home/focuswts/Icons For Development/Aesthetica Icons Set/png/24x24/user_accept.png")); // NOI18N

        jLabel11.setText("Vendedor:");

        txt_nomeVendedor.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel11))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_nomeVendedor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_NomeCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(lb_CheckClient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(btn_Pesquisar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_CheckClient)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_NomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_Pesquisar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nomeVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel2.setText("Valor Total Da Venda:");

        jLabel3.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel3.setText("Desconto:");

        jLabel4.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel4.setText("Sub Total:");

        jLabel5.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel5.setText("Valor Recebido:");

        jLabel6.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel6.setText("Troco:");

        Ftxt_totalValue.setEditable(false);
        Ftxt_totalValue.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        Ftxt_totalValue.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        Ftxt_desconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        Ftxt_desconto.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        Ftxt_desconto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Ftxt_descontoFocusLost(evt);
            }
        });

        Ftxt_subTotal.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        Ftxt_receivedValue.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        Ftxt_receivedValue.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        Ftxt_receivedValue.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Ftxt_receivedValueFocusLost(evt);
            }
        });

        Ftxt_troco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        Ftxt_troco.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("%");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Ftxt_subTotal)
                        .addComponent(Ftxt_receivedValue)
                        .addComponent(Ftxt_troco)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Ftxt_desconto, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel10)))
                    .addComponent(Ftxt_totalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Ftxt_totalValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ftxt_desconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Ftxt_subTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Ftxt_receivedValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Ftxt_troco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel7.setText("Tipo De Pagamento");
        jLabel7.setToolTipText("");

        cb_paymentForms.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_paymentForms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_paymentFormsActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel8.setText("Código Da Venda");

        lb_codVenda.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        lb_codVenda.setText("jLabel9");

        jLabel9.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel9.setText("Valor Total Da Venda");

        lb_totalValue.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        lb_totalValue.setText("jLabel10");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_paymentForms, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(67, 67, 67)
                                        .addComponent(jLabel8)))
                                .addGap(0, 72, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(lb_codVenda))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabel9)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(lb_totalValue)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_paymentForms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_codVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_totalValue)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        btn_updateValues.setText("Calcular Troco E Desconto");
        btn_updateValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateValuesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_updateValues)
                                .addGap(34, 34, 34)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_updateValues))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_concluirVenda.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        btn_concluirVenda.setIcon(new javax.swing.ImageIcon("/home/focuswts/Icons For Development/Aesthetica Icons Set/png/24x24/shopping_cart_accept.png")); // NOI18N
        btn_concluirVenda.setText("Concluir Venda");
        btn_concluirVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_concluirVendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_concluirVenda)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_concluirVenda)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_PesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PesquisarActionPerformed
        searchClientGUI searchClients = new searchClientGUI();
        JDesktopPane desktopPane = this.getDesktopPane();
        desktopPane.add(searchClients);
        searchClients.setVisible(true);
    }//GEN-LAST:event_btn_PesquisarActionPerformed

    private void txt_NomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NomeClienteActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_NomeClienteActionPerformed

    private void btn_concluirVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_concluirVendaActionPerformed
        checkPaymentMethod();
        concluirCompra();
    }//GEN-LAST:event_btn_concluirVendaActionPerformed

    private void Ftxt_descontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Ftxt_descontoFocusLost
    }//GEN-LAST:event_Ftxt_descontoFocusLost

    private void Ftxt_receivedValueFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Ftxt_receivedValueFocusLost
    }//GEN-LAST:event_Ftxt_receivedValueFocusLost

    private void btn_updateValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateValuesActionPerformed
        if (Ftxt_desconto.getText().equals("") || Ftxt_receivedValue.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Insira O Valor Recebido E/OU Valor Do Desconto");
        }
        if (contador == 1) {
            calculoDesconto();
            contador = contador + 1;
        } else {
            //JOptionPane.showMessageDialog(this, "Cálculo Para Atualizar Os Valores Já Efetuado");

        }
        calculoTroco();
    }//GEN-LAST:event_btn_updateValuesActionPerformed

    private void cb_paymentFormsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_paymentFormsActionPerformed
    }//GEN-LAST:event_cb_paymentFormsActionPerformed

    public static void setTxt_NomeCliente(String nome) {
        String nomeCliente = nome;
        txt_NomeCliente.setText(nomeCliente);
        lb_CheckClient.setVisible(true);
    }

    private void preencheCBMetodos() {
        try {
            cb_paymentForms.removeAllItems();
            String sql = "SELECT idForma,nomeForma FROM formasPagamento";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            cb_paymentForms.insertItemAt("----Selecione A Forma De Pagamento----", 0);
            int x = 1;
            while (rs.next()) {
                cb_paymentForms.insertItemAt(rs.getString("nomeForma"), x);
                x++;
            }
            cb_paymentForms.setSelectedIndex(0);
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Falha Na Conexão");
        }

    }

    private void checkPaymentMethod() {
        if (cb_paymentForms.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Selecione Uma Forma De Pagamento");
        } else {
            try {
                String sql = "SELECT idForma from formasPagamento where nomeForma = ?";
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                st.setString(1, cb_paymentForms.getSelectedItem().toString());
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    idFormaPagamento = rs.getInt(1);
                }
                st.close();
            } catch (Exception e) {
            }

        }

    }

    private void calculoDesconto() {
        double valorDescontoDV = Double.valueOf(Ftxt_desconto.getText());
        BigDecimal valorDescontoDecimal = new BigDecimal(valorDescontoDV).divide(new BigDecimal(100));
        BigDecimal valorTotal = new BigDecimal(Double.valueOf(lb_totalValue.getText()));
        valorDesconto = valorTotal.multiply(valorDescontoDecimal);
        valorTotalDescontado = valorTotal.subtract(valorDesconto).setScale(2);
        //JOptionPane.showMessageDialog(this, valorTotalDescontado.toString());
        //   DecimalFormat decimalFormat = new DecimalFormat();
        //decimalFormat.setMinimumFractionDigits(2);

        lb_totalValue.setText(valorTotalDescontado.toString());
        Ftxt_desconto.setEditable(false);
    }

    private void calculoTroco() {
        BigDecimal valorTotal = new BigDecimal(Double.valueOf(lb_totalValue.getText().replaceAll(",", ".")));
        BigDecimal valorRecebido = new BigDecimal(Double.valueOf(Ftxt_receivedValue.getText().replaceAll(",", ".")));
        if (valorTotal.compareTo(valorRecebido) == 1) {
            JOptionPane.showMessageDialog(this, "Valor Recebido Insuficiente");
            return;
        }
        BigDecimal troco = valorRecebido.subtract(valorTotal);
        Ftxt_troco.setText(troco.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        Ftxt_troco.setEditable(false);
    }

    private void addItemsToDB() {

        for (int i = 0; i < item.size(); i++) {
            // JOptionPane.showMessageDialog(this, item.get(i).getNome_Produto() + "" + item.get(i).getQtd_Produto() + "" + item.get(i).getVal_UnitarioTotal());
            int idProduto = Integer.valueOf(item.get(i).getId_Produto());
            String nomeProduto = item.get(i).getNome_Produto();
            BigDecimal qtdProduto = new BigDecimal(Integer.valueOf(item.get(i).getQtd_Produto()));
            BigDecimal valUnitTotal = new BigDecimal(item.get(i).getVal_UnitarioTotal());
            BigDecimal valUnit = valUnitTotal.divide(qtdProduto);
            try {
                String sql = "INSERT INTO itemsPedido (id,idProduto,quantidade,valorUnitario,valorTotal) VALUES (?,?,?,?,?)";
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                System.out.println("ID PRODUTO:" + idProduto);
                st.setInt(1, codigoVenda);
                st.setInt(2, idProduto);
                st.setInt(3, qtdProduto.intValue());
                st.setBigDecimal(4, valUnit);
                st.setBigDecimal(5, valUnitTotal);
                st.executeUpdate();
                st.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    private void addPedido() {
        java.sql.Date dataSql = new java.sql.Date(data.getTime());
        try {
            String sql = "INSERT INTO pedidos (id,data,id_Funcionario,id_Cliente,id_FormaPagamento,percentualDesconto,valorDesconto,valorTotal) values(?,?,?,?,?,?,?,?)";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            st.setInt(1, codigoVenda);
            st.setDate(2, dataSql);
            st.setInt(3, idFuncionario);
            st.setInt(4, idCliente);
            st.setInt(5, idFormaPagamento);
            st.setInt(6, Integer.valueOf(Ftxt_desconto.getText()));
            st.setBigDecimal(7, valorDesconto);
            st.setBigDecimal(8, valorTotalDescontado);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void updateEstoque() {
        try {
//Buscar Os Items Do Pedido Para Atualizar Estoque//
            String sql = "SELECT * FROM itemsVenda WHERE itemsVenda.id = ?";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            st.setInt(1, codigoVenda);
            ResultSet rs = st.executeQuery();
            /////////////////

            //Atualizar Estoque//
            String sql2 = "UPDATE produtos set quantidade = quantidade - ? WHERE desprod = ?";
            while (rs.next()) {
                PreparedStatement st2 = (PreparedStatement) con.prepareStatement(sql2);
                String descricaoProduto = rs.getString("desprod");
                double quantidade = rs.getInt("quantidade");
                System.out.println(descricaoProduto);
                System.out.println(quantidade);
                st2.setDouble(1, quantidade);
                st2.setString(2, descricaoProduto);
                st2.executeUpdate();
                st2.close();
            }
            st.close();
            JOptionPane.showMessageDialog(this, "Venda Efetuada Com Sucesso!");
            //////////////////
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    private void concluirCompra() {
        try {
            addPedido();
            addItemsToDB();
            updateEstoque();
            this.dispose();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField Ftxt_desconto;
    private javax.swing.JFormattedTextField Ftxt_receivedValue;
    private javax.swing.JFormattedTextField Ftxt_subTotal;
    private javax.swing.JFormattedTextField Ftxt_totalValue;
    private javax.swing.JFormattedTextField Ftxt_troco;
    private javax.swing.JButton btn_Pesquisar;
    private javax.swing.JButton btn_concluirVenda;
    private javax.swing.JButton btn_updateValues;
    private javax.swing.JComboBox<String> cb_paymentForms;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private static javax.swing.JLabel lb_CheckClient;
    private javax.swing.JLabel lb_codVenda;
    private javax.swing.JLabel lb_totalValue;
    private static javax.swing.JTextField txt_NomeCliente;
    private javax.swing.JTextField txt_nomeVendedor;
    // End of variables declaration//GEN-END:variables

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
        this.lb_totalValue.setText(valorTotal);
        this.Ftxt_totalValue.setText(valorTotal);
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public void setCodigoVenda(int codigoVenda) {
        this.codigoVenda = codigoVenda;
        this.lb_codVenda.setText(String.valueOf(codigoVenda));
    }

    public ArrayList<cartItems> getItem() {
        return item;
    }

    public void setItem(ArrayList<cartItems> item) {
        this.item = item;
    }

    public Date getDate() {
        return data;
    }

    public void setDate(Date date) {
        this.data = date;
    }

    public static void setIdCliente(int id_Cliente) {
        idCliente = id_Cliente;
    }
}
