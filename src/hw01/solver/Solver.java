/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 10/17/2019
 * Time: 1:45 PM
 *
 * Project: csci205_hw
 * Package: hw01.solver
 * Class: Solver
 *
 * Description:
 *
 * ****************************************
 */
package hw01.solver;

import hw01.game.MasterMindBoard;
import hw01.game.MasterMindBoardException;

import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;

public abstract class Solver {
    private IntSummaryStatistics stats;


    public Solver() {
        stats = new IntSummaryStatistics();
    }

    public void reset() {
        stats = new IntSummaryStatistics();
    }

    protected void addStat(int turns) {
        stats.accept(turns);
    }

    protected abstract int[] getNextMove();
    protected abstract int play() throws Exception;
    public abstract void simulate(int numSimulations) throws Exception;

    public IntSummaryStatistics getStats() {
        return stats;
    }
}
