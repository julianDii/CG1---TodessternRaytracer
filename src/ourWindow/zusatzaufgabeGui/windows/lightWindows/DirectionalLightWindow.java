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
import lights.DirectionalLight;
import ourWindow.zusatzaufgabeGui.windows.NumberField;
import ourWindow.zusatzaufgabeGui.windows.TodessternGUI;
import raytracer.Color;
import matVecLib.Vector3;

/**
 * Created by Juliand on 30.11.15.
 */
public class DirectionalLightWindow extends Stage {

    public final BorderPane border = new BorderPane();

    private final Button add = new Button("ADD");
    private final Button can = new Button("CANCEL");
    private final GridPane grid = new GridPane();
    private final HBox btnBox = new HBox();




    //------------------------------ PointLight --------------------------------//

    private final Label direction = new Label("Direction");

    private final NumberField directionx = new NumberField("-1");
    private final NumberField directiony = new NumberField("-1");
    private final NumberField directionz = new NumberField("-1");

    private final Label color = new Label("Color");

    private final NumberField colorr = new NumberField("1");
    private final NumberField colorg = new NumberField("1");
    private final NumberField colorb = new NumberField("1");



    public DirectionalLightWindow(){

        Scene sceneDirLight = new Scene(border);
        initRoot();

        this.setWidth(350);
        this.setHeight(150);
        this.setTitle("Box Menu");
        this.setScene(sceneDirLight);
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

        grid.add(direction,0,3);

        grid.add(directionx,1,3);
        grid.add(directiony,2,3);
        grid.add(directionz,3,3);

        grid.add(color,0,4);

        grid.add(colorr,1,4);
        grid.add(colorg,2,4);
        grid.add(colorb,3,4);


        //--------------------- EVENTS ------------------------------//
        add.setOnAction(e->addLight());
        can.setOnAction(e->this.close());
    }

    private void addLight() {
        adddirectionalLight();
        this.close();

    }
    private void adddirectionalLight() {

        Vector3 dir = new Vector3(directionx.getNumber(), directiony.getNumber(),directionz.getNumber());

        Color color = new Color(colorr.getNumber(),colorg.getNumber(),colorb.getNumber());

        DirectionalLight dirL = new DirectionalLight(color,dir,true);

        TodessternGUI.welt.lightList.add(dirL);

    }

}
