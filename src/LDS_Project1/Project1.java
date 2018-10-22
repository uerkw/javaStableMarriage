package LDS_Project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author Kyle Uerkwitz
 * @version 1.0
 * Originally written for the EECS 2500 course at the University of Toledo
 *
 *  The main program file, used to execute code from the other included methods.
 *  Calls functions and constructors to create and manipulate the pairing data.
 */

class Project1 {
    // Defining colors to be used in System Printing to
    // keep track of errors and aid debugging in console
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_CYAN = "\u001B[36m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_WHITE = "\u001B[37m";

    // Defining the filepath. Editing this will change the file access for the whole program.
    private static final String inputFilePath = "src/Project1TestData.txt";


    public static void main(String args[]){


        // Open file and assign a new bufferedReader from inputFilePath
        BufferedReader bufferedReader = FileUtilities.assignReader(inputFilePath);

        /* Declare & assign pairingSize from the bufferedReader we setup above
           FileUtilities.assignPairing will find the pairing size */
        int pairingSize = FileUtilities.assignPairing(bufferedReader);


        /* Declare & assign an array of usable data filled up from the file,
           process the data into two groups and print the results for
           checking/debugging purposes, and finally run the matching method.
           Try and catch to handle the IOException thrown in FileUtilities.makeRawData */
        try{
            individualData[] initPairingData = FileUtilities.makeRawData(bufferedReader, pairingSize);
            ArrayList<individualData> groupA = new ArrayList<>();
            ArrayList<individualData> groupB = new ArrayList<>();

            // Copying each half of the array into its respective group
            // TODO: Possible array copying optimization
            for(int i = 0; i < (pairingSize); i++)
                groupA.add(initPairingData[i]);
            for(int i = (pairingSize); i < pairingSize*2; i++)
                groupB.add(initPairingData[i]);

            /* Printing the groups to check that they have been properly formatted by the user beforehand
            as well as by the program. Utilizes the testing method created in FileUtilities.java */

            System.out.println(ANSI_YELLOW + "Group A:");
            FileUtilities.testCurrentData(pairingSize, groupA);
            System.out.println(ANSI_YELLOW + "Group B:");
            FileUtilities.testCurrentData(pairingSize, groupB);

            // Pass off to another method to process the data
            matchingAlgorithm(groupA, groupB, pairingSize);

            // Add them all to an array and print
            ArrayList Matches = new ArrayList();

            for (int i = 0; i < groupB.size(); i++){
                ArrayList tempMatches = new ArrayList();
                tempMatches.add(groupB.get(i));
                tempMatches.add(groupB.get(i).getSpouse());
                Matches.add(tempMatches);
            }
            FileUtilities.printFinalData(pairingSize, Matches);


        } catch(IOException IOe) {
            IOe.printStackTrace();
            System.out.println(ANSI_RED + "The program encountered an error in " + ANSI_YELLOW +
                    "creating pairing data");
        }

    }

    /**
     *  An algorithm used to pair each group member with their most desired and free choice. The algorithm
     *  will add each member of one group to a stack at the beginning to keep track of the single people
     *  (called soloPeople in this implementation). Anytime a person becomes free, they are added to the
     *  stack and try to find a new partner. Partners matching and matches are called engagements and
     *  spouses to easier represent what is happening in the method.
     *
     * @param groupA    One group of the potential pairs in ArrayList form
     * @param groupB    The second provided group, each is matched to a member of Group A
     * @param pairingSize   The provided pairing size for each
     */
    private static void matchingAlgorithm(ArrayList<individualData> groupA, ArrayList<individualData> groupB, int pairingSize) {

        //Initialize a stack for backtracking
        Stack<individualData> soloPeople = new Stack<individualData>();
        for (int i = pairingSize - 1; i >= 0; i--) {
            soloPeople.push(groupA.get(i));
        }
        while (!soloPeople.empty()) {

            //Access the first person in the stack
            individualData initialPerson = soloPeople.pop();

            for (int i = 0; i < pairingSize && !initialPerson.getEngaged(); i++) {
                //Get the first person in the preferences, set up a few variables for use
                int preferredPerson = initialPerson.getPreferencesAsOrder()[i];

                individualData personDesired = groupB.get(preferredPerson);
                int initialPerson_index = 0;


                for (int j = 0; j < pairingSize; j++) {
                    if (initialPerson == groupA.get(j))
                        initialPerson_index = j;
                }

                // Find the person being proposed to's preference of proposer
                int preferanceOfProposer = personDesired.getPreferences()[initialPerson_index];

                // Get the current spouse for use later on
                individualData currentSpouse = personDesired.getSpouse();

                int currentSpouse_preferance = personDesired.getSpousePreference();

                //Check person is free

                if (!personDesired.getEngaged() && i >= initialPerson.getSpousePreference()) {

                    //Current spouse (if exists) is now single, added to stack
                    if (currentSpouse != null) {
                        currentSpouse.setEngaged(false);
                        currentSpouse.setSpousePreference(-1);
                        soloPeople.push(currentSpouse);
                    }
                    //If free, an engagement follows.
                    initialPerson.setEngaged(true);
                    initialPerson.setSpouse(personDesired);
                    initialPerson.setSpousePreference(i);
                    personDesired.setEngaged(true);
                    personDesired.setSpouse(initialPerson);
                    personDesired.setSpousePreference(preferanceOfProposer);

                } else {
                    if ((preferanceOfProposer < currentSpouse_preferance) && (i >= initialPerson.getSpousePreference())) {

                        //Current spouse (if exists) is now single, added to stack
                        if (currentSpouse != null) {
                            currentSpouse.setEngaged(false);
                            currentSpouse.setSpousePreference(-1);
                            soloPeople.push(currentSpouse);
                        }

                        //An engagement follows.
                        initialPerson.setEngaged(true);
                        initialPerson.setSpouse(personDesired);
                        personDesired.setEngaged(true);
                        personDesired.setSpouse(initialPerson);
                        personDesired.setSpousePreference(preferanceOfProposer);
                    }
                }

            }
        }
    }
}
