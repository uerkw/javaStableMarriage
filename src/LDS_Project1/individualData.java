package LDS_Project1;


/**
 * @author Kyle Uerkwitz
 * @version 1.0
 * Originally written for the EECS 2500 course at the University of Toledo
 *
 * A class to construct a simple object containing the name and preferences
 * of each desired person. Also contains getter methods for these parameters.
 */
class individualData {

    private String name;
    private int[] preferences;
    private int[] preferencesAsOrder;
    private boolean engagedStatus;
    private int spousePreference;
    private individualData spouse;
    individualData(String Name, int[] preferences){
        this.name = Name;
        this.preferences = preferences;
        this.engagedStatus = false;
        this.spousePreference = -1;
        this.preferencesAsOrder = preferencesAsOrder(preferences);
    }

    String getName() {
        return name;
    }

    int[] getPreferences() {
        return preferences;
    }

    boolean getEngaged(){
        return engagedStatus;
    }
    void setSpousePreference(int pref){
        this.spousePreference = pref;
    }
    int getSpousePreference(){
        return spousePreference;
    }
    individualData getSpouse(){
        return spouse;
    }
    void setSpouse(individualData spouse){
        this.spouse = spouse;
    }
    void setEngaged(boolean engStat){
        this.engagedStatus = engStat;
    }

    int[] getPreferencesAsOrder() {
        return preferencesAsOrder;
    }

    /**
     *  Turns the formatted preferences into an order more easily workable by the machines. The index of the
     *  array provides the preference of the other member (with 0 index being highest preference) and the
     *  value represents what index they are in the other group list. Allows the algorithm in Project1.java
     *  to be much easier to implement.
     *
     * @param arr   The unformatted array
     * @return      The formatted array, with index corresponding to index of the other group and the value
     *              representing their preference for that index of the group.
     */
    private int[] preferencesAsOrder(int[] arr){
        int[] orderedArray = new int[arr.length];
        for(int i = 0; i < arr.length; i++)
            for(int j = 0; j < arr.length; j++){
                if(j == arr[i]) orderedArray[j] = i;
            }

        return orderedArray;
    }
}
