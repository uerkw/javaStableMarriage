package LDS_Project1;

import java.util.ArrayList;

// EECS 2500, Project1
// Written by Kyle Uerkwitz

/**
 * A class to construct a simple object containing the name and preferences
 * of each desired person. Also contains getter methods for these parameters.
 */
public class individualData {

    private String name;
    private ArrayList preferences;

    public individualData(String Name, ArrayList preferences){
        this.name = Name;
        this.preferences = preferences;
    }

    public String getName() {
        return name;
    }

    public ArrayList getPreferences() {
        return preferences;
    }

}
