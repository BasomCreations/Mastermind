/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/26/2019
 * Time: 12:52 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI
 * Class: StartMenuView
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI.startMenu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class StartMenuView {

    final public static int WIDTH = 400;
    final public static int HEIGHT = 650;
    final static int TITLESZE = 50;

    private VBox root;

    public String TITLE = "Mastermind";
    private final Button singleBtn;

    public StartMenuView() throws FileNotFoundException {

        root = new VBox();
        root.setPrefWidth(WIDTH);
        root.setPrefHeight(HEIGHT);


        //root.setPadding(new Insets(15));
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(15);

        Label title = new Label(TITLE);
       // title.setPrefSize(root.getWidth() / 3, root.getHeight() / 10);
        title.setFont(Font.font(null, FontWeight.BOLD, null, TITLESZE));

        Image image = new Image(new FileInputStream("images/icon1.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(HEIGHT / 4);
        imageView.setPreserveRatio(true);

        singleBtn = new StartMenuButton("Single");
        Button configBtn = new StartMenuButton("Settings");


        root.getChildren().addAll(title, imageView, singleBtn, configBtn);

        root.setStyle("-fx-background-color: #4b6a80");

    }

    public VBox getRoot() {
        return root;
    }

    public Button getSingleBtn() {
        return singleBtn;
    }
}
