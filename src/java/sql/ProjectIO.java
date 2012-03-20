/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import business.Project;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Zhengyi
 */
public class ProjectIO {
    public static ArrayList<Project> getProjects (String filePath) {
        ArrayList<Project> projects = new ArrayList<Project>();
        try {
            File file = new File (filePath);
            BufferedReader reader = new BufferedReader (new FileReader (file));
            String projectName = reader.readLine();
            while (projectName != null) {
                Project p = new Project ();
                p.setName (projectName);
                projects.add(p);
                
                projectName = reader.readLine();
            }
            reader.close();
            return projects;
        } catch (IOException ex) {
            return null;
        }
    }
}
