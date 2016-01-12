package ourWindow.zusatzaufgabeGui.windows;

import camera.PerspectiveCamera;
import geometries.Node;
import geometries.ShapeFromFile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lights.Light;
import lights.PointLight;
import lights.SpotLight;
import matVecLib.Point3;
import matVecLib.Vector3;
import materials.SingleColorMaterial;
import raytracer.Color;
import raytracer.Transform;
import raytracer.World;
import sampling.SamplingPattern;

import java.util.ArrayList;


/**
 * This class represents the window of the OBJLoader menu.
 * You can type a file name in the text field and choose a texture.
 * Created by Juliand on 09.01.16.
 */
public class ObjWindow extends Stage {
    
    private BorderPane border = new BorderPane();
    private GridPane grid  = new GridPane();
    private HBox btnBox = new HBox();
    private Button add = new Button("LOAD");
    private Button can = new Button("CANCEL");
    private Label text = new Label("FILE NAME: ");
    private TextField fileName = new TextField();

    private final CheckBox defaultCam = new CheckBox("default cam");


    
    public ObjWindow(){

        Scene sceneCamera = new Scene(border);
        initRoot();

        this.setWidth(300);
        this.setHeight(180);
        this.setTitle("OBJLoader");
        this.setScene(sceneCamera);
        this.initModality(Modality.APPLICATION_MODAL);
        this.showAndWait();


    }

    private void initRoot() {

        fileName.setMinWidth(110);

        btnBox.getChildren().addAll(add,can);


        border.setCenter(grid);
        border.setBottom(btnBox);


        grid.add(text,0,3);
        grid.add(fileName,1,3);
        grid.add(defaultCam,1,4);
        add.setOnAction(e->loadScene());
        can.setOnAction(e->this.close());

        btnBox.setSpacing(5);
        btnBox.setPadding(new Insets(3,3,15,85));
        grid.setPadding(new Insets(3,10,10,30));

        grid.setVgap(5);
        grid.setHgap(5);

    }

    /**
     * This
     */
    private void loadScene() {


        createShape();
        this.close();


    }

    /**
     * This class creates a shape from file.
     * @return The ShapeObject
     */

    private ShapeFromFile createShape() {

        if (TodessternGUI.welt == null){
            TodessternGUI.welt = new World(new raytracer.Color(0.0,0.0,0.0),new raytracer.Color(0.1,0.1,0.1),(1.0));
        }

        if (defaultCam.isSelected() == true){


            final PointLight spotlight = new PointLight(new Color(1,1,1), new Point3(4,4,4),true);

            final PerspectiveCamera cam10 = new PerspectiveCamera(new Point3(8,8,8),new Vector3(-1,-1,-1),
                    new Vector3(0,1,0),new SamplingPattern(1),Math.PI/4);


            TodessternGUI.cam = cam10;
            TodessternGUI.welt.lightList.add(spotlight);

        }


        String getFile = "";
        getFile  = fileName.getText();
        System.out.println(fileName);

        ShapeFromFile loadshape = new ShapeFromFile(getFile,new SingleColorMaterial(new Color(1,0,0)));

        Node testn = new Node(new Transform(), new ArrayList<>());

        testn.g.add(loadshape.OBJLoader());

        TodessternGUI.welt.list.add(testn);
        return loadshape;
    }


}
