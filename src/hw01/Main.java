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
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to MasterMind!");


        System.out.println("Single or 2 player?");
        String answer = in.nextLine();
        if (answer.equals("2")){

            System.out.println("Server? [yes/no]");
            answer = in.nextLine();
            if (answer.equals("yes")) {
                TwoPlayerGameServerSide hostBoard = new TwoPlayerGameServerSide("host player");
                hostBoard.playCommandLine();
            } else {
                TwoPlayerGameClientSide clientBoard = new TwoPlayerGameClientSide("Client Player");
                clientBoard.playCommandLine();
            }



        } else {
            //1 player game
            MasterMindBoard board = new MasterMindBoard();
            board.playCommandLine();

        }

    }
}
