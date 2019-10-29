package hw01.GUI.onePlayerGame;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

public enum PegColor {
    BLUE(Color.BLUE), GREEN(Color.GREEN), ORANGE(Color.ORANGE), PURPLE(Color.PURPLE), RED(Color.RED), YELLOW(Color.YELLOW);


    Paint color;

    PegColor(Paint color) {
        this.color = color;
    }

    public int numberValue(){
        return this.ordinal() + 1;
    }

    public Paint getColor() {
        return color;
    }

    public static Paint getNextColor(Paint cur){
        List<Paint> colorList = getColorValues();
        if (!colorList.contains(cur)){
            return PegColor.BLUE.getColor();
        } else {
            int index = colorList.indexOf(cur);
            return PegColor.values()[(index + 1) % PegColor.values().length].getColor();
        }
    }

    public static PegColor getNextPegColor(PegColor color) {
        if (color == null) {
            return PegColor.BLUE;
        } else {
            int ordinal = color.ordinal();
            return PegColor.values()[(ordinal + 1) % PegColor.values().length];
        }
    }

    private static List<Paint> getColorValues(){
        List<Paint> colorList = new ArrayList<Paint>();
        for (PegColor peg:
             PegColor.values()) {
            colorList.add(peg.getColor());
        }
        return colorList;
    }


}
