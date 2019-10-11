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
        System.out.print("Please enter the host's port number: ");
        int portNumber = (int)in.nextInt();
        //TODO add validations

        gameClient = new GameClient();
        gameClient.connectToServer(ipadress, portNumber);
        System.out.println("Successfully connected to server");

        boolean play = true;

        while (play) {
            //Receive secret code and confirm reception
            int[] code = (int[])gameClient.readObject();
            gameClient.sendObject(Protocol.RECEIVED);


            board = new MasterMindBoard(code);
            board.playCommandLine();

            Score score = new Score(board.getGuesses(), board.getPlayTime(), this.clientName, board.checkWin());

            gameClient.sendObject(score);
            System.out.println("Waiting for the server...");
            Protocol response = (Protocol) gameClient.readObject();

            GameResults scores = (GameResults) gameClient.readObject();

            System.out.println("\nResults:");
            System.out.println(scores);

            // See if Client wants to play again
            System.out.println("Do you want to play again? [yes/no]");
            String answer = in.nextLine();
            // If Client does not want to play again, quit
            if (answer.equalsIgnoreCase("no")) {
                play = false;
                Protocol quit = Protocol.QUIT;
                gameClient.sendObject(quit);
            }
            // Otherwise, see if host wants to play again
            else {
                Protocol again = Protocol.READY;
                gameClient.sendObject(again);
                System.out.println("Waiting for host...");
                // If host does not want to play again, quit
                if (!((Protocol) gameClient.readObject()).equals(Protocol.READY)) {
                    play = false;
                }
                // Otherwise, both want to play again
            }
        }
        System.out.println("Thank you for playing. Goodbye!");
    }
}
