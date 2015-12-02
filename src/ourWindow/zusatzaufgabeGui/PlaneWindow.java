package ourWindow.zusatzaufgabeGui;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import material.LambertMaterial;
import material.PhongMaterial;
import material.SingleColorMaterial;
import raytracer.Color;
import raytracer.geometrie.Plane;
import raytracer.matVecLib.Normal3;
import raytracer.matVecLib.Point3;

/**
 * This class represents a gui to create a new plane.
 * Created by Juliand on 25.11.15.
 */
public class PlaneWindow extends Stage{

    private final Label material = new Label("Material: ");
    private final RadioButton togSingle = new RadioButton("SingleColor");
    private final RadioButton togLambert = new RadioButton("Lambert");
    private final RadioButton togPhong = new RadioButton("Phong");

    private final ToggleGroup tog = new ToggleGroup();

    private final BorderPane border = new BorderPane();
    private final Button add = new Button("ADD");
    private final Button can = new Button("CANCEL");
    private final GridPane grid = new GridPane();
    private final HBox btnBox = new HBox();
    private final HBox togBox = new HBox();

    //------------------- PLANE ---------------------------//

    private final Label position = new Label("Position");

    private final NumberField punktx = new NumberField("0");
    private final NumberField punkty = new NumberField("-1");
    private final NumberField punktz = new NumberField("0");

    private final Label normal = new Label("Normal");

    private final NumberField normalx = new NumberField("0");
    private final NumberField normaly = new NumberField("1");
    private final NumberField normalz = new NumberField("0");

    private final Label color = new Label("Color");

    private final NumberField colorr = new NumberField("1");
    private final NumberField colorg = new NumberField("1");
    private final NumberField colorb = new NumberField("1");
    //------------------- SINGLE -------------------------//
    private  final Label single = new Label("Color");

    private final NumberField singleCr = new NumberField("0");
    private final NumberField singleCg = new NumberField("0");
    private final NumberField singleCb = new NumberField("1");




    //------------------- PHONG --------------------------//

    private final Label diffCol = new Label("Diffuse");

    private final NumberField diffuser = new NumberField("1");
    private final NumberField diffuseg = new NumberField("0");
    private final NumberField diffuseb = new NumberField("0");

    private final Label specCol  = new Label("Specular");

    private final NumberField specularr = new NumberField("1");
    private final NumberField specularg = new NumberField("1");
    private final NumberField specularb = new NumberField("1");

    private final Label exp = new Label("Exponennt");

    private final NumberField exponennt = new NumberField("64");

    //------------------- LAMBERT ---------------------------//
    private final Label lamCol = new Label("Lambert");

    private final NumberField lamr = new NumberField("1");
    private final NumberField lamg = new NumberField("0");
    private final NumberField lamb = new NumberField("0");

    /**
     *This contructor builds a new plane window
     * @param primaryStage
     */

    public PlaneWindow(Stage primaryStage){

        Scene sceneCamera = new Scene(border);
        initRoot();
        this.setWidth(350);
        this.setHeight(120);
        this.setTitle("Plane Menu");
        this.setScene(sceneCamera);
        this.initModality(Modality.APPLICATION_MODAL);
        this.showAndWait();
    }

    /**
     * This method crates the Layout of the plane window with the gui elements for creating a new plane.
     */
    private void initRoot() {

        btnBox.getChildren().addAll(can);
        togBox.getChildren().addAll(material,togSingle,togLambert, togPhong);

        border.setTop(togBox);
        border.setCenter(grid);
        border.setBottom(btnBox);

        togSingle.setToggleGroup(tog);
        togLambert.setToggleGroup(tog);
        togPhong.setToggleGroup(tog);


        togBox.setSpacing(10);
        btnBox.setSpacing(5);

        togBox.setPadding(new Insets(15, 3, 3, 16));
        btnBox.setPadding(new Insets(3,3,15,140));
        grid.setPadding(new Insets(3,10,10,65));

        grid.setVgap(5);
        grid.setHgap(5);

        //--------------------- EVENTS ------------------------------//
        add.setOnAction(e->addPlane());
        can.setOnAction(e->this.close());


        tog.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {

            if(tog.getSelectedToggle() == togSingle){
                this.setHeight(250);
                grid.getChildren().clear();
                btnBox.getChildren().clear();
                btnBox.getChildren().addAll(add,can);
                btnBox.setPadding(new Insets(3,3,15,115));

                grid.add(position,0,3);

                grid.add(punktx,1,3);
                grid.add(punkty,2,3);
                grid.add(punktz,3,3);

                grid.add(normal,0,4);

                grid.add(normalx,1,4);
                grid.add(normaly,2,4);
                grid.add(normalz,3,4);

                grid.add(single,0,5);

                grid.add(singleCr,1,5);
                grid.add(singleCg,2,5);
                grid.add(singleCb,3,5);
            }

            if (tog.getSelectedToggle() == togLambert) {

                this.setHeight(250);
                grid.getChildren().clear();
                btnBox.getChildren().clear();
                btnBox.getChildren().addAll(add,can);
                btnBox.setPadding(new Insets(3,3,15,115));

                grid.add(position,0,3);

                grid.add(punktx,1,3);
                grid.add(punkty,2,3);
                grid.add(punktz,3,3);

                grid.add(normal,0,4);

                grid.add(normalx,1,4);
                grid.add(normaly,2,4);
                grid.add(normalz,3,4);

                grid.add(lamCol,0,5);

                grid.add(lamr,1,5);
                grid.add(lamg,2,5);
                grid.add(lamb,3,5);

            } else {
                if (tog.getSelectedToggle() == togPhong) {

                    this.setHeight(300);

                    grid.getChildren().clear();

                    btnBox.getChildren().clear();
                    btnBox.getChildren().addAll(add,can);
                    btnBox.setPadding(new Insets(3,3,15,115));

                    grid.add(position,0,3);

                    grid.add(punktx,1,3);
                    grid.add(punkty,2,3);
                    grid.add(punktz,3,3);

                    grid.add(normal,0,4);

                    grid.add(normalx,1,4);
                    grid.add(normaly,2,4);
                    grid.add(normalz,3,4);

                    grid.add(diffCol,0,5);

                    grid.add(diffuser,1,5);
                    grid.add(diffuseg,2,5);
                    grid.add(diffuseb,3,5);

                    grid.add(specCol,0,6);

                    grid.add(specularr,1,6);
                    grid.add(specularg,2,6);
                    grid.add(specularb,3,6);

                    grid.add(exp,0,7);
                    grid.add(exponennt,1,7);

                }
            }
        });
    }

    /**
     * This method calls the createPlane method with the selected material as parameter.
     * Also the plane window will be closed by this method.
     */

    private void addPlane() {
        createPlane(tog.getSelectedToggle());
        this.close();
    }

    /**
     * This method creates an new plane with the gui values.
     * @param selectedToggle
     * @return The new plane
     */

    private Plane createPlane(Toggle selectedToggle) {

        Point3 point = new Point3(punktx.getNumber(), punkty.getNumber(), punktz.getNumber());
        Normal3 normal = new Normal3(normalx.getNumber(), normaly.getNumber(), normalz.getNumber());
        Color color = new Color(colorr.getNumber(),colorg.getNumber(), colorb.getNumber());

        //Single
        Color singleCol =new Color(singleCr.getNumber(),singleCg.getNumber(), singleCb.getNumber());
        SingleColorMaterial sinCol = new SingleColorMaterial(singleCol);


        //Phong

        Color diffCol = new Color(diffuser.getNumber(),diffuseg.getNumber(), diffuseb.getNumber());
        Color specCol = new Color(specularr.getNumber(),specularg.getNumber(), specularb.getNumber());

        Integer exp = new Integer((int) exponennt.getNumber());

        PhongMaterial phongMat = new PhongMaterial(diffCol,specCol,exp);

        //Lambert

        Color lamColor = new Color(lamr.getNumber(),lamg.getNumber(), lamb.getNumber());

        LambertMaterial lamMat = new LambertMaterial(lamColor);

        if(selectedToggle == togSingle){
            Plane sinPlane = new Plane(point,normal,sinCol);
            TodessternGUI.welt.list.add(sinPlane);
            System.out.println(TodessternGUI.welt.list.size());
            return sinPlane;
        }

        if(selectedToggle == togLambert){

            Plane lamPlane = new Plane(point,normal,lamMat);
            TodessternGUI.welt.list.add(lamPlane);

            return lamPlane;
        }
        if(selectedToggle == togPhong){

            Plane phongPlane = new Plane(point,normal,phongMat);
            TodessternGUI.welt.list.add(phongPlane);
            return phongPlane;
        }
        return null;
    }

}
