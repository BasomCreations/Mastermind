/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/26/2019
 * Time: 1:16 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI
 * Class: StartMenuButton
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI;

import javafx.scene.control.Button;


public class StartMenuButton extends Button {


    /**
     * Creates a button with the specified text as its label.
     *
     * @param text A text string for its label.
     */
    public StartMenuButton(String text) {
        super(text);
        setStyle("-fx-background-color: #7ec6e6; -fx-font-size: 2em");
        setPrefSize(StartMenuView.WIDTH / 3, StartMenuView.HEIGHT / 8);

    }
}
