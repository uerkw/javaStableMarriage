package LDS_Project1;

import java.util.ArrayList;

// EECS 2500, Project1
// Written by Kyle Uerkwitz

/**
 * A class to construct a simple object containing the name and preferences
 * of each desired person. Also contains getter methods for these parameters.
 */
class individualData {

    private String name;
    private int[] preferences;

    individualData(String Name, int[] preferences){
        this.name = Name;
        this.preferences = preferences;
    }

    String getName() {
        return name;
    }

    int[] getPreferences() {
        return preferences;
    }

}
