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
    private User creator;
    private Date creationTime;
    private ArrayList<User> users;
    private ArrayList<String> pages;

    public ArrayList<User> getUsers() {
        return users;
    }

    public Project () {
        name = "";
        description = "";
        myRole = "";
        pendingRole = "";
    }
    
    public Project (int id, String name, String description, String myRole) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.myRole = myRole;
    }

    public ArrayList<String> getPages() {
        return pages;
    }
    
    /**
     * return creation time up to seconds
     * @return 
     */
    public Date getCreationTime() {
        return creationTime;
    }

    public User getCreator() {
        return creator;
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

    public void setMyRole(String email) {
        myRole = "N/A";
        if (users != null)
            for (User user: users)
                if (user.getEmail().equals(email))
                    myRole = user.getRelativeRole();
        
    }

    public void setPages(ArrayList<String> pages) {
        this.pages = pages;
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

    public void setCreator(User creator) {
        this.creator = creator;
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
