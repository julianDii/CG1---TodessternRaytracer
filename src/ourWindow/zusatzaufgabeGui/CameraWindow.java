package ourWindow.zusatzaufgabeGui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import raytracer.camera.Camera;
import raytracer.camera.OrthographicCamera;
import raytracer.camera.PerspectiveCamera;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;


/**
 * This class represents a gui to select and build new cameras.
 * Created by Juliand on 25.11.15.
 */

public class CameraWindow extends Stage {

    private final  Button add = new Button("ADD");
    private final Button can = new Button("CANCEL");
    private ToggleGroup tog;
    private final RadioButton togOrto = new RadioButton("Orthographic");
    private final RadioButton togPer = new RadioButton("Perspective");
    private final TextField punktx = new TextField("0");
    private final TextField punkty = new TextField("0");
    private final TextField punktz = new TextField("0");
    private final TextField directionx = new TextField("0");
    private final TextField directiony = new TextField("0");
    private final TextField directionz = new TextField("-1");
    private final TextField rotationx = new TextField("0");
    private final TextField rotationy = new TextField("1");
    private final TextField rotationz = new TextField("0");
    private final TextField scaling = new TextField("4");
    private final GridPane root = new GridPane();
    private final BorderPane border = new BorderPane();

    /**
     * This constructor builds a new camera window.
     */
    public CameraWindow(){

        Scene sceneCamera = new Scene(border);
        initRoot();
        this.setWidth(300);
        this.setHeight(100);
        this.setTitle("Cam Menu");
        this.setScene(sceneCamera);
        this.initModality(Modality.APPLICATION_MODAL);
        this.showAndWait();
    }

    /**
     * This method creates the layout of the camera window.
     */
    private void initRoot() {

        // Maximal size of the fields
        punktx.setMaxSize(50, 10);
        punkty.setMaxSize(50, 10);
        punktz.setMaxSize(50, 10);
        directionx.setMaxSize(50, 10);
        directiony.setMaxSize(50, 10);
        directionz.setMaxSize(50, 10);
        rotationx.setMaxSize(50, 10);
        rotationy.setMaxSize(50, 10);
        rotationz.setMaxSize(50, 10);
        scaling.setMaxSize(50,10);

        //----------------------------
        border.setCenter(root);

        root.setPadding(new Insets(3,10,10,30));
        root.setVgap(5);
        root.setHgap(5);


        add.setMaxSize(100, 10);
        can.setMaxSize(100, 10);


        tog = new ToggleGroup();
        togOrto.setToggleGroup(tog);
        togPer.setToggleGroup(tog);
        HBox togBox = new HBox();
        togBox.setPadding(new Insets(15, 3, 3, 30));
        HBox btnBox = new HBox();
        btnBox.setPadding(new Insets(3,3,15,115));
        togBox.setSpacing(20);
        togBox.getChildren().addAll(togOrto,togPer);
        btnBox.setSpacing(5);
        btnBox.getChildren().addAll(can);
        border.setTop(togBox);
        border.setBottom(btnBox);


        // Events
        add.setOnAction(e->addCamera());
        can.setOnAction(e->this.close());
        //------------------------------------------------------------------------------------//

        tog.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (tog.getSelectedToggle() == togOrto) {
                this.setHeight(250);

                root.getChildren().clear();
                btnBox.getChildren().clear();
                btnBox.getChildren().addAll(add,can);
                btnBox.setPadding(new Insets(3,3,15,90));

                Label e = new Label("POSITION");

                root.add(e, 0, 3);
                root.add(punktx, 1, 3);
                root.add(punkty, 2, 3);
                root.add(punktz, 3, 3);

                Label g = new Label("DIRECTION");

                root.add(g, 0, 4);
                root.add(directionx, 1, 4);
                root.add(directiony, 2, 4);
                root.add(directionz, 3, 4);

                Label t = new Label("ROTATION");

                root.add(t, 0, 5);
                root.add(rotationx, 1, 5);
                root.add(rotationy, 2, 5);
                root.add(rotationz, 3, 5);

                Label s = new Label("Scaling");

                root.add(s, 0, 6);
                root.add(scaling, 1, 6);

                border.setBottom(btnBox);

            } else {
                if (tog.getSelectedToggle() == togPer) {

                    this.setHeight(250);

                    root.getChildren().clear();
                    btnBox.getChildren().clear();
                    btnBox.getChildren().addAll(add,can);
                    btnBox.setPadding(new Insets(3,3,15,90));

                    Label e = new Label("POSITION");

                    root.add(e, 0, 3);
                    root.add(punktx, 1, 3);
                    root.add(punkty, 2, 3);
                    root.add(punktz, 3, 3);

                    Label g = new Label("DIRECTION");

                    root.add(g, 0, 4);
                    root.add(directionx, 1, 4);
                    root.add(directiony, 2, 4);
                    root.add(directionz, 3, 4);

                    Label t = new Label("ROTATION");

                    root.add(t, 0, 5);
                    root.add(rotationx, 1, 5);
                    root.add(rotationy, 2, 5);
                    root.add(rotationz, 3, 5);

                    Label s = new Label("Angle");

                    root.add(s, 0, 6);
                    root.add(scaling, 1, 6);

                    border.setBottom(btnBox);
                }
            }
        });
    }

    /**
     * This method calls the createCmera method eith the selected toggle.
     */
    private void addCamera() {

        createCamera(tog.getSelectedToggle());
        this.close();
    }

    /**
     * This method creates a new camera with the given parameters from the gui.
     * @param selectedToggle
     * @return The new camera.
     */
    private Camera createCamera(Toggle selectedToggle) {

        Point3 point = new Point3(Double.valueOf(punktx.getText()), Double.valueOf(punkty.getText()),
                Double.valueOf(punktz.getText()));
        Vector3 direction = new Vector3(Double.valueOf(directionx.getText()), Double.valueOf(directiony.getText()),
                Double.valueOf(directionz.getText()));
        Vector3 rotation = new Vector3(Double.valueOf(rotationx.getText()), Double.valueOf(rotationy.getText()),
                Double.valueOf(rotationz.getText()));

        Double sca = new Double(scaling.getText());


        if (selectedToggle == togOrto) {

            OrthographicCamera orthoCam = new OrthographicCamera(point, direction, rotation, sca);
            TodessternGUI.cam=orthoCam;

            return orthoCam;
        }

        if (selectedToggle == togPer){
            PerspectiveCamera perCam = new PerspectiveCamera(point, direction, rotation,Math.PI/sca);
            TodessternGUI.cam=perCam;
            return perCam;
        }
        return null;
    }

}


