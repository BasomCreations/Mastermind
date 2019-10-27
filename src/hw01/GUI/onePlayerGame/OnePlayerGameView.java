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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class OnePlayerGameView extends SceneViewTemplate {


    private OnePlayerGameModel model;
    private GridPane board;
    private Button goBackBtn;


    public String TITLE = "Single Player Game";


    public OnePlayerGameView(double w, double h, OnePlayerGameModel model) {

        super(w, h);
        BorderPane root = getRoot();
        getTitle().setText(TITLE);


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


        root.setCenter(board);


    }



}
