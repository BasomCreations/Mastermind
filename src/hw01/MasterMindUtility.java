/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/11/2019
 * Time: 11:42 AM
 *
 * Project: csci205_hw
 * Package: hw01
 * Class: UsefullFunctions
 *
 * Description:
 *
 * ****************************************
 */
package hw01;

import java.util.Random;
import java.util.Scanner;

/**
 * Utility class for useful functions
 */
public final class MasterMindUtility {

    /**
     * Repeatedly asks a user to enter an input until the input is valid
     * @param in scanner object
     * @param inputOptions string array wih valid input options
     * @return
     */
    public static String getValidInput(Scanner in, String[] inputOptions){
        while (true) {
            String inp = in.nextLine().trim();
            for (String s :
                    inputOptions) {
                if (s.equals(inp)) {
                    return s;
                }
            }
            System.out.print("Error: Please enter valid input: ");
        }
    }

    /**
     * Repeatedly asks the user to enter an input until it is numeric and integer
     * @param in scanner
     * @return numeric input
     */
    public static int getIntegerInput(Scanner in){
        while (true){
            String inp = in.nextLine();
            try{
                int intInput = Integer.parseInt(inp);
                return intInput;
            } catch (Exception e){
                System.out.print("Error: Please enter numeric input: ");
            }
        }
    }

    /**
     * Converts guess string (which should be already validated) into an array that the guess method
     * can take as a parameter
     * @param input string with user's guess
     * @return array of integers the guess method can take as parameter
     */
    static int[] convertStrToArray(String input) {
        int[] intArray = new int[MasterMindBoard.ROW_SIZE];
        for (int i = 0; i < input.length(); i++) {

            char c = input.charAt(i);
            intArray[i] = Integer.parseInt(String.valueOf(c));
        }

        return intArray;
    }

    /**
     * Checks if the guessed code string is in the right format
     * @param input input string
     * @return boolean
     */
    static boolean isValidInput(String input) {
        if (input.length() != MasterMindBoard.ROW_SIZE) {
            return false;
        }
        String pattern = String.format("[%d-%d]{%d}", MasterMindBoard.MIN_SLOT_VALUE, MasterMindBoard.MAX_SLOT_VALUE, MasterMindBoard.ROW_SIZE);
        return input.matches(pattern);
    }

    /**
     * Check if secret code provided in the constructor is valid
     * @param secretCode secret code array of integers
     * @return boolean stating weather code is valid or not
     */
    static boolean isSecretCodeValid(int[] secretCode){
        if (secretCode.length != MasterMindBoard.ROW_SIZE) {return false;}
        for (int digit:
                secretCode) {
            if (digit < MasterMindBoard.MIN_SLOT_VALUE || digit > MasterMindBoard.MAX_SLOT_VALUE) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generate random secret code
     */
    public static int[] generateRandomSecretCode() {

        int[] secretCode = new int[MasterMindBoard.ROW_SIZE];

        Random rand = new Random();
        for (int i = 0; i < secretCode.length; i++) {
            secretCode[i] = rand.nextInt(MasterMindBoard.MAX_SLOT_VALUE) + MasterMindBoard.MIN_SLOT_VALUE;
        }
        return secretCode;
    }
}
