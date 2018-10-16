package LDS_Project1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

// EECS 2500, Project1
// Written by Kyle Uerkwitz


class Project1 {
    // Defining colors to be used in System Printing to
    // keep track of errors and aid debugging in console
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_CYAN = "\u001B[36m";
    static final String ANSI_YELLOW = "\u001B[33m";

    private static final String filePath = "src/Project1TestData.txt";


    public static void main(String args[]){


        // Open file and assign a new bufferedReader from filePath
        BufferedReader bufferedReader = FileUtilities.assignReader(filePath);

        // Declare & assign pairingSize from the bufferedReader we setup above
        // FileUtilities.assignPairing will find the pairing size
        int pairingSize = FileUtilities.assignPairing(bufferedReader);

        // Declare & assign an arrayList of usable data from the file
        ArrayList usablePairing = FileUtilities.makePairData(pairingSize);

        //TODO Temporary : Test the data
        ArrayList array = new ArrayList();
        array.add("Stuff 1");
        array.add("Stuff 2");
        array.add("Test 1");
        array.add("Test 2");
        FileUtilities.testCurrentData(pairingSize, array);
        //TODO Temporary: End Test Data
    }

}
