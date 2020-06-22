/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.bean;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/bd_exalmar?useServerPrepStmts=true";
    private static final String USER = "root";
    private static final String PASSWORD = "crisbd";
    private static Connection con = null;

    public Connection getConection() throws  SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        return con;
    }

    public void CloseSql() throws SQLException {
        con.close();
    }
}
