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
public class User implements Serializable {
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private ArrayList<Project> projects;
    
    public User () {
        lastName = "";
        firstName = "";
        email = "";
        password = "";
        projects = new ArrayList<Project>();
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }
    
    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }
    
    public void addProject(Project project) {
        projects.add(project);
    }
    
    public void removeProject (Project project) {
        projects.remove(project);
    }
   
    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
