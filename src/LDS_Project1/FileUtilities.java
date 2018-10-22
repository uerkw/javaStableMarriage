package LDS_Project1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Kyle Uerkwitz
 * @version 1.0
 * Originally written for the EECS 2500 course at the University of Toledo
 *
 *  A collection of useful methods for reading and creating data from
 *  the given test file.
 */
class FileUtilities {
    // Use Colors from beginning of Project1.java
    private static final String ANSI_RED = Project1.ANSI_RED;
    private static final String ANSI_YELLOW = Project1.ANSI_YELLOW;
    private static final String ANSI_CYAN = Project1.ANSI_CYAN;
    private static final String ANSI_WHITE = Project1.ANSI_WHITE;


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
     * @return          the formed bufferedReader linked to the file
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

    /**
     *  Creates pairing data by reading the given file to parse the information into a usable format.
     *  Modifying this method will allow the use of other input formats
     *
     * @param reader        a created bufferedReader so the method can start after reading the pairing
     *                      size, and knows what file to read from
     * @param pairingSize   an integer representing the pairingSize desired. Changing pairing size allows
     *                      larger data sets to be used
     * @return              an array of type 'individualData'. This lets all of the raw data be passed back to
     *                      the origin of the method call.
     * @throws IOException  If the reader cannot access the input file
     */
    static individualData[] makeRawData(BufferedReader reader, int pairingSize) throws IOException{
        individualData[] dataArray = new individualData[pairingSize*2];
        for(int i = 0; i < (pairingSize*2); i++){
           String tempName = reader.readLine();
           String preferences = reader.readLine();
           String[] splitPrefs = preferences.split("\t");
           int[] intPrefs = new int[pairingSize];
           for(int j = 0; j < splitPrefs.length; j++)
               intPrefs[j] = Integer.parseInt(splitPrefs[j]);
           individualData temporaryData = new individualData(tempName, intPrefs);
           dataArray[i] = temporaryData;
        }

        return dataArray;
    }

    /**
     * A test method, made with the idea in mind to quickly print out the pairing size
     * and raw pairing data for debugging purposes. Uses cyan text to differentiate
     * from errors, exceptions, and desired data.
     *
     * @param pairingSize   the desired pairing size for the file
     * @param pairingData   array full of data from the input file
     */
    static void testCurrentData(int pairingSize, ArrayList<individualData> pairingData){
        System.out.println(ANSI_CYAN + "Pairing Size = " + pairingSize);
        System.out.println("Array Data:");
        int dataSize = pairingData.size();
        if (pairingData != null) {
            for(int i = 0; i < dataSize; i++){
                System.out.println(ANSI_WHITE + (pairingData.get(i).getName()));
                for(int j = 0; j < pairingSize; j++ ) {
                    System.out.print(ANSI_WHITE + (pairingData.get(i).getPreferences())[j] + " ");
                }
                System.out.println("");
            }
        }
    }
    /**
     * Deriving from testCurrentData, this method provides a way to easily print the final data given in one ArrayList.
     * Uses cyan text to differentiate from errors, exceptions, and desired data.
     *
     * @param pairingSize   the desired pairing size for the file
     * @param pairingData   array composing of two ArrayLists (one for each group) holding the member's data.
     */
    static void printFinalData(int pairingSize, ArrayList<ArrayList<individualData>> pairingData){
        System.out.println();

        System.out.println(ANSI_CYAN+ "Completed Pairing Data: ");
        int dataSize = pairingData.size();
        if (pairingData != null) {
            for(int i = 0; i < dataSize; i++){
                ArrayList<individualData> pair = pairingData.get(i);
                individualData person1 = pair.get(1);
                individualData person2= pair.get(0);
                System.out.println(ANSI_WHITE + "Team " + (i+1) + ": " + (person1.getName()) +
                    " and " + person2.getName());

                    }
            }
    }
}
