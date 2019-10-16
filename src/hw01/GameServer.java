/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom and Sebastian Ascoli
 * Section: 9 am / 11 am
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

/**
 * Class to manage socket on the server side
 */
public class GameServer {

    /**
     * Ip address
     */
    private InetAddress ip;

    /**
     * port
     */
    private int port;

    /**
     * Server socket
     */
    private ServerSocket serverSocket;

    /**
     * Client socket
     */
    private Socket clientSocket;


    /**
     * Object output stream
     */
    private ObjectOutputStream objOut;

    /**
     * Object input stream
     */
    private ObjectInputStream objIn;


    /**
     * Default port
     */
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


    /**
     * Reads oject sent from client
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object readObject() throws IOException, ClassNotFoundException {
        return this.objIn.readObject();
    }

    /**
     * Sends object to server
     * @param o
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void sendObject(Object o) throws IOException, ClassNotFoundException {
        this.objOut.writeObject(o);
    }

    /**
     * Closes connection with client
     * @throws IOException
     */
    public void closeClientSocket() throws IOException {
        this.clientSocket.close();
    }

    /**
     * Closes server socket
     * @throws IOException
     */
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
