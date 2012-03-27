/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Zhengyi
 */
public class Project implements Serializable {
    private int id;
    private int userID;
    private String name;
    private String description;
    private String myRole;
    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUsers(User user) {
        this.users.add(user);
    }
    
    public Project () {
        name = "";
        id = -1;
        userID = -1;
        description = "";
        myRole = "";
        users = new ArrayList<User>();
    }

    public int getUserID() {
        return userID;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMyRole(String mymyRole) {
        this.myRole = mymyRole;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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
