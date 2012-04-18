/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Zhengyi
 */
public class User implements Serializable {
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private Date creationTime;
    private boolean isActive;
    private Project selectedProject;
    private HashMap<Integer, Project> projects;
    private String relativeRole;

    public User () {
        lastName = "";
        firstName = "";
        email = "";
        password = "";
        if (projects == null)
            projects = new HashMap<>();
    }
    
    public User (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public User (String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    
    public User (String firstName, String lastName, String email, String password, Timestamp creationTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creationTime = creationTime;
    }
    
    public User (String firstName, String lastName, String email, String relativeRole, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.relativeRole = relativeRole;
        this.password = "";
    }

    public boolean isIsActive() {
        return isActive;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public String getRelativeRole() {
        return relativeRole;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }
       
    public HashMap<Integer, Project> getProjects() {
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
    
    public void addProject(int id, Project project) {
        projects.put(id, project);
    }
    
    public void removeProject (int projectId) {
        projects.remove(projectId);
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
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

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRelativeRole(String relativeRole) {
        this.relativeRole = relativeRole;
    }

    public void setSelectedProject(int pId) {
        selectedProject = projects.get(pId);
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    public void setProjects(HashMap<Integer, Project> projects) {
        this.projects = projects;
    }
    
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
