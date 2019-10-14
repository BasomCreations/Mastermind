package hw01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RowTest {


    /**
     * Verify toString method of Row object
     */
    @Test
    void testToString() {
        Row testRow = new Row(2, 1, 1);
        assertEquals("**+-", testRow.toString());

        Row testRow2 = new Row(3,0,1);
        assertEquals("***-", testRow2.toString());

        Row testRow3 = new Row(0, 2, 2);
        assertEquals("++--", testRow3.toString());
    }
}