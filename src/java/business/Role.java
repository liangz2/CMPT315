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
    public static final int ADMIN = 0;
    public static final int COORD = 1;
    public static final int CONTRIB = 2;
    public static final int OBSERV = 3;
    public static final int INACTIVE = 4;
    public static final int PENDING = 5;
    public static final int NONE = -99;
    private int id;
    private String name;
    
    public Role () {
        id = NONE;
        name = Constants.NONE;
    }
    
    public Role (int id) {
        switch (id) {
            case ADMIN:
                name = Constants.ADMIN;
                break;
            case COORD:
                name = Constants.COORD;
                break;
            case CONTRIB:
                name = Constants.CONTRIB;
                break;
            case OBSERV:
                name = Constants.OBSERV;
                break;
            case INACTIVE:
                name = Constants.INACTIVE;
                break;
            case PENDING:
                name = Constants.PENDING;
                break;
            default:
                name = Constants.NONE;
        }
    }
    
    public Role (int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public boolean equals(Object name) {
        return this.name.hashCode() == name.hashCode();
    }
}
