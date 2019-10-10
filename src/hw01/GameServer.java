/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/9/2019
 * Time: 4:09 PM
 *
 * Project: csci205_hw
 * Package: hw01
 * Class: GameServer
 *
 * Description:
 *
 * ****************************************
 */
package hw01;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

    private InetAddress ip;
    private int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;


    private ObjectOutputStream objOut;
    private ObjectInputStream objIn;


    public static int DEFAULTPORT = 20000;

    /**
     * Default constructor with default port number
     * @throws IOException
     */
    public GameServer() throws IOException {
        this.ip = InetAddress.getLocalHost();
        this.port = DEFAULTPORT;

        serverSocket = new ServerSocket(port);
    }

    /**
     * COnstructor in which you specify port number
     * @param port port number
     * @throws IOException
     */
    public GameServer(int port) throws IOException {
        this.ip = InetAddress.getLocalHost();
        this.port = port;

        serverSocket = new ServerSocket(port);
    }


    /**
     * Waits until the client requests to connect to server
     * and then creates a client socket, it creates output and input
     * streams writers and readers
     * @throws IOException
     */
    public void connectToClient() throws IOException {
        clientSocket = serverSocket.accept();

        this.objOut = new ObjectOutputStream(clientSocket.getOutputStream());
        this.objIn = new ObjectInputStream(clientSocket.getInputStream());

    }


    public Object readObject() throws IOException, ClassNotFoundException {
        return this.objIn.readObject();
    }

    public void closeClientScoket() throws IOException {
        this.clientSocket.close();
    }

    public void closeServerSocket() throws IOException {
        this.serverSocket.close();
    }


    public InetAddress getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
