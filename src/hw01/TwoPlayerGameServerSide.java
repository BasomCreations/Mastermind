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


        //Send secret code to client
        server.sendObject(this.getSecretCode());
        Protocol response = (Protocol)server.readObject();
        if (!response.equals(Protocol.RECEIVED)){
            System.out.println("Something went wrong...");
            return;
        }


        board.playCommandLine();
        Score score = new Score(board.getGuesses(), board.getPlayTime(), this.hostPlayerName, board.checkWin());

        System.out.println("\nWaiting for other player...");
        Score clientScore = (Score) server.readObject();
        server.sendObject(Protocol.RECEIVED);


        GameResults scores = new GameResults();
        scores.addScore(score);
        scores.addScore(clientScore);

        scores.sortByMoves();

        server.sendObject(scores);

        System.out.println("The results are:");
        System.out.println(scores);




    }


    public int[] getSecretCode() {
        return secretCode;
    }
}
