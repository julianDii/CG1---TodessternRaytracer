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
import raytracer.geometrie.AxisAlignedBox;
import raytracer.matVecLib.Point3;

/**
 * Created by Juliand on 25.11.15.
 */
public class BoxWindow extends Stage {

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

    private final Label lbf = new Label("lbf");

    private final NumberField lbfx = new NumberField("-1.5");
    private final NumberField lbfy = new NumberField("0.5");
    private final NumberField lbfz = new NumberField("0.5");

    private final Label run = new Label("run");

    private final NumberField runx = new NumberField("-0.5");
    private final NumberField runy = new NumberField("1.5");
    private final NumberField runz = new NumberField("1.5");


    //---------------------- SINGLE ----------------------//
    private  final Label single = new Label("Color");

    private final NumberField singleCr = new NumberField("0");
    private final NumberField singleCg = new NumberField("0");
    private final NumberField singleCb = new NumberField("1");

    //------------------- PHONG --------------------------//

    private final Label diffCol = new Label("Diffuse");

    private final NumberField diffuser = new NumberField("0");
    private final NumberField diffuseg = new NumberField("0");
    private final NumberField diffuseb = new NumberField("1");

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

    public BoxWindow(){

        Scene sceneBox = new Scene(border);
        initRoot();

        this.setWidth(350);
        this.setHeight(120);
        this.setTitle("Box Menu");
        this.setScene(sceneBox);
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
        add.setOnAction(e->addBox());
        can.setOnAction(e->this.close());

        tog.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {

            if(tog.getSelectedToggle() == togSingle){
                this.setHeight(250);
                grid.getChildren().clear();

                btnBox.getChildren().clear();
                btnBox.getChildren().addAll(add,can);
                btnBox.setPadding(new Insets(3,3,15,115));

                grid.add(lbf,0,3);

                grid.add(lbfx,1,3);
                grid.add(lbfy,2,3);
                grid.add(lbfz,3,3);

                grid.add(run,0,4);

                grid.add(runx,1,4);
                grid.add(runy,2,4);
                grid.add(runz,3,4);

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

                grid.add(lbf,0,3);

                grid.add(lbfx,1,3);
                grid.add(lbfy,2,3);
                grid.add(lbfz,3,3);

                grid.add(run,0,4);

                grid.add(runx,1,4);
                grid.add(runy,2,4);
                grid.add(runz,3,4);

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

                    grid.add(lbf,0,3);

                    grid.add(lbfx,1,3);
                    grid.add(lbfy,2,3);
                    grid.add(lbfz,3,3);

                    grid.add(run,0,4);

                    grid.add(runx,1,4);
                    grid.add(runy,2,4);
                    grid.add(runz,3,4);

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

    private void addBox() {
        addBox(tog.getSelectedToggle());
        this.close();
    }

    private void addBox(Toggle selectedToggle) {

        Point3 lb = new Point3(lbfx.getNumber(),lbfy.getNumber(), lbfz.getNumber());
        Point3 ru = new Point3(runx.getNumber(), runy.getNumber(), runz.getNumber());

        //Single
        Color singleCol = new Color(singleCr.getNumber(), singleCg.getNumber(), singleCb.getNumber());
        SingleColorMaterial sinCol = new SingleColorMaterial(singleCol);

        //Phong
        Color diffCol = new Color(diffuser.getNumber(), diffuseg.getNumber(), diffuseb.getNumber());
        Color specCol = new Color(specularr.getNumber(), specularg.getNumber(), specularb.getNumber());
        Integer exp = new Integer((int)exponennt.getNumber());
        PhongMaterial phongMat = new PhongMaterial(diffCol, specCol, exp);

        //Lambert
        Color lamColor = new Color(lamr.getNumber(), lamg.getNumber(),lamb.getNumber());
        LambertMaterial lamMat = new LambertMaterial(lamColor);

//        if (selectedToggle == togSingle) {
//            AxisAlignedBox sinBox = new AxisAlignedBox(lb, ru, sinCol);
//            TodessternGUI.welt.list.add(sinBox);
//        }
//        if (selectedToggle == togLambert) {
//            AxisAlignedBox lamBox = new AxisAlignedBox(lb, ru, lamMat);
//            TodessternGUI.welt.list.add(lamBox);
//        }
//        if (selectedToggle == togPhong) {
//            AxisAlignedBox phongBox = new AxisAlignedBox(lb, ru, phongMat);
//            TodessternGUI.welt.list.add(phongBox);
//        }

    }

}
