/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import business.Project;
import business.User;
import java.sql.*;
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
        Connection connection = null;
        String query = "SELECT * FROM Project "
                    + "WHERE ProjectID = ?";
        try {
            connection = pool.getConnection ();
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
        } finally {
            // free up resources
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
            return project;
        }
    }
    
    public static User getUser (String email) {
        User user = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String query = "SELECT FisrtName, LastName, EmailAddress, Password "
                + "UserIsActive, UserCreationTime + 0 FROM User "
                + "WHERE emailaddress = ?";
        try {
            connection = pool.getConnection();
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
                user.setIsActive(resultSet.getString(5).equals("1"));
                Timestamp creationDate = 
                        new Timestamp(Long.parseLong(resultSet.getString(6)));
                user.setCreationTime(creationDate);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // free up resources
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
            return user;
        }
    }
    
    /**
     * create a user in the DB
     * @param user 
     */
    public static void createUser (User user) {
        PreparedStatement ps = null;
        String query = "insert into user values (?,?,?,?,NOW(),true)";
        Connection connection = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(query);
            // pull out the info to complete the query
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            // add the user into the DB
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // free up resources
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    /**
     * get all the active projects just the descriptions and the ids 
     * @return 
     */
    public static HashMap getActiveProjects () {
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        HashMap<Integer, Project> activeProjects = null;
        String query = "SELECT ProjectID, ProjectName, ProjectDescription, "
                + "ProjectCreationTime"
                + " FROM Project WHERE ProjectIsActive=true";
        try {
            connection = pool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            if (activeProjects == null)
                activeProjects = new HashMap<Integer, Project>();

            while (resultSet.next()) {
                Project project = new Project();
                int id = Integer.parseInt(resultSet.getString(1));
                project.setId(id);
                project.setName(resultSet.getString(2));
                project.setDescription(resultSet.getString(3));
                project.setCreationTime(resultSet.getDate(4));
                activeProjects.put(id, project);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            pool.freeConnection(connection);
            DBUtil.closeStatement(statement);
            DBUtil.closeResultSet(resultSet);
            return activeProjects;
        }
    }
    /**
     * get the projects that the user is currently in
     * @param email
     * @return 
     */
    public static HashMap getUserProjects (String email) {
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        Connection connection = null;
        HashMap<Integer, Project> userProjects = null;
        String query = "select project.projectid, "
                    + "projectname, projectdescription, role from project "
                    + "inner join wikirecord on project.projectid=wikirecord.projectid "
                    + "where EmailAddress=? and projectisactive=true";
        
        try {
            connection = pool.getConnection();
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // free up resources
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
            return userProjects;
        }
    }
    
    /**
     * obtain all users that registered under a project by project id
     * @param pId
     * @return 
     */
    public static ArrayList<User> getProjectUsers (String pId) {        
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        ArrayList<User> users = null;
        Connection connection = null;
        String query = "select firstname, lastname, emailaddress, role"
                    + " from wikirecord inner join user on user.emailaddress="
                    + "wikirecord.EmailAddress where projectid=?";
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, pId);
            resultSet = ps.executeQuery();
            
            if (users == null)
                users = new ArrayList<User>();
            
            while (resultSet.next()) 
                users.add(new User(resultSet.getString(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getString(4), ""));
                        
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // free up resources
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
            return users;
        }
    }
    
    /**
     * create a project into the DB
     * @param p 
     */
    public static boolean createProject (Project p, String email) {
        PreparedStatement ps = null;
        Connection connection = null;
        String query = "INSERT INTO Project "
                + "(Projectname, ProjectDescription, ProjectCreator, ProjectCreationTime, ProjectIsActive)"
                + " Values (?,?,?,NOW(),true)";
        boolean success = false;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString (1, p.getName());
            ps.setString (2, p.getDescription());
            ps.setString(3, email);
            
            ps.executeUpdate();
            success = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
            return success;
        }
    }
    
    /**
     * close a preparedstatement
     * @param ps 
     */
    public static void closePreparedStatement (PreparedStatement ps) {
        try {
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * close a statement
     * @param statement 
     */
    public static void closeStatement (Statement statement) {
        try {
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * close a resultset
     * @param rs 
     */
    public static void closeResultSet (ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
