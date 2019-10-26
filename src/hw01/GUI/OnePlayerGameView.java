/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/26/2019
 * Time: 3:10 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI
 * Class: OnePlayerGameView
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI;

import hw01.game.MasterMindBoard;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OnePlayerGameView {


    final public static int WIDTH = StartMenuView.WIDTH;
    final public static int HEIGHT = StartMenuView.HEIGHT;
    final static int TITLESZE = 50;

    private BorderPane root;
    private OnePlayerGameModel model;
    private GridPane board;

    public String TITLE = "Single Player Game";


    public OnePlayerGameView(double w, double h, OnePlayerGameModel model) {

        root = new BorderPane();
        root.setPrefWidth(w);
        root.setPrefHeight(h);

        Label title = new Label(TITLE);

        board = new GridPane();
        board.setHgap(10);
        board.setVgap(10);
        board.setStyle("-fx-background-color: #4b6a80");

        for (int y = 0; y < MasterMindBoard.DEFAULT_MAXIMUM_ATTEMPTS; y++) {
            for (int x = 0; x < MasterMindBoard.ROW_SIZE; x++) {
                board.add(new Circle(15, Color.GRAY), x, y);

            }
        }


        board.setAlignment(Pos.CENTER);


        root.setLeft(new Separator(Orientation.VERTICAL));
        root.setRight(new Separator(Orientation.VERTICAL));
        root.setBottom(new Separator(Orientation.HORIZONTAL));
        root.setCenter(board);

        root.setTop(title);

    }

    public BorderPane getRoot() {
        return root;
    }
}
