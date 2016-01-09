package ourWindow.zusatzaufgabeGui.windows.geometrieWindows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import materials.LambertMaterial;
import materials.PhongMaterial;
import materials.SingleColorMaterial;
import ourWindow.zusatzaufgabeGui.windows.NumberField;
import ourWindow.zusatzaufgabeGui.windows.TodessternGUI;
import raytracer.Color;
import matVecLib.Point3;

/**
 * Created by Juliand on 25.11.15.
 */

public class SphereWindow extends Stage {

    public final BorderPane border = new BorderPane();

    private final Button add = new Button("ADD");
    private final Button can = new Button("CANCEL");
    private final GridPane grid = new GridPane();
    private final HBox btnBox = new HBox();
    private final HBox togBox = new HBox();

    private final Label material = new Label("Material: ");

    private final RadioButton togSingle = new RadioButton("SingleColor");
    private final RadioButton togLambert = new RadioButton("Lambert");
    private final RadioButton togPhong = new RadioButton("Phong");

    private final ToggleGroup tog = new ToggleGroup();

    //------------------------------ SPHERE --------------------------------//

    private final Label centerPoint = new Label("Center");

    private final NumberField punktx = new NumberField("0");
    private final NumberField punkty = new NumberField("0");
    private final NumberField punktz = new NumberField("-1");

    private final Label radius = new Label("Radius");

    private final NumberField ra = new NumberField("0.5");


    //---------------------- SINGLE ----------------------//
    private  final Label single = new Label("Color");

    private final NumberField singleCr = new NumberField("0");
    private final NumberField singleCg = new NumberField("0");
    private final NumberField singleCb = new NumberField("1");


    //------------------- PHONG --------------------------//

    private final Label diffCol = new Label("Diffuse");

    private final NumberField diffuser = new NumberField("0");
    private final NumberField diffuseg = new NumberField("1");
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

    public SphereWindow(){

        Scene sceneSphere = new Scene(border);
        initRoot();

        this.setWidth(350);
        this.setHeight(120);
        this.setTitle("Sphere Menu");
        this.setScene(sceneSphere);
        this.initModality(Modality.APPLICATION_MODAL);
        this.showAndWait();

    }

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
        add.setOnAction(e->addSph());
        can.setOnAction(e->this.close());


        tog.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {

            if(tog.getSelectedToggle() == togSingle){
                this.setHeight(250);
                grid.getChildren().clear();

                btnBox.getChildren().clear();
                btnBox.getChildren().addAll(add,can);
                btnBox.setPadding(new Insets(3,3,15,115));

                grid.add(centerPoint,0,3);

                grid.add(punktx,1,3);
                grid.add(punkty,2,3);
                grid.add(punktz,3,3);

                grid.add(radius,0,4);

                grid.add(ra,1,4);

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

                grid.add(centerPoint,0,3);

                grid.add(punktx,1,3);
                grid.add(punkty,2,3);
                grid.add(punktz,3,3);

                grid.add(radius,0,4);

                grid.add(ra,1,4);

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

                    grid.add(centerPoint,0,3);

                    grid.add(punktx,1,3);
                    grid.add(punkty,2,3);
                    grid.add(punktz,3,3);

                    grid.add(radius,0,4);

                    grid.add(ra,1,4);

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

    private void addSph() {
        addSphere(tog.getSelectedToggle());
        this.close();
    }


    private void addSphere(Toggle selectedToggle) {

        Point3 center = new Point3(punktx.getNumber(), punkty.getNumber(), punktz.getNumber());
        Double radi = new Double(ra.getNumber());

        //Single
        Color singleCol = new Color(singleCr.getNumber(), singleCg.getNumber(), singleCb.getNumber());
        SingleColorMaterial sinCol = new SingleColorMaterial(singleCol);

        //Phong
        Color diffCol = new Color(diffuser.getNumber(), diffuseg.getNumber(), diffuseb.getNumber());
        Color specCol = new Color(specularr.getNumber(), specularg.getNumber(), specularb.getNumber());
        Integer exp = new Integer((int)exponennt.getNumber());

        PhongMaterial phongMat = new PhongMaterial(diffCol, specCol, exp);

        //Lambert
        Color lamColor = new Color(lamr.getNumber(), lamg.getNumber(), lamb.getNumber());

        LambertMaterial lamMat = new LambertMaterial(lamColor);

        if (selectedToggle == togSingle) {
            geometries.Sphere sinSphere = new geometries.Sphere(center, radi, sinCol);
            TodessternGUI.welt.list.add(sinSphere);
        }
        if (selectedToggle == togLambert) {
            geometries.Sphere lamSphere = new geometries.Sphere(center, radi, lamMat);
            TodessternGUI.welt.list.add(lamSphere);
        }
        if (selectedToggle == togPhong) {
            geometries.Sphere phoSphere = new geometries.Sphere(center, radi, phongMat);
            TodessternGUI.welt.list.add(phoSphere);
        }

    }
}
