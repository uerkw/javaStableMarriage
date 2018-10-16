package LDS_Project1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *  A collection of useful methods for reading and creating data from
 *  the given test file.
 */
class FileUtilities {
    // Use Colors from beginning of Project1.java
    private static String ANSI_RED = Project1.ANSI_RED;
    private static String ANSI_YELLOW = Project1.ANSI_YELLOW;
    private static String ANSI_CYAN = Project1.ANSI_CYAN;
    private static String ANSI_WHITE = Project1.ANSI_WHITE;


    /**
     * Returns a pairing size that is read from the test file. The argument must
     * specify an already created BufferedReader that the method can use to access
     * data in the test file.
     *
     * @param bufferedReader    a BufferedReader to read data in the test file
     * @return                  the size of the pairing given in the test file
     */
    static int assignPairing(BufferedReader bufferedReader){
        //Declare variables to be filled up by the input data
        int pairingSize = 0;

        // Attempt to read from the file, throw errors if file not found
        try{
            pairingSize = (Integer.valueOf(bufferedReader.readLine()));}
        catch (NullPointerException nullExcept) {
            System.out.println(ANSI_YELLOW + "NullPointerException reached in 'assignPairing' ");
        } catch (IOException IOExcept){
            System.out.println(ANSI_YELLOW + "IOException reached: File Not Found");
        }
        return pairingSize;
    }


    /**
     *  Returns a bufferReader that is linked to the given file path, useful for quickly
     *  creating a reader and handling the exceptions associated with it.
     *
     * @param filePath  the relative or absolute path giving the location of the test file
     * @return          the formed bufferedReader of the file
     */
    static BufferedReader assignReader(String filePath){
        BufferedReader bufferedReader = null;
        try {
            FileReader FileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(FileReader);
        } catch (FileNotFoundException fileException){
            System.out.println(ANSI_YELLOW + "Error in file read: File not found.");
            System.out.println(ANSI_RED + "--> System exiting, file cannot be reached");
            System.exit(1);
        }
        return bufferedReader;
    }
    static ArrayList makePairData(int pairingSize){

        //TODO Create method to create usable array from pairing size
        //Use 'pairingSize' var to create two arrays of size 'pairingSize'
            //Array 1 = Senior Programmers
            //Array 2 = Junior Programmers
            //Each element in array is another array, one to hold NAME, other to hold PREFERENCES
        //Use 'For' loop with iterations equal to pairing size to fill each of JR/SR array data
            //Create a subarray for each element. Subarray(0) = String Name, Subarray(1) = int preferences
            //Read preferences, store in Subarray(1) as ints

        //TODO Decide: ArrayLists or Arrays better? Arrays can only hold one type, ArrayLists can hold any.
            // ArrayLists provide more functionality, easier to manipulate

        ArrayList dataArray = null;

        return dataArray;
    }

    /**
     * A test method, made with the idea in mind to quickly print out the pairing size
     * and raw pairing data for debugging purposes. Uses cyan text to differentiate
     * from errors, exceptions, and desired data.
     *
     * @param pairingSize   the desired pairing size for the
     * @param pairingData   arrayList full of data from the input file
     */
    static void testCurrentData(int pairingSize, ArrayList pairingData){
        System.out.println(ANSI_CYAN + "Pairing Size = " + pairingSize);
        System.out.println("Array Data:");
        if (pairingData != null) {
            for(int i = 0 ; i < pairingData.size(); i++){
                System.out.println(ANSI_WHITE + "\t- " + pairingData.get(i));
            }
        }

    }
}
