/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/28/2019
 * Time: 11:28 AM
 *
 * Project: csci205_hw
 * Package: hw01.GUI.onePlayerGame
 * Class: Peg
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI.onePlayerGame;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class PegSphere extends Sphere {

    private Paint color;


    /**
     * Creates a new instance of {@code Sphere} with the given radius.
     * The resolution defaults to 64 divisions along the sphere's axes.
     *
     * @param radius Radius
     */
    public PegSphere(double radius) {
        super(radius);
        color = Color.WHITE;
    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor((Color)color);
        material.setSpecularColor((Color)color);
        this.color = color;
        this.setMaterial(material);

    }


}
