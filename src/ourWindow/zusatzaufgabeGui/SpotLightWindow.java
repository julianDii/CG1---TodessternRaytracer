package ourWindow.zusatzaufgabeGui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import licht.PointLight;
import licht.SpotLight;
import raytracer.Color;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

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

    private final NumberField hAngle = new NumberField("4");

    private final Label color = new Label("Color");

    private final NumberField colorr = new NumberField("1");
    private final NumberField colorg = new NumberField("1");
    private final NumberField colorb = new NumberField("1");



    public SpotLightWindow(){

        Scene sceneSpotLight = new Scene(border);
        initRoot();

        this.setWidth(350);
        this.setHeight(150);
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
        add.setOnAction(e->addLight());
        can.setOnAction(e->this.close());
    }

    private void addLight() {
        addSpotLight();
        this.close();
    }
    private void addSpotLight() {

        Point3 position = new Point3(positionx.getNumber(), positiony.getNumber(),positionz.getNumber());

        Vector3 dir = new Vector3(directionx.getNumber(), directiony.getNumber(),positionz.getNumber());

        Color color = new Color(colorr.getNumber(),colorg.getNumber(),colorb.getNumber());

        Double ha = new Double(hAngle.getNumber());

        SpotLight spotL = new SpotLight(color,position,dir,ha);

        TodessternGUI.welt.lightList.add(spotL);

    }
}
