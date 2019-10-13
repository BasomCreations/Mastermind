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
import java.net.SocketException;
import java.util.Scanner;

public class TwoPlayerGameServerSide {

    private MasterMindBoard board;
    private int[] secretCode;
    private GameServer server;
    private String hostPlayerName;

    public TwoPlayerGameServerSide(String hostPlayerName) throws IOException {

        this.secretCode = MasterMindBoard.generateRandomSecretCode();
        this.server = new GameServer();
        this.hostPlayerName = hostPlayerName;
    }

    public void playCommandLine() throws Exception {
        System.out.println("Your ip is: " + server.getIp());
        System.out.println("The port is: " + server.getPort());
        System.out.println("Waiting for client to connect...");
        server.connectToClient();

        System.out.println("Connection established successfully");

        boolean play = true;

        try {
            while (play) {
                //Send secret code to client
                server.sendObject(this.getSecretCode());
                Protocol response = (Protocol)server.readObject();
                if (!response.equals(Protocol.RECEIVED)){
                    System.out.println("Something went wrong...");
                    return;
                }

                board = new MasterMindBoard(secretCode);

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

                System.out.println("\nResults:");
                System.out.println(scores);

                // See if Host wants to play again
                System.out.println("Do you want to rematch your opponent? [yes/no]");
                Scanner in = new Scanner(System.in);
                String answer = UsefullFunctions.getValidInput(in, new String[]{"yes", "no"});
                // If Host does not want to play again, quit
                if (answer.equalsIgnoreCase("no")) {
                    server.sendObject(Protocol.QUIT);
                    play = false;
                }
                // Otherwise, make sure Client also wants to play again
                else {
                    System.out.println("Waiting for opponent...");
                    Protocol playAgain = (Protocol) server.readObject();
                    // If Client does not want to play again, quit
                    if (!playAgain.equals(Protocol.READY)) {
                        play = false;
                        System.out.println("Opponent has left the game");
                    }
                    // Otherwise, both want to play again, and generate new code
                    else {
                        this.secretCode = this.board.generateRandomSecretCode();
                        server.sendObject(Protocol.READY);
                    }
                }
            }
        } catch (SocketException e) {
            System.out.println("Opponent has disconnected");;
        }
    }


    public int[] getSecretCode() {
        return secretCode;
    }
}
