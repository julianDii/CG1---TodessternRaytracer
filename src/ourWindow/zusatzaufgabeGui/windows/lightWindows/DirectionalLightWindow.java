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
