/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/26/2019
 * Time: 8:53 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI.settings
 * Class: SettingsView
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI.settings;

import hw01.GUI.sceneTemplate.SceneViewTemplate;
import hw01.game.MasterMindBoard;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SettingsView extends SceneViewTemplate {

    private final VBox settingsChoicesVbox;
    private final ComboBox<String> comboBoxMaxRows;
    private final ComboBox<String> comboBoxRowSize;
    private final Button applyBtn;

    /**
     * Constructor
     *
     * @param w width
     * @param h height
     */
    public SettingsView(double w, double h) {
        super(w, h);
        getTitle().setText("Settings");
        BorderPane root = getRoot();

        //Create vbox
        settingsChoicesVbox = new VBox();
        settingsChoicesVbox.setSpacing(15);
        settingsChoicesVbox.setStyle("-fx-background-color: #4b6a80");
        settingsChoicesVbox.setAlignment(Pos.TOP_CENTER);

        //settings icon
        Image image = null;
        try {
            image = new Image(new FileInputStream("images/settingsIcon.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(h / 4);
        imageView.setPreserveRatio(true);


        //Choose max rows
        Label maxRowsLabel = new Label("Max number of rows:");
        maxRowsLabel.setStyle("-fx-font-size: 15");
        List<String> numberOfRowsOptions = new ArrayList<>();
        for (int i = 5; i <= 15; i++) {
            numberOfRowsOptions.add(Integer.toString(i));
        }
        comboBoxMaxRows = new ComboBox<>(FXCollections.observableArrayList(numberOfRowsOptions));
        comboBoxMaxRows.setValue(Integer.toString(MasterMindBoard.DEFAULT_MAXIMUM_ATTEMPTS));
        HBox hboxMaxRows = new HBox();
        hboxMaxRows.getChildren().addAll(maxRowsLabel, comboBoxMaxRows);
        hboxMaxRows.setSpacing(10);
        hboxMaxRows.setAlignment(Pos.CENTER);

        //Choose Row size
        Label rowSizeLabel = new Label("Row size:");
        rowSizeLabel.setStyle("-fx-font-size: 15");
        List<String> rowSizeOptions = new ArrayList<>();
        for (int i = 3; i <= 8; i++) {
            rowSizeOptions.add(Integer.toString(i));
        }
        comboBoxRowSize = new ComboBox<>(FXCollections.observableArrayList(rowSizeOptions));
        comboBoxRowSize.setValue(Integer.toString(MasterMindBoard.ROW_SIZE));
        HBox hboxRowSize = new HBox();
        hboxRowSize.getChildren().addAll(rowSizeLabel, comboBoxRowSize);
        hboxRowSize.setSpacing(10);
        hboxRowSize.setAlignment(Pos.CENTER);

        //Apply Button
        applyBtn = new Button("Apply");
        applyBtn.setStyle("-fx-background-color: #248054; -fx-font-size: 2em");


        settingsChoicesVbox.getChildren().addAll(imageView, hboxMaxRows, hboxRowSize, applyBtn);


        root.setCenter(settingsChoicesVbox);



    }

    public Button getApplyBtn() {
        return applyBtn;
    }

    public ComboBox<String> getComboBoxMaxRows() {
        return comboBoxMaxRows;
    }

    public ComboBox<String> getComboBoxRowSize() {
        return comboBoxRowSize;
    }
}
