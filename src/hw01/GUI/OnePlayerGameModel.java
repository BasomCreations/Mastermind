/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/26/2019
 * Time: 4:14 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI
 * Class: OnePlayerGameModel
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI;

import hw01.game.MasterMindBoard;

public class OnePlayerGameModel {

    private MasterMindBoard board;

    public OnePlayerGameModel() {

        board = new MasterMindBoard();


    }


    public MasterMindBoard getBoard() {
        return board;
    }
}
