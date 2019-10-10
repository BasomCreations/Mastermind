/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/10/2019
 * Time: 2:54 PM
 *
 * Project: csci205_hw
 * Package: hw01
 * Class: gameClient
 *
 * Description:
 *
 * ****************************************
 */
package hw01;

import java.io.*;
import java.net.Socket;

public class GameClient {

    Socket clientSocket;
    PrintWriter out;
    BufferedReader in;
    ObjectInputStream objIn;
    ObjectOutputStream objOut;


    public void connectToServer(String ipadress, int port) throws IOException {
        clientSocket = new Socket(ipadress, port);


        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.objIn = new ObjectInputStream(clientSocket.getInputStream());
        this.objOut = new ObjectOutputStream(clientSocket.getOutputStream());
    }


    public Object readObject() throws IOException, ClassNotFoundException {
        return objIn.readObject();
    }


    public void sendScore(Score socre) throws IOException {
        objOut.writeObject(socre);
    }

}
