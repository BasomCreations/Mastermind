/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Names: Jonathan Basom and Sebastian Ascoli
 * Section: 9am / 11 am
 * Date: 10/6/2019
 * Time: 5:18 PM
 *
 * Project: csci205_hw
 * Package: main
 * Class: main
 *
 * Description:
 *
 * ****************************************
 */
package hw01;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        playGame();

    }


    /**
     * Method containing all the game logic
     * @throws Exception
     */
    private static void playGame() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to MasterMind!\n");

        System.out.print("Please Enter Your Name: ");
        String name = in.nextLine();


        //After each game the player can either keep playing or terminate the program
        while (true){

            System.out.println("Do you want a single or 2 player game? [1/2]");
            String answer = UsefullFunctions.getValidInput(in, new String[]{"1", "2"});

            //two player game
            if (answer.equals("2")){

                System.out.print("host or client? ");
                answer = UsefullFunctions.getValidInput(in, new String[]{"host", "client"});

                if (answer.equals("host")) {
                    TwoPlayerGameServerSide hostBoard = new TwoPlayerGameServerSide(name);
                    hostBoard.playCommandLine();
                } else {
                    TwoPlayerGameClientSide clientBoard = new TwoPlayerGameClientSide(name);
                    clientBoard.playCommandLine();
                }


            } else {
                // Single player game
                MasterMindBoard board = new MasterMindBoard();
                board.playCommandLine();

            }

            //Check if the player wants to play another game
            System.out.println("\n Do you want to start a new game? [yes/no]");
            answer = UsefullFunctions.getValidInput(in, new String[]{"yes", "no"});
            if (answer.equals("no")){break;}

        }
        System.out.println("Thank you for playing. Goodbye!");
    }


}



