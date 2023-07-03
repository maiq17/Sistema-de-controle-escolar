package oi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class conexao {
	   public static Connection ConnectDb() {
	        try {
	           
	            // Estabelecer a conexão com o banco de dados
	            String url = "jdbc:mysql://localhost:3306/controle_escolar";
	            String username = "root";
	            String password = "";
	            Connection conn = DriverManager.getConnection(url, username, password);
	            
	            JOptionPane.showMessageDialog(null, "Base de dados Conectada");
				return conn;
	            
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	}