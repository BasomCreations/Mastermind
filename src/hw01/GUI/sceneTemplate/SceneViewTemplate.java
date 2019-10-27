/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/26/2019
 * Time: 6:11 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI
 * Class: SceneTemplate
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI.sceneTemplate;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


/**
 * Template for generating new scenes
 */
public abstract class SceneViewTemplate {



    private BorderPane root;
    private Label title;
    private HBox menuBar;
    private Button goBackBtn;

    /**
     * Constructor
     * @param w width
     * @param h height
     */
    public SceneViewTemplate(double w, double h) {
        root = new BorderPane();

        //Set dimensions
        root.setPrefWidth(w);
        root.setPrefHeight(h);


        //Go Back Button
        goBackBtn = new Button();
        Image image = new Image("file:images/goBackArrow.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(10);
        imageView.setPreserveRatio(true);
        goBackBtn.setGraphic(imageView);
        goBackBtn.setStyle("-fx-background-color: #264cff");

        //Title
        title = new Label();
        title.setStyle("-fx-font-size: 15");

        //Put menu bar ar the top
        menuBar = new HBox();
        menuBar.setSpacing(30);
        menuBar.setStyle("-fx-background-color: #229fff");
        menuBar.setAlignment(Pos.TOP_LEFT);
        menuBar.getChildren().addAll(goBackBtn, title);

        root.setTop(menuBar);
    }


    public BorderPane getRoot() {
        return root;
    }

    public Label getTitle() {
        return title;
    }

    public HBox getMenuBar() {
        return menuBar;
    }

    public Button getGoBackBtn() {
        return goBackBtn;
    }
}
