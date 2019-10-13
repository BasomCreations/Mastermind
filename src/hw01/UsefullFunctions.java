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

import java.util.Scanner;

/**
 * Utility class for useful functions
 */
public final class UsefullFunctions {

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
}
