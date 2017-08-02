/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendasGUI;

/**
 *
 * @author focuswts
 */
public class cartItems {
    private String id_Produto;
    private String nome_Produto;
    private String qtd_Produto;
    private String val_UnitarioTotal;

    public cartItems() {
    }

    public cartItems(String id_Produto,String nome_Produto, String qtd_Produto, String val_UnitarioTotal) {
        this.id_Produto = id_Produto;
        this.nome_Produto = nome_Produto;
        this.qtd_Produto = qtd_Produto;
        this.val_UnitarioTotal = val_UnitarioTotal;
    }

   

  
    public String getNome_Produto() {
        return nome_Produto;
    }

    public void setNome_Produto(String nome_Produto) {
        this.nome_Produto = nome_Produto;
    }

    public String getQtd_Produto() {
        return qtd_Produto;
    }

    public void setQtd_Produto(String qtd_Produto) {
        this.qtd_Produto = qtd_Produto;
    }

    public String getVal_UnitarioTotal() {
        return val_UnitarioTotal;
    }

    public void setVal_UnitarioTotal(String val_UnitarioTotal) {
        this.val_UnitarioTotal = val_UnitarioTotal;
    }

    public String getId_Produto() {
        return id_Produto;
    }

    public void setId_Produto(String id_Produto) {
        this.id_Produto = id_Produto;
    }


    
    
}
