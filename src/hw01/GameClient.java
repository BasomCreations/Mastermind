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
    ObjectOutputStream objOut;
    ObjectInputStream objIn;



    public void connectToServer(String ipadress, int port) throws IOException {
        clientSocket = new Socket(ipadress, port);

        this.objOut = new ObjectOutputStream(clientSocket.getOutputStream());
        this.objIn = new ObjectInputStream(clientSocket.getInputStream());

    }


    public Object readObject() throws IOException, ClassNotFoundException {
        return objIn.readObject();
    }


    public void sendObject(Object o) throws IOException {
        objOut.writeObject(o);
    }


    public void close() throws IOException {
        clientSocket.close();
    }

}
