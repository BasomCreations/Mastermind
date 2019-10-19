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


import hw01.game.MasterMindBoard;
import hw01.game.MasterMindUtility;
import hw01.game.TwoPlayerGameClientSide;
import hw01.game.TwoPlayerGameServerSide;
import hw01.solver.MinimaxSolver;
import hw01.solver.RandomSolver;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to MasterMind!\n");

        try{
            playGame();
        }
        catch (Exception e) {
            System.out.println("Unexpected Error Occurred - Try Again");
        }

    }


    /**
     * Method containing all the game logic
     * @throws Exception
     * @author Jonathan
     * @author Sebastian
     */
    private static void playGame() throws Exception {
        Scanner in = new Scanner(System.in);


        System.out.print("Please Enter Your Name: ");
        String name = in.nextLine();


        //After each game the player can either keep playing or terminate the program
        while (true){

            System.out.println("Do you want to play single player, multi-player, or enter solver mode? [1/2/solver]");
            String answer = MasterMindUtility.getValidInput(in, new String[]{"1", "2", "solver"});

            //two player game
            if (answer.equals("2")){

                System.out.print("Are you hosting the game or joining? [host/join]");
                answer = MasterMindUtility.getValidInput(in, new String[]{"host", "join"});

                if (answer.equals("host")) {
                    TwoPlayerGameServerSide hostBoard = new TwoPlayerGameServerSide(name);
                    hostBoard.playCommandLine();
                } else {
                    TwoPlayerGameClientSide clientBoard = new TwoPlayerGameClientSide(name);
                    clientBoard.playCommandLine();
                }


            } else if (answer.equals("1")){
                // Single player game
                MasterMindBoard board = new MasterMindBoard();
                board.playCommandLine();

            } else {
                // Solver mode
                playSolverMode();
            }

            //Check if the player wants to play another game
            System.out.println("\nDo you want to start a new game? [yes/no]");
            answer = MasterMindUtility.getValidInput(in, new String[]{"yes", "no"});
            if (answer.equals("no")){break;}

        }
        System.out.println("Thank you for playing. Goodbye!");
    }

    /**
     * Method to determine how to what solver mode to play
     * @throws Exception
     * @author Jonathan
     * @author Sebastian
     */
    private static void playSolverMode() throws Exception {
        System.out.println("What type of solver would you like to use? [random/minimax/surprise]");
        Scanner in = new Scanner(System.in);
        String response = MasterMindUtility.getValidInput(in, new String[] {"random", "minimax"});
        if (response.equals("random")) {
            RandomSolver randSolver = new RandomSolver();
            System.out.println("How many games would you like to simulate?");
            int iterations = MasterMindUtility.verifyNumericInput(in);
            randSolver.simulate(iterations);
            System.out.println(randSolver.getStats().toString());
        } else if (response.equals("minimax")){
            MinimaxSolver solver = new MinimaxSolver();
            System.out.println("How many games would you like to simulate?");
            int iterations = MasterMindUtility.verifyNumericInput(in);
            solver.simulate(iterations);
            System.out.println(solver.getStats().toString());

        }
    }


}



