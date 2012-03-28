/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Zhengyi
 */
public class ConnectionPool {
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    private ConnectionPool () {
        try {
            InitialContext ic = new InitialContext ();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/wiki");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ConnectionPool getInstance () {
        if (pool == null) {
            pool = new ConnectionPool ();
        }
        
        return pool;
    }
    
    public Connection getConnection () {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void freeConnection (Connection c) {
        try {
            c.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
