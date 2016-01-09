package ourWindow.zusatzaufgabeGui.windows.lightWindows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lights.PointLight;
import ourWindow.zusatzaufgabeGui.windows.NumberField;
import ourWindow.zusatzaufgabeGui.windows.TodessternGUI;
import raytracer.Color;
import matVecLib.Point3;

/**
 * Created by Juliand on 30.11.15.
 */
public class PointLightWindow extends Stage{

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

    private final Label color = new Label("Color");

    private final NumberField colorr = new NumberField("1");
    private final NumberField colorg = new NumberField("1");
    private final NumberField colorb = new NumberField("1");



    public PointLightWindow(){

        Scene scenePointLight = new Scene(border);
        initRoot();

        this.setWidth(350);
        this.setHeight(150);
        this.setTitle("Box Menu");
        this.setScene(scenePointLight);
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

        grid.add(color,0,4);

        grid.add(colorr,1,4);
        grid.add(colorg,2,4);
        grid.add(colorb,3,4);


        //--------------------- EVENTS ------------------------------//
        add.setOnAction(e->addLight());
        can.setOnAction(e->this.close());
    }

    private void addLight() {
        addpLight();
        this.close();
    }

    private void addpLight() {

        Point3 position = new Point3(positionx.getNumber(), positiony.getNumber(),positionz.getNumber());

        Color color = new Color(colorr.getNumber(),colorg.getNumber(),colorb.getNumber());

        PointLight pointL = new PointLight(color,position,true);

        TodessternGUI.welt.lightList.add(pointL);

    }

}
