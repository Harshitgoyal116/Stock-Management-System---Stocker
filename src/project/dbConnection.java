/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Harshit Goyal
 */
public class dbConnection {
        public static Connection getCon(){
        try {
            /////////////////////////////SQLite3 dbBrowser///////////////////////////////////////////////
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:data.db");
            return con;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection failed"+e.getMessage());
        } 
            return null;
    }
}
