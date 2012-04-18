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
 * The class that communicates with the database, used by other servlets.
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
                + "UserIsActive, UserCreationTime FROM User "
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
                Date creationDate = resultSet.getDate(6);
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
                + "ProjectCreationTime, FirstName, LastName"
                + " FROM Project INNER JOIN User ON User.EmailAddress=ProjectCreator"
                + " WHERE ProjectIsActive=true";
        try {
            connection = pool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            if (activeProjects == null)
                activeProjects = new HashMap<>();

            while (resultSet.next()) {
                Project project = new Project();
                int id = Integer.parseInt(resultSet.getString(1));
                project.setId(id);
                project.setName(resultSet.getString(2));
                project.setDescription(resultSet.getString(3));
                project.setCreationTime(resultSet.getDate(4));
                User creator = new User();
                creator.setFirstName(resultSet.getString(5));
                creator.setLastName(resultSet.getString(6));
                project.setCreator(creator);
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
        String query = "SELECT Project.ProjectId, ProjectName, ProjectDescription, "
                + "Role, ProjectCreationTime, FirstName, LastName FROM Project "
                + "INNER JOIN (WIKIRecord,User) ON Project.ProjectId=WIKIRecord.ProjectId "
                + "AND WIKIRecord.EmailAddress=User.EmailAddress WHERE EmailAddress=? "
                + "AND ProjectIsActive=TRUE";
        
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            resultSet = ps.executeQuery();
            
            if (userProjects == null)
                    userProjects = new HashMap<>();
            
            while (resultSet.next()) {
                Project p = new Project();
                p.setId(Integer.parseInt(resultSet.getString(1)));
                p.setName(resultSet.getString(2));
                p.setDescription(resultSet.getString(3));
                p.setMyRole(resultSet.getString(4));
                p.setCreationTime(resultSet.getDate(5));
                p.setCreator(new User(resultSet.getString(6), resultSet.getString(7)));
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
            if (ps != null)
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
            if (statement != null)
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
            if (rs != null)
                rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
