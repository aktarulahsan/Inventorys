/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author C3
 */
public class Database {
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test2","root", "123");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    
    }
    public void close(Connection con){
        try {
            con.close();
        } catch (Exception e) {
        }
    }
    
}
