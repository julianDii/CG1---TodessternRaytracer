package ourWindow.zusatzaufgabeGui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import raytracer.camera.Camera;
import raytracer.camera.OrthographicCamera;
import raytracer.camera.PerspectiveCamera;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * Created by Juliand on 25.11.15.
 */

public class CameraWindow extends Stage {

    private final RadioButton togOrto = new RadioButton("Orthographic");
    private final RadioButton togPer = new RadioButton("Perspective");
    private final TextField punktx = new TextField("0");
    private final TextField punkty = new TextField("0");
    private final TextField punktz = new TextField("0");
    private final TextField directionx = new TextField("0");
    private final TextField directiony = new TextField("0");
    private final TextField directionz = new TextField("0");
    private final TextField rotationx = new TextField("0");
    private final TextField rotationy = new TextField("0");
    private final TextField rotationz = new TextField("0");
    private final TextField scaling = new TextField("1");
    GridPane root = new GridPane();

    public CameraWindow(){



        Scene sceneCamera = new Scene(root);
        initRoot();
        this.setWidth(300);
        this.setHeight(200);
        this.setTitle("Cam Menu");
        this.setScene(sceneCamera);
        this.initModality(Modality.APPLICATION_MODAL);
        this.showAndWait();

    }

    private void initRoot() {


        root.setPadding(new Insets(10,10,10,30));



        ToggleGroup tog = new ToggleGroup();
        togOrto.setToggleGroup(tog);
        togPer.setToggleGroup(tog);
        HBox togBox = new HBox();
        togBox.setSpacing(20);
        togBox.getChildren().addAll(togOrto,togPer);
        root.add(togBox,0,0);

        tog.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (tog.getSelectedToggle() == togOrto) {

                Label e = new Label("POSITION");

                root.getChildren().removeAll();
                root.add(e,0,2);
                root.add(punktx,1,2);
                root.add(punkty,2,2);
                root.add(punktz,3,2);

                Label g = new Label("DIRECTION");
                Label t = new Label("ROTATION");
                Label s = new Label("Scaling");


            }
               else {
                if (tog.getSelectedToggle() == togPer) {

                    root.getChildren().removeAll();

                    System.out.println("togPer");

                }
            }

        });


    }

    private Camera createCamera(Toggle selectedToggle) {

        Point3 point = new Point3(Double.valueOf(punktx.getText()), Double.valueOf(punkty.getText()),
                Double.valueOf(punktz.getText()));
        Vector3 direction = new Vector3(Double.valueOf(directionx.getText()), Double.valueOf(directiony.getText()),
                Double.valueOf(directionz.getText()));
        Vector3 rotation = new Vector3(Double.valueOf(rotationx.getText()), Double.valueOf(rotationy.getText()),
                Double.valueOf(rotationz.getText()));


        if (selectedToggle == togOrto) {

            return new OrthographicCamera(point, direction, rotation, Double.valueOf(scaling.getText()));
        }

        return new PerspectiveCamera(point, direction, rotation, Double.valueOf(scaling.getText()));
    }

}


