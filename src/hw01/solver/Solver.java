/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom / Sebastian Ascoli
 * Section: 9am / 11 am
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

import java.util.IntSummaryStatistics;


public abstract class Solver {
    private IntSummaryStatistics stats;


    public Solver() {
        stats = new IntSummaryStatistics();
    }

    public void reset() {
        stats = new IntSummaryStatistics();
    }


    public void simulate(int numSimulations) throws Exception{
        for (int i = 0; i < numSimulations; i++) {
            this.stats.accept((this.play()));
        }
    };

    protected abstract int[] getNextMove();
    protected abstract int play() throws Exception;


    public IntSummaryStatistics getStats() {
        return stats;
    }
}
