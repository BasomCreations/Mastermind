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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;


public class OnePlayerGameView extends SceneViewTemplate {


    private OnePlayerGameModel model;
    private GridPane board;
    private PegSphere[][] pegGrid;
    private Circle[][] resultsGrid;
    private Polygon[] arrows;
    private Button[] buttons;

    private Alert errorMsg;

    public final static String BGCOLOR = "#4b6a80";
    public final static int GRIDSQUARESIZE = 30;


    public String TITLE = "Single Player Game";
    private Button resetBtn;
    private VBox resultsVBox;
    private Label resultsLbl;
    private HBox correctAnswerBox;


    public OnePlayerGameView(double w, double h, OnePlayerGameModel model) {

        super(w, h);
        BorderPane root = getRoot();
        getTitle().setText(TITLE);

        initializeErrorMsg();

        createBoard(root, MasterMindBoard.DEFAULT_MAXIMUM_ATTEMPTS, MasterMindBoard.ROW_SIZE);
        initializeResetBtn();
        initializeResultsLbl(root);

    }

    public OnePlayerGameView(double w, double h, OnePlayerGameModel model, int boardRows, int numGuesses) {
        super(w, h);
        BorderPane root = getRoot();
        getTitle().setText(TITLE);

        initializeErrorMsg();

        createBoard(root, boardRows, numGuesses);
        initializeResetBtn();
        initializeResultsLbl(root);

    }

    private void initializeErrorMsg() {
        errorMsg = new Alert(Alert.AlertType.ERROR);
    }

    private void initializeResultsLbl(BorderPane root) {
        // Add results label
        resultsLbl = new Label();
        resultsLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        resultsLbl.setTextFill(Color.SILVER);
        resultsLbl.setTextAlignment(TextAlignment.CENTER);
        resultsLbl.setWrapText(true);
        resultsLbl.setVisible(false);


        //Add correct code
        correctAnswerBox = new HBox();
        correctAnswerBox.setAlignment(Pos.CENTER);
        correctAnswerBox.setVisible(false);


        // Add VBox to bottom for results
        resultsVBox = new VBox();
        resultsVBox.setAlignment(Pos.CENTER);
        resultsVBox.setStyle("-fx-background-color: " + BGCOLOR);
        resultsVBox.getChildren().addAll(resultsLbl, correctAnswerBox);

        root.setBottom(resultsVBox);
    }

    private void initializeResetBtn() {
        // Add reset button
        resetBtn = new Button("Restart");
        super.getMenuBar().getChildren().add(resetBtn);
    }

    /**
     * Creates the MasterMind Board
     * @param root
     */
    protected void createBoard(BorderPane root, int rows, int guesses) {

        board = new GridPane();
        board.setStyle("-fx-background-color:" + BGCOLOR);
        board.setAlignment(Pos.CENTER);

        pegGrid = new PegSphere[rows][guesses];
        resultsGrid = new Circle[rows][guesses];
        buttons = new Button[rows];

        for (int y = 0; y < rows; y++) {

            buttons[y] = new Button("Guess");
            buttons[y].setVisible(false);
            buttons[y].setStyle("-fx-background-color: tan;");
            board.add(buttons[y], 0, y);
            for (int x = 0; x < guesses; x++) {

                StackPane stack = new StackPane();
                stack.setAlignment(Pos.CENTER);
                stack.getChildren().add(new Rectangle(GRIDSQUARESIZE,GRIDSQUARESIZE, Color.GRAY));


                PegSphere curPeg = new PegSphere( GRIDSQUARESIZE / 3);
                stack.getChildren().add(curPeg);

                board.add(stack,x+1,y);
                pegGrid[y][x] = curPeg;

            }

            // Add results for each row
            for (int i = 1; i <= guesses; i++) {
                Circle circle = new Circle(GRIDSQUARESIZE / 10, Color.web(BGCOLOR));
                board.add(circle, rows+i, y);

                resultsGrid[y][i - 1] = circle;
            }
        }

        // Set first button to visible
        buttons[0].setVisible(true);

        board.setAlignment(Pos.CENTER);

        root.setCenter(board);
    }

    public GridPane getBoard() {
        return board;
    }

    public PegSphere[][] getPegGrid() {
        return pegGrid;
    }

    public Circle[][] getResultsGrid() {
        return resultsGrid;
    }

    public Button[] getButtons() {
        return buttons;
    }

    public Alert getErrorMsg() {
        return errorMsg;
    }

    public Button getResetBtn() {
        return resetBtn;
    }

    public Label getResultsLbl() {
        return resultsLbl;
    }

    public VBox getResultsVBox() {
        return resultsVBox;
    }

    public HBox getCorrectAnswerBox() {
        return correctAnswerBox;
    }

}
