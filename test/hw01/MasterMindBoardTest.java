package hw01;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MasterMindBoardTest {

    MasterMindBoard board;

    @BeforeEach
    void setUp() {
        int[] code = {1, 3, 3, 5};
        board = new MasterMindBoard(code);

    }


    /**
     * Test guess method
     */
    @Test
    void guessTest() throws Exception {
        Row guessResult = board.guess(new int[]{1, 1, 1, 1});
        assertEquals("*---", guessResult.toString());

        guessResult = board.guess(new int[]{3, 1, 5, 4});
        assertEquals("+++-", guessResult.toString());

        guessResult = board.guess(new int[]{3, 3, 2, 2});
        assertEquals("*+--", guessResult.toString());

        guessResult = board.guess(new int[]{2, 2, 2, 2});
        assertEquals("----", guessResult.toString());

        guessResult = board.guess(new int[]{1, 3, 5, 3});
        assertEquals("**++", guessResult.toString());

        guessResult = board.guess(new int[]{3, 3, 0, 3});
        assertEquals("*+--", guessResult.toString());

    }


    /**
     * Test Constructor Exception
     */
    @Test
    void testConstructorException(){

        assertThrows(IllegalArgumentException.class, () -> new MasterMindBoard(new int[]{3, 3, 4}));
        assertThrows(IllegalArgumentException.class, () -> new MasterMindBoard(new int[]{3, 3, 4, 5, 6, 6}));
        assertThrows(IllegalArgumentException.class, () -> new MasterMindBoard(new int[]{7, 7, 7, 7}));
    }


}