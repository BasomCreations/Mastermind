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
package hw01.GUI.onePlayerGame;


import hw01.GUI.sceneTemplate.SceneViewTemplate;
import hw01.game.MasterMindBoard;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;


public class OnePlayerGameView extends SceneViewTemplate {


    private OnePlayerGameModel model;
    private GridPane board;
    private Button goBackBtn;
    private Circle[][] pegGrid;
    private Circle[][] resultsGrid;
    private Polygon[] arrows;

    private static String BGCOLOR = "#4b6a80";


    public String TITLE = "Single Player Game";


    public OnePlayerGameView(double w, double h, OnePlayerGameModel model) {

        super(w, h);
        BorderPane root = getRoot();
        getTitle().setText(TITLE);


        board = new GridPane();
        //board.setHgap(10);
        //board.setVgap(10);
        board.setStyle("-fx-background-color:" + BGCOLOR);
        board.setAlignment(Pos.CENTER);

        pegGrid = new Circle[MasterMindBoard.DEFAULT_MAXIMUM_ATTEMPTS][MasterMindBoard.ROW_SIZE];
        arrows = new Polygon[MasterMindBoard.DEFAULT_MAXIMUM_ATTEMPTS];
        resultsGrid = new Circle[MasterMindBoard.DEFAULT_MAXIMUM_ATTEMPTS][MasterMindBoard.ROW_SIZE];

        for (int y = 0; y < MasterMindBoard.DEFAULT_MAXIMUM_ATTEMPTS; y++) {
            Polygon triangle = new Polygon();
            triangle.getPoints().addAll(new Double[] {
                    0.0, 10.0, 0.0, 20.0, 15.0, 15.0
            });
            arrows[y] = triangle;
            board.add(triangle, 0, y);
            for (int x = 0; x < MasterMindBoard.ROW_SIZE; x++) {

                StackPane stack = new StackPane();
                stack.setAlignment(Pos.CENTER);
                stack.getChildren().add(new Rectangle(30,30, Color.GRAY));
                Circle circle = new Circle(10, Color.WHITE);
                stack.getChildren().add(circle);
                board.add(stack,x+1,y);
                pegGrid[y][x] = circle;

            }

            // Add results for each row
            for (int i = 1; i <= MasterMindBoard.ROW_SIZE; i++) {
                Circle circle = new Circle(3, Color.web("#ffffff"));
                board.add(circle, MasterMindBoard.ROW_SIZE+i, y);

                resultsGrid[y][i - 1] = circle;
            }
        }



        board.setAlignment(Pos.CENTER);


        root.setCenter(board);


    }

    public GridPane getBoard() {
        return board;
    }

    public Circle[][] getPegGrid() {
        return pegGrid;
    }

    public Circle[][] getResultsGrid() {
        return resultsGrid;
    }

    public Polygon[] getArrows() {
        return arrows;
    }
}
