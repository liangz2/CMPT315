/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import business.Project;
import business.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Zhengyi
 */
public class DBUtil {
    private static ConnectionPool pool = ConnectionPool.getInstance ();
    
    public static Project getProject (int pId) {
        PreparedStatement ps = null;
        Project project = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Project "
                    + "WHERE ProjectID = ?";
        try {
            Connection connection = pool.getConnection ();
            ps = connection.prepareStatement (query);
            ps.setString(1, Integer.toString(pId));
            resultSet = ps.executeQuery();
            
            if (resultSet.next()) {
                project = new Project ();
                project.setId(Integer.parseInt(resultSet.getString("projectid")));
                project.setName(resultSet.getString("projectname"));
                project.setDescription(resultSet.getString("projectdescription"));
            }
            
            resultSet.close();
            ps.close();
            pool.freeConnection(connection);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return project;
    }
    
    public static User getUser (String email) {
        User user = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM User "
                    + "WHERE emailaddress = ?";
        try {
            Connection connection = pool.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            
            resultSet = ps.executeQuery();
            
            // if login successful
            if (resultSet.next ()) {
                user = new User();
                // obtain user data
                user.setFirstName (resultSet.getString (1));
                user.setLastName (resultSet.getString (2));
                user.setEmail (resultSet.getString (3));
                user.setPassword (resultSet.getString (4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return user;
    }
    
    public static HashMap getActiveProjects () {
        
        return null;
    }
    
    public static HashMap getUserProjects (String email) {
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        HashMap<Integer, Project> userProjects = null;
        String query = "select project.projectid, "
                    + "projectname, projectdescription, role from project "
                    + "inner join wikirecord on project.projectid=wikirecord.projectid "
                    + "where useremail=? and projectisactive=true";
        
        try {
            Connection connection = pool.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            resultSet = ps.executeQuery();
            
            if (userProjects == null)
                    userProjects = new HashMap<Integer, Project>();
            
            while (resultSet.next()) {
                Project p = new Project(Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4));
                // add projects to the user for future usage
                userProjects.put(p.getId(), p);
            }
            resultSet.close();
            ps.close();
            pool.freeConnection(connection);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return userProjects;
    }
    
    public static ArrayList<User> getProjectUsers (String pId) {        
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        ArrayList<User> users = null;
        String query = "select firstname, lastname, emailaddress, role"
                    + " from wikirecord inner join user on user.emailaddress="
                    + "wikirecord.useremail where projectid=?";
        try {
            Connection connection = pool.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, pId);
            resultSet = ps.executeQuery();
            
            if (users == null)
                users = new ArrayList<User>();
            
            while (resultSet.next()) 
                users.add(new User(resultSet.getString(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getString(4), ""));
            
            resultSet.close();
            ps.close();
            pool.freeConnection(connection);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return users;
    }
}
