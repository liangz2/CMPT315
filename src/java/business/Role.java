/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Icewill
 */
public class Role {
    String id;
    String name;
    
    public Role () {
        id = "";
        name = "";
    }
    
    public Role (String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
