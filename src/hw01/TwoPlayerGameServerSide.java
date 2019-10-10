/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/10/2019
 * Time: 1:12 PM
 *
 * Project: csci205_hw
 * Package: hw01
 * Class: twoPlayerGame
 *
 * Description:
 *
 * ****************************************
 */
package hw01;

import java.io.IOException;

public class TwoPlayerGameServerSide {

    private MasterMindBoard board;
    private int[] secretCode;
    private GameServer server;
    private String hostPlayerName;

    public TwoPlayerGameServerSide(String hostPlayerName) throws IOException {

        this.secretCode = MasterMindBoard.generateRandomSecretCode();
        this.board = new MasterMindBoard(secretCode);
        this.server = new GameServer();
        this.hostPlayerName = hostPlayerName;
    }

    public void playCommandLine() throws Exception {
        System.out.println("Your ip is: " + server.getIp());
        System.out.println("The port is: " + server.getPort());
        System.out.println("Waiting for client to connect...");
        server.connectToClient();

        System.out.println("Connection established successfully");
        board.playCommandLine();
        Score score = new Score(board.getGuesses(), board.getPlayTime(), this.hostPlayerName, board.checkWin());

        System.out.println("\nWaiting for other player...");
        Score clientScore = (Score) server.readObject();
        if (clientScore  == null){
            System.out.println("Error: client disconnected");
        }
        System.out.println(clientScore);




    }


    public int[] getSecretCode() {
        return secretCode;
    }
}
