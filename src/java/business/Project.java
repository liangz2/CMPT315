/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Zhengyi
 */
public class Project implements Serializable {
    private int id;
    private String name;
    private String description;
    private String myRole;
    private String pendingRole;
    private Date creationTime;
    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public Project () {
        name = "";
        description = "";
        myRole = "";
    }
    
    public Project (int id, String name, String description, String myRole) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.myRole = myRole;
    }
    /**
     * returns creation time up to date only
     * @return 
     */
    public String getCreationDate() {
        return creationTime.toString();
    }
    
    /**
     * return creation time up to seconds
     * @return 
     */
    public Date getCreationTime() {
        return creationTime;
    }

    public String getPendingRole() {
        return pendingRole;
    }

    public String getMyRole() {
        return myRole;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMyRole(String mymyRole) {
        this.myRole = mymyRole;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    
    public void setPendingRole(String pendingRole) {
        this.pendingRole = pendingRole;
    }
    
    @Override
    public String toString() {
        return name + 
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "[" + myRole + "]"; 
    }
}
