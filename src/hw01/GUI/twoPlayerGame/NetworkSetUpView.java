/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/29/2019
 * Time: 7:56 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI.twoPlayerGame
 * Class: NetworkSetUpView
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI.twoPlayerGame;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class NetworkSetUpView {
    private VBox root;
    private TextField nameInputField;
    private Button hostBtn;
    private Button joinBtn;
    private TextField ipInputField;
    private TextField portInputField;
    private Label ipLabel;
    private Label portLabel;
    private Button okButton;

    private SimpleBooleanProperty hostModeProperty;
    private SimpleBooleanProperty joinModeProperty;


    public NetworkSetUpView() {
        hostModeProperty = new SimpleBooleanProperty(false);
        joinModeProperty = new SimpleBooleanProperty(false);

        this.root = new VBox();
        root.setPrefWidth(250);
        root.setPrefHeight(200);
        root.setSpacing(15);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.TOP_CENTER);
        HBox nameHBox = new HBox();

        nameInputField = new TextField();
        nameInputField.disableProperty().bind(hostModeProperty.or(joinModeProperty));
        nameHBox.setSpacing(10);
        nameHBox.getChildren().addAll(new Label("Your name:") ,nameInputField);


        hostBtn = new Button("Host");
        joinBtn = new Button("Join");


        HBox btnHbox = new HBox();
        btnHbox.setSpacing(20);
        btnHbox.getChildren().addAll(hostBtn, joinBtn);


        HBox ipHbox = new HBox();
        ipHbox.setSpacing(10);
        ipInputField = new TextField();
        ipInputField.visibleProperty().bind(hostModeProperty);
        ipLabel = new Label("IP:");
        ipLabel.visibleProperty().bind(hostModeProperty.or(joinModeProperty));
        ipHbox.getChildren().addAll(ipLabel,ipInputField);

        HBox portHbox = new HBox();
        portHbox.setSpacing(10);
        portInputField = new TextField();
        portInputField.visibleProperty().bind(hostModeProperty);
        portLabel = new Label("Port:");
        portLabel.visibleProperty().bind(hostModeProperty.or(joinModeProperty));
        portHbox.getChildren().addAll(portLabel,portInputField);

        okButton = new Button("OK");
        okButton.visibleProperty().bind(hostModeProperty.or(joinModeProperty));

        root.getChildren().addAll(nameHBox, btnHbox, ipHbox, portHbox, okButton);
    }

    public VBox getRoot() {
        return root;
    }

    public TextField getNameInputField() {
        return nameInputField;
    }

    public Button getHostBtn() {
        return hostBtn;
    }

    public Button getJoinBtn() {
        return joinBtn;
    }


    public TextField getIpInputField() {
        return ipInputField;
    }

    public TextField getPortInputField() {
        return portInputField;
    }

    public Label getIpLabel() {
        return ipLabel;
    }

    public Label getPortLabel() {
        return portLabel;
    }

    public Button getOkButton() {
        return okButton;
    }

    public void setHostModeProperty(boolean hostModeProperty) {
        this.hostModeProperty.set(hostModeProperty);
    }


    public void setJoinModeProperty(boolean joinModeProperty) {
        this.joinModeProperty.set(joinModeProperty);
    }
}
