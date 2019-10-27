package hw01.GUI.onePlayerGame;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
}
