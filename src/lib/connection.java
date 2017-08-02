/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;


import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author focuswts
 */

public class connection {
    public Connection con;
    public static Connection connect(){
   Connection con = null;    
      try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Nao Encontrado");
        }
        
        try {
            String host = "localhost";
            String dbmysql = "zanete";
            String user = "netbeans";
            String pwd = "1337";
            
            con = (Connection) DriverManager.getConnection("jdbc:mysql://"+host+"/"+dbmysql,user,pwd);
            System.out.println("Conexao Com Sucesso");
           return con;  

        } catch (SQLException e) {
            System.out.println("Conexao Falhou");
        }
      return con ;
    }
    
    
}
