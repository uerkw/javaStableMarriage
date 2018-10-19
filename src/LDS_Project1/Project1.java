package LDS_Project1;

import java.io.BufferedReader;
import java.io.IOException;

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
            individualData[] groupA = new individualData[pairingSize];
            individualData[] groupB = new individualData[pairingSize];

            // Copying each half of the array into its respective group
            // TODO: Possible array copying optimization
            for(int i = 0; i < (pairingSize); i++)
                groupA[i] = initPairingData[i];
            for(int i = (pairingSize); i < pairingSize*2; i++)
                groupB[i-pairingSize] = initPairingData[i];

            /* Printing the groups to check that they have been properly formatted by the user beforehand
            as well as by the program. Utilizes the testing method created in FileUtilities.java */

            System.out.println(ANSI_YELLOW + "Group A:");
            FileUtilities.testCurrentData(pairingSize, groupA);
            System.out.println(ANSI_YELLOW + "Group B:");
            FileUtilities.testCurrentData(pairingSize, groupB);

            // Pass off to another method to process the data
            matchingAlgorithm(groupA, groupB, pairingSize);

        } catch(IOException IOe) {
            IOe.printStackTrace();
            System.out.println(ANSI_RED + "The program encountered an error in " + ANSI_YELLOW +
                    "creating pairing data");
        }


    }
    private static void matchingAlgorithm(individualData[] groupA, individualData[] groupB, int pairingSize){
        //TODO create matching algorithm (implementing a stack for backtracking).
    }
}
