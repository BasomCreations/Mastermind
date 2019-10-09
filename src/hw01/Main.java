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

import hw01.MasterMindBoard;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to MasterMind!");

//        MasterMindBoard game = new MasterMindBoard(new int[]{1, 2, 3, 6});
//        //MasterMindBoard game = new MasterMindBoard();
//
//        game.playCommandLine();

        GameServer g = new GameServer(2000);
        System.out.println("Ip is: "+g.getIp());
        g.connectToClient();

        System.out.println("Connected");

        String s = g.readline();
        while (s != null){
            System.out.println(s);
            s = g.readline();
        }

    }
}
