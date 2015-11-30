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
import raytracer.geometrie.Triangle;
import raytracer.matVecLib.Point3;

/**
 * Created by Juliand on 25.11.15.
 */
public class TriangleWindow extends Stage {

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


    //------------------------------ TRIANGLE --------------------------------//

    private final Label firstCorner = new Label("firstCorner");

    private final NumberField firstx = new NumberField("0");
    private final NumberField firsty = new NumberField("0");
    private final NumberField firstz = new NumberField("-1");

    private final Label secondCorner = new Label("run");

    private final NumberField secondx = new NumberField("1");
    private final NumberField secondy = new NumberField("0");
    private final NumberField secondz = new NumberField("-1");

    private final Label thirdCorner = new Label("thirdCorner");

    private final NumberField thirdx = new NumberField("1");
    private final NumberField thirdy = new NumberField("1");
    private final NumberField thirdz = new NumberField("-1");


    //---------------------- SINGLE ----------------------//
    private  final Label single = new Label("Color");

    private final NumberField singleCr = new NumberField("0");
    private final NumberField singleCg = new NumberField("0");
    private final NumberField singleCb = new NumberField("1");


    //------------------- PHONG --------------------------//

    private final Label diffCol = new Label("Diffuse");

    private final NumberField diffuser = new NumberField("1");
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

    public TriangleWindow(){


        Scene sceneBox = new Scene(border);
        initRoot();

        this.setWidth(350);
        this.setHeight(120);
        this.setTitle("Triangle Menu");
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
        add.setOnAction(e->addTri());
        can.setOnAction(e->this.close());

        tog.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {

            if(tog.getSelectedToggle() == togSingle){
                this.setHeight(250);
                grid.getChildren().clear();

                btnBox.getChildren().clear();
                btnBox.getChildren().addAll(add,can);
                btnBox.setPadding(new Insets(3,3,15,115));

                grid.add(firstCorner,0,3);

                grid.add(firstx,1,3);
                grid.add(firsty,2,3);
                grid.add(firstz,3,3);

                grid.add(secondCorner,0,4);

                grid.add(secondx,1,4);
                grid.add(secondy,2,4);
                grid.add(secondz,3,4);

                grid.add(thirdCorner,0,5);

                grid.add(thirdx,1,5);
                grid.add(thirdy,2,5);
                grid.add(thirdz,3,5);

                grid.add(single,0,6);

                grid.add(singleCr,1,6);
                grid.add(singleCg,2,6);
                grid.add(singleCb,3,6);
            }

            if (tog.getSelectedToggle() == togLambert) {

                this.setHeight(250);
                grid.getChildren().clear();
                btnBox.getChildren().clear();
                btnBox.getChildren().addAll(add,can);
                btnBox.setPadding(new Insets(3,3,15,115));

                grid.add(firstCorner,0,3);

                grid.add(firstx,1,3);
                grid.add(firsty,2,3);
                grid.add(firstz,3,3);

                grid.add(secondCorner,0,4);

                grid.add(secondx,1,4);
                grid.add(secondy,2,4);
                grid.add(secondz,3,4);

                grid.add(thirdCorner,0,5);

                grid.add(thirdx,1,5);
                grid.add(thirdy,2,5);
                grid.add(thirdz,3,5);

                grid.add(lamCol,0,6);

                grid.add(lamr,1,6);
                grid.add(lamg,2,6);
                grid.add(lamb,3,6);

            } else {
                if (tog.getSelectedToggle() == togPhong) {

                    this.setHeight(300);

                    grid.getChildren().clear();
                    btnBox.getChildren().clear();
                    btnBox.getChildren().addAll(add,can);
                    btnBox.setPadding(new Insets(3,3,15,115));

                    grid.add(firstCorner,0,3);

                    grid.add(firstx,1,3);
                    grid.add(firsty,2,3);
                    grid.add(firstz,3,3);

                    grid.add(secondCorner,0,4);

                    grid.add(secondx,1,4);
                    grid.add(secondy,2,4);
                    grid.add(secondz,3,4);

                    grid.add(thirdCorner,0,5);

                    grid.add(thirdx,1,5);
                    grid.add(thirdy,2,5);
                    grid.add(thirdz,3,5);

                    grid.add(diffCol,0,6);

                    grid.add(diffuser,1,6);
                    grid.add(diffuseg,2,6);
                    grid.add(diffuseb,3,6);

                    grid.add(specCol,0,7);

                    grid.add(specularr,1,7);
                    grid.add(specularg,2,7);
                    grid.add(specularb,3,7);

                    grid.add(exp,0,8);
                    grid.add(exponennt,1,8);

                }
            }
        });

    }

    private void addTri() {
        addTriangle(tog.getSelectedToggle());
        this.close();
    }


    private void addTriangle(Toggle selectedToggle) {

        Point3 fPoint = new Point3(firstx.getNumber(), firsty.getNumber(), firstz.getNumber());
        Point3 sPoint = new Point3(secondx.getNumber(), secondy.getNumber(), secondz.getNumber());
        Point3 tPoint = new Point3(thirdx.getNumber(), thirdy.getNumber(), thirdz.getNumber());

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
            Triangle sinTri = new Triangle(fPoint,sPoint,tPoint, sinCol);
            TodessternGUI.welt.list.add(sinTri);
        }
        if (selectedToggle == togLambert) {
            Triangle lamTri = new Triangle(fPoint,sPoint,tPoint, lamMat);
            TodessternGUI.welt.list.add(lamTri);
        }
        if (selectedToggle == togPhong) {
            Triangle phongTri = new Triangle(fPoint,sPoint,tPoint, phongMat);
            TodessternGUI.welt.list.add(phongTri);
        }

    }

}
