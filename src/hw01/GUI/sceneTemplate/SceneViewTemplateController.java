/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/26/2019
 * Time: 7:31 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI.sceneTemplate
 * Class: SceneViewTemplateController
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI.sceneTemplate;


import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class SceneViewTemplateController {

    private SceneViewTemplate theView;
    private Scene prevScene;

    public SceneViewTemplateController(Stage primaryStage, Scene prevScene, SceneViewTemplate view) {
        theView = view;

        prevScene = prevScene;

        Scene finalPrevScene = prevScene;
        view.getGoBackBtn().setOnAction(event -> {
            primaryStage.setScene(finalPrevScene);

        });
    }


}
