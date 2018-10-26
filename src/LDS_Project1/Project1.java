package LDS_Project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import ArrayStack.*;

/**
 * @author Kyle Uerkwitz
 * @version 1.0
 * Originally written for the EECS 2500 course at the University of Toledo
 *
 *  The main program file, used to execute code from the other included methods.
 *  Calls functions and constructors to create and manipulate the pairing data.
 */

class Project1 {

    // Defining the filepath. Editing this will change the file access for the whole program.
    private static final String inputFilePath = "src/Project1TestData.txt";
    //private static final String inputFilePath = "src/newTestdata0.txt";
    //private static final String inputFilePath = "src/testData_2.txt";


    public static void main(String args[]){
        long startTime = 0;
        long endTime = 0;

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
            for(int i = 0; i < (pairingSize); i++)
                groupA.add(initPairingData[i]);
            for(int i = (pairingSize); i < pairingSize*2; i++)
                groupB.add(initPairingData[i]);

            /* Printing the groups to check that they have been properly formatted by the user beforehand
            as well as by the program. Utilizes the testing method created in FileUtilities.java */

            /*
            System.out.println("Group A:");
            FileUtilities.testCurrentData(pairingSize, groupA);
            System.out.println("Group B:");
            FileUtilities.testCurrentData(pairingSize, groupB);
            */

            /* Pass off to another method to process the data. Each instance of a person will have their
             "spouse" field filled, so no ArrayList changes need to be made */
            try{
                startTime = System.nanoTime();
                matchingAlgorithm(groupA, groupB, pairingSize);
                endTime = System.nanoTime();
            } catch (StackOverflowException OFlowExcept){
                OFlowExcept.printStackTrace();
                System.out.println("A Stack Overflow occurred during the algorithm.");
            } catch (StackUnderflowException UFlowExcept){
                UFlowExcept.printStackTrace();
                System.out.println("A Stack Underflow occurred during the algorithm.");
            }

            // Add all matches to one main ArrayList
            ArrayList<ArrayList<individualData>> Matches = new ArrayList<ArrayList<individualData>>();

            for (int i = 0; i < groupB.size(); i++){

                // Use a temporary arrayList to hold both people of the pairing
                ArrayList<individualData> tempMatches = new ArrayList<individualData>();
                tempMatches.add(groupB.get(i));
                tempMatches.add(groupB.get(i).getSpouse());

                /* Add this temporary arrayList to the main List, each index of
                'Matches' holds the pair for the printing method to process */
                Matches.add(tempMatches);
            }
            //Finally, pass the main ArrayList off to be printed
            FileUtilities.printFinalData(pairingSize, Matches);


        } catch(IOException IOe) {
            IOe.printStackTrace();
            System.out.println("The program encountered an error in creating pairing data");
        }
        System.out.println((endTime - startTime)/1_000_000_000.0);
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
    private static void matchingAlgorithm(ArrayList<individualData> groupA, ArrayList<individualData> groupB,
                                          int pairingSize) throws StackOverflowException, StackUnderflowException{

        //Initialize a stack for backtracking purposes
        ArrayStack soloPeople = new ArrayStack();
        for (int i = pairingSize - 1; i >= 0; i--) {
            soloPeople.push(groupA.get(i));
        }
        while (!soloPeople.isEmpty()) {

            //Access the first person in the stack
            individualData initialPerson = (individualData)soloPeople.top();
            soloPeople.pop();

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
                    if ((preferanceOfProposer < currentSpouse_preferance)&&(i >= initialPerson.getSpousePreference()))
                    {

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