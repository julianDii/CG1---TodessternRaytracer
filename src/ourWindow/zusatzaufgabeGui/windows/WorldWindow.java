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
package ourWindow.zusatzaufgabeGui.windows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ourWindow.zusatzaufgabeGui.windows.NumberField;
import raytracer.Color;
import raytracer.World;

/**
 * This class represents a world window gui.
 * Created by Juliand on 25.11.15.
 */
public class WorldWindow extends Stage {

    private final BorderPane border = new BorderPane();

    private final Button add = new Button("ADD");
    private final Button can = new Button("CANCEL");
    private final GridPane grid = new GridPane();
    private final HBox btnBox = new HBox();

    private final Label color = new Label("Color");

    private final NumberField colorr = new NumberField("0");
    private final NumberField colorg = new NumberField("0");
    private final NumberField colorb = new NumberField("0");

    private final Label ambiColor = new Label("AmbientColor");

    private final NumberField ambir = new NumberField("0");
    private final NumberField ambig = new NumberField("0");
    private final NumberField ambib = new NumberField("0");

    private final Label ref = new Label("Refraction Index");
    private final NumberField refIndex = new NumberField("1");

    /**
     * This constructor builds a new world window.
     * @param primarayStage
     */
    public WorldWindow(Stage primarayStage){

        Scene sceneCamera = new Scene(border);
        initRoot();

        this.setWidth(300);
        this.setHeight(180);
        this.setTitle("World Menu");
        this.setScene(sceneCamera);
        this.initModality(Modality.APPLICATION_MODAL);
        this.showAndWait();

    }
    /**
     *This method sets the window loyout up.
     */
    private void initRoot() {

        btnBox.getChildren().addAll(add,can);

        border.setCenter(grid);
        border.setBottom(btnBox);

        grid.add(color,0,3);

        grid.add(colorr,1,3);
        grid.add(colorg,2,3);
        grid.add(colorb,3,3);

        grid.add(ambiColor,0,4);

        grid.add(ambir,1,4);
        grid.add(ambig,2,4);
        grid.add(ambib,3,4);

        grid.add(ref,0,5);
        grid.add(refIndex,1,5);

        add.setOnAction(e->addWorld());
        can.setOnAction(e->this.close());

        btnBox.setSpacing(5);
        btnBox.setPadding(new Insets(3,3,15,70));
        grid.setPadding(new Insets(3,10,10,30));

        grid.setVgap(5);
        grid.setHgap(5);

    }

    /**
     * This method calls the create world method and closes the window.
     */

    private void addWorld() {
        createWorld();
        this.close();
    }

    /**
     * This method creates a new world with the values from the gui.
     */

    private void createWorld() {

        Color worldcol = new raytracer.Color(colorr.getNumber(),colorg.getNumber(), colorb.getNumber());
        Color worldAmbient = new raytracer.Color(ambir.getNumber(),ambig.getNumber(), ambib.getNumber());
        Double r = refIndex.getNumber();


        World newWold = new World(worldcol,worldAmbient, r);

        TodessternGUI.welt = newWold;
    }
}
