/*
 * MIT License
 *
 * Copyright (c) 2016 Julian Dobrot
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package ourWindow.zusatzaufgabeGui.windows.lightWindows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lights.SpotLight;
import ourWindow.zusatzaufgabeGui.windows.NumberField;
import ourWindow.zusatzaufgabeGui.windows.TodessternGUI;
import raytracer.Color;
import matVecLib.Point3;
import matVecLib.Vector3;

/**
 * Created by Juliand on 30.11.15.
 */
public class SpotLightWindow extends Stage {

    public final BorderPane border = new BorderPane();

    private final Button add = new Button("ADD");
    private final Button can = new Button("CANCEL");
    private final GridPane grid = new GridPane();
    private final HBox btnBox = new HBox();




    //------------------------------ PointLight --------------------------------//

    private final Label position = new Label("Position");

    private final NumberField positionx = new NumberField("4");
    private final NumberField positiony = new NumberField("4");
    private final NumberField positionz = new NumberField("4");

    private final Label direction = new Label("Direction");

    private final NumberField directionx = new NumberField("-1");
    private final NumberField directiony = new NumberField("-1");
    private final NumberField directionz = new NumberField("-1");

    private final Label halfAngle = new Label("Angle");

    private final NumberField hAngle = new NumberField("14");

    private final Label color = new Label("Color");

    private final NumberField colorr = new NumberField("1");
    private final NumberField colorg = new NumberField("1");
    private final NumberField colorb = new NumberField("1");



    public SpotLightWindow(){

        Scene sceneSpotLight = new Scene(border);
        initRoot();

        this.setWidth(350);
        this.setHeight(250);
        this.setTitle("SpotLight Menu");
        this.setScene(sceneSpotLight);
        this.initModality(Modality.APPLICATION_MODAL);
        this.showAndWait();

    }

    private void initRoot() {

        btnBox.getChildren().addAll(add,can);

        border.setCenter(grid);
        border.setBottom(btnBox);

        btnBox.setSpacing(5);

        btnBox.setPadding(new Insets(3,3,15,120));
        grid.setPadding(new Insets(3,10,10,65));

        grid.setVgap(5);
        grid.setHgap(5);

        grid.add(position,0,3);

        grid.add(positionx,1,3);
        grid.add(positiony,2,3);
        grid.add(positionz,3,3);

        grid.add(direction,0,4);

        grid.add(directionx,1,4);
        grid.add(directiony,2,4);
        grid.add(directionz,3,4);

        grid.add(halfAngle,0,5);

        grid.add(hAngle,1,5);

        grid.add(color,0,6);

        grid.add(colorr,1,6);
        grid.add(colorg,2,6);
        grid.add(colorb,3,6);


        //--------------------- EVENTS ------------------------------//
        add.setOnAction(e->addsLight());
        can.setOnAction(e->this.close());
    }

    private void addsLight() {
        addSpotLight();
        this.close();
    }
    private void addSpotLight() {


        Point3 pos = new Point3(positionx.getNumber(), positiony.getNumber(),positionz.getNumber());

        Vector3 dir = new Vector3(directionx.getNumber(), directiony.getNumber(),directionz.getNumber());

        Color color = new Color(colorr.getNumber(),colorg.getNumber(),colorb.getNumber());

        Double ha = new Double(hAngle.getNumber());

        SpotLight spot = new SpotLight(color,pos,true,dir,Math.PI/ha);

        TodessternGUI.welt.lightList.add(spot);

    }
}
