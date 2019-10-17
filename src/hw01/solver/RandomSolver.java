/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 10/17/2019
 * Time: 1:51 PM
 *
 * Project: csci205_hw
 * Package: hw01.solver
 * Class: RandomSolver
 *
 * Description:
 *
 * ****************************************
 */
package hw01.solver;

import hw01.game.MasterMindBoard;
import hw01.game.MasterMindUtility;

import java.util.Random;

public class RandomSolver extends Solver {

    public RandomSolver() {
        super();
    }

    @Override
    protected int[] getNextMove() {
        return MasterMindUtility.generateRandomSecretCode();
    }

    @Override
    protected int play() throws Exception {
        MasterMindBoard board = new MasterMindBoard(true);
        while (!board.checkWin()) {
            board.guess(this.getNextMove());
        }
        return board.getGuesses();
    }

    @Override
    public void simulate(int numSimulations) throws Exception {
        for (int i = 0; i < numSimulations; i++) {
            this.addStat(this.play());
        }

    }


}
