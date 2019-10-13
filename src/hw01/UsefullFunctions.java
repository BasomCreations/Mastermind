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
            System.out.println("Error: Please enter valid input");
        }
    }
}
