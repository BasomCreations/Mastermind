package hw01.GUI.onePlayerGame;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PegColor {
    //BLUE(Color.BLUE), GREEN(Color.GREEN), ORANGE(Color.ORANGE), PURPLE(Color.PURPLE), RED(Color.RED), YELLOW(Color.YELLOW);

    final static List<Paint> colors = new ArrayList<Paint>(Arrays.asList(Color.BLUE, Color.GREEN, Color.ORANGE, Color.PURPLE, Color.RED, Color.YELLOW));


    /**
     * Returns the numeric value of the color
     * @param color
     * @return the numeric value of the color or 0 if it is unselected
     */
    public static int getColorNumber(Paint color) {
        return colors.indexOf(color) + 1;
    }

    /**
     * Gets the next color
     * @param cur
     * @return
     */
    public static Paint getNextColor(Paint cur){
        return colors.get(getColorNumber(cur) % colors.size());
    }



}
