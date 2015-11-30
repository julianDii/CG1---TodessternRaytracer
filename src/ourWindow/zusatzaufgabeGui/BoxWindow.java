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

    private final TextField lbfx = new TextField("-0.5");
    private final TextField lbfy = new TextField("0");
    private final TextField lbfz = new TextField("-0.5");

    private final Label run = new Label("run");

    private final TextField runx = new TextField("0.5");
    private final TextField runy = new TextField("1");
    private final TextField runz = new TextField("0.5");


    //---------------------- SINGLE ----------------------//
    private  final Label single = new Label("Color");

    private final TextField singleCr = new TextField("0");
    private final TextField singleCg = new TextField("0");
    private final TextField singleCb = new TextField("1");


    //------------------- PHONG --------------------------//

    private final Label diffCol = new Label("Diffuse");

    private final TextField diffuser = new TextField("1");
    private final TextField diffuseg = new TextField("0");
    private final TextField diffuseb = new TextField("0");

    private final Label specCol  = new Label("Specular");

    private final TextField specularr = new TextField("1");
    private final TextField specularg = new TextField("1");
    private final TextField specularb = new TextField("1");

    private final Label exp = new Label("Exponennt");

    private final TextField exponennt = new TextField("64");

    //------------------- LAMBERT ---------------------------//
    private final Label lamCol = new Label("Lambert");

    private final TextField lamr = new TextField("1");
    private final TextField lamg = new TextField("0");
    private final TextField lamb = new TextField("0");

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

        // Maximal size of the fields
        lbfx.setMaxSize(50, 10);
        lbfy.setMaxSize(50, 10);
        lbfz.setMaxSize(50, 10);

        runx.setMaxSize(50, 10);
        runy.setMaxSize(50, 10);
        runz.setMaxSize(50, 10);
        exponennt.setMaxSize(50,10);

        diffuser.setMaxSize(50, 10);
        diffuseg.setMaxSize(50, 10);
        diffuseb.setMaxSize(50, 10);
        specularr.setMaxSize(50, 10);
        specularg.setMaxSize(50, 10);
        specularb.setMaxSize(50, 10);

        lamr.setMaxSize(50, 10);
        lamg.setMaxSize(50, 10);
        lamb.setMaxSize(50, 10);

        singleCr.setMaxSize(50, 10);
        singleCg.setMaxSize(50, 10);
        singleCb.setMaxSize(50, 10);

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

        Point3 lb = new Point3(Double.valueOf(lbfx.getText()), Double.valueOf(lbfy.getText()),
                Double.valueOf(lbfz.getText()));

        Point3 ru = new Point3(Double.valueOf(runx.getText()), Double.valueOf(runy.getText()),
                Double.valueOf(runz.getText()));

        //Single
        Color singleCol = new Color(Double.valueOf(singleCr.getText()), Double.valueOf(singleCg.getText()),
                Double.valueOf(singleCb.getText()));

        SingleColorMaterial sinCol = new SingleColorMaterial(singleCol);

        //Phong

        Color diffCol = new Color(Double.valueOf(diffuser.getText()), Double.valueOf(diffuseg.getText()),
                Double.valueOf(diffuseb.getText()));

        Color specCol = new Color(Double.valueOf(specularr.getText()), Double.valueOf(specularg.getText()),
                Double.valueOf(specularb.getText()));

        Integer exp = new Integer(exponennt.getText());

        PhongMaterial phongMat = new PhongMaterial(diffCol, specCol, exp);

        //Lambert

        Color lamColor = new Color(Double.valueOf(lamr.getText()), Double.valueOf(lamg.getText()),
                Double.valueOf(lamb.getText()));


        LambertMaterial lamMat = new LambertMaterial(lamColor);

        if (selectedToggle == togSingle) {
            AxisAlignedBox sinBox = new AxisAlignedBox(lb, ru, sinCol);
            TodessternGUI.welt.list.add(sinBox);
        }
        if (selectedToggle == togLambert) {
            AxisAlignedBox lamBox = new AxisAlignedBox(lb, ru, lamMat);
            TodessternGUI.welt.list.add(lamBox);
        }
        if (selectedToggle == togPhong) {
            AxisAlignedBox phongBox = new AxisAlignedBox(lb, ru, phongMat);
            TodessternGUI.welt.list.add(phongBox);
        }

    }

}
