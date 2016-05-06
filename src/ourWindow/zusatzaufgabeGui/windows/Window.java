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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This Class builds a basic MenuScreen (window). Is's created with a border plane and a grid pane to structure the
 * layouts. Also we have a box with two buttons in the bottom area of the BorderPane.
 * Created by Juliand on 09.01.16.
 */
public class Window extends Stage {


    /**
     * The first button component.
     */
    public Button add = new Button("XX");
    /**
     * The second button component
     */
    public Button can = new Button("XX");


    private final HBox btnBox = new HBox();


    /**
     * The border pane for layouting the main components on the window.
     */
    public BorderPane border = new BorderPane();

    /**
     * The grid pane to structure the layout of the window.
     */
    public GridPane grid = new GridPane();


    Label file = new Label("egsöigjoi");

    NumberField fileName = new NumberField("insert filename");

    /**
     * This constructor builds the basic window.
     */
    public Window(){


        Scene window = new Scene(border);
        initRoot();

        this.setWidth(300);
        this.setHeight(180);
        this.setTitle("overwrite this");
        this.setScene(window);
        this.initModality(Modality.APPLICATION_MODAL);
        this.showAndWait();
    }

    /**
     * This method sets-up the stuff.
     */
    public void initRoot() {

        btnBox.getChildren().addAll(add,can);


        btnBox.setSpacing(5);
        btnBox.setPadding(new Insets(3, 3, 15, 70));

        border.setCenter(grid);
        border.setBottom(btnBox);



        add.setOnAction(e -> doSmthg());
        can.setOnAction(e -> this.close());

        grid.add(fileName,0,3);
        grid.setPadding(new Insets(3, 10, 10, 30));
        grid.setVgap(5);
        grid.setHgap(5);
    }

    private void doSmthg() {

    }
}
