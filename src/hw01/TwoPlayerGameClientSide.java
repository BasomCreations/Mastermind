/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/10/2019
 * Time: 2:48 PM
 *
 * Project: csci205_hw
 * Package: hw01
 * Class: TwoPlayerGameClientSide
 *
 * Description:
 *
 * ****************************************
 */
package hw01;

import java.io.IOException;
import java.util.Scanner;

public class TwoPlayerGameClientSide {

    private String clientName;
    private GameClient gameClient;
    private MasterMindBoard board;

    public TwoPlayerGameClientSide(String playername) {
        this.clientName = playername;
    }

    void playCommandLine() throws Exception {
        Scanner in = new Scanner(System.in);

        System.out.print("Please enter the host's ip address: ");
        String ipadress = in.nextLine();
        System.out.println("Please enter the host's port number: ");
        int portNumber = (int)in.nextInt();
        //TODO add validations

        gameClient = new GameClient();
        gameClient.connectToServer(ipadress, portNumber);

        System.out.println("Successfully connected to server");

        board = new MasterMindBoard();
        board.playCommandLine();

        Score score = new Score(board.getGuesses(), board.getPlayTime(), this.clientName, board.checkWin());

        gameClient.sendScore(score);

        System.out.println("Score sent");


    }
}
