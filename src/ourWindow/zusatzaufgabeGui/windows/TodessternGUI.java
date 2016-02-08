package ourWindow.zusatzaufgabeGui.windows;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import ourWindow.zusatzaufgabeGui.windows.geometrieWindows.*;
import ourWindow.zusatzaufgabeGui.windows.lightWindows.DirectionalLightWindow;
import ourWindow.zusatzaufgabeGui.windows.lightWindows.PointLightWindow;
import ourWindow.zusatzaufgabeGui.windows.lightWindows.SpotLightWindow;
import raytracer.ImageLoader;
import raytracer.ImageSaver;
import raytracer.Ray;
import raytracer.World;
import camera.Camera;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * The TodessternGUI Class opens a window thanks to the implemented JavaFX
 * application and gives the user the oportunity to
 * create his own world containing objects such as world, camera and geometry.
 *
 * @author Julian dobrot
 */

public class TodessternGUI extends Application {

    /**
     * THe World component.
     */
    public static World welt;

    /**
     * The camera component.
     */
    public static Camera cam;



    /**
     * Drawing Surface:
     */
    private final VBox root = new VBox();
    private ImageView imageview;
    private WritableImage writableimage;
    private ProgressBar progress = new ProgressBar(0);
    private Label lab = new Label();
    protected static long startTime;
    private static int iterations=0;


    /**
     * The start method initializes the window property at initial point. The
     * title is added and its initial size is set.
     */

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("TodessternRaytracer");
        primaryStage.setMinHeight(51);
        primaryStage.setWidth(640);
        primaryStage.setHeight(480);
        primaryStage.setResizable(false);
        initializeMenu(primaryStage);

        primaryStage.show();
        progress.setMinWidth(640);


    }

    /**
     * The drawPicture method draws a picture pixel by pixel using a
     * writableImage which is stored in the imageview.
     *
     * @param primaryStage
     *            is needed to get the width and height property for the image
     *            to be drawn
     *
     * In order to be able to draw a completely new picture each time
     * the method is called, we first need to remove the earlier
     * added imageview from the VBox. The width and height property
     * cannot be bound directly because neither the writableimage nor
     * the imageview provides a property binding method.
     */
    public void drawPicture(Stage primaryStage) throws InterruptedException {



        root.getChildren().remove(imageview);

        final int height = (int) primaryStage.getHeight() - 50;
        final int width = (int) primaryStage.getWidth();

        this.imageview = new ImageView();
        this.writableimage = new WritableImage(width, height);


        final PixelWriter writer = writableimage.getPixelWriter();



        try {
            for (int y = 0; y < height; ++y) {
                for (int x = 0; x < width; ++x) {
                 
                    writer.setColor(x, y, getColor(width, height, x, y));
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Das Fenster ist zu klein um ein Bild zu Zeichnen." + e.getMessage());
        }



        imageview.setImage(writableimage);
        root.getChildren().add(imageview);
        primaryStage.onCloseRequestProperty().set(e->backToMenu(primaryStage));
    }
//        try {
//            for (int y = 0; y < height; ++y) {
//                final int y1;
//                y1=y;
//
//                Task<Void> task = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//
//                       for (int x = 0; x < width; ++x) {
//
//                            iterations++;
//                            final int iterations1;
//                            final int x1;
//
//
//
//                            long elapsedTime = (System.currentTimeMillis() - TodessternGUI.startTime) / 1000;
//                            long estimatedTime = (long)((double)elapsedTime / TodessternGUI.iterations * (width*height));
//
//
//                            x1=x;
//
//                            iterations1 = iterations;
//
//                            Platform.runLater(() -> updateProgress(iterations1, width * height));
//                            Platform.runLater(() -> writer.setColor(x1, y1, getColor(width, height, x1, y1)));
//                            Platform.runLater(() -> updateMessage("elapsed Time: " + elapsedTime + "sec. estimated Time: " + estimatedTime + " sec."));
//                            Thread.sleep(100);
//
//
//                        }
//
//                return null;
//            }
//        };
//                startTime = System.currentTimeMillis();
//                progress.progressProperty().bind(task.progressProperty());
//                lab.textProperty().bind(task.messageProperty());
//
//                Thread t = new Thread(task);
//                t.setDaemon(true);
//                t.start();
//            }
//        } catch (IllegalArgumentException e) {
//            System.out.println("Das Fenster ist zu klein um ein Bild zu Zeichnen." + e.getMessage());
//        }
//
//
//
//
//
//        imageview.setImage(writableimage);
//        root.getChildren().add(imageview);
//        primaryStage.onCloseRequestProperty().set(e->backToMenu(primaryStage));
//    }


    private void backToMenu (Stage primaryStage) {
        this.root.getChildren().remove(imageview);
    }

    /**
     * The getColor method is called in order to decide which color to give the
     * pixel at the given point (x,y).
     *
     * @param x > 0 && y > 0 coordinate of the pixel which is drawn in this moment
     * @param width > 0 && height > null, both represent the size of the window -
     * @return Color object either gets the color from the geometry, which was hit
     * or the background color of the world.
     */
    private Color getColor (int width , int height, int x, int y) throws IllegalArgumentException {

        raytracer.Color hitFarbe;
        raytracer.Color addFarbe = new raytracer.Color(0,0,0);


        Set<Ray> rays = cam.rayFor(width, height, x, height - 1 - y);

        for ( Ray r : rays) {

            hitFarbe = welt.hit(r);


            if (hitFarbe == null) throw new IllegalArgumentException("color has to be not null");

            if (hitFarbe.r < 0) {
                hitFarbe.r = 0;
            }
            if (hitFarbe.r > 1) {
                hitFarbe.r = 1;
            }
            if (hitFarbe.g < 0) {
                hitFarbe.g = 0;
            }
            if (hitFarbe.g > 1) {
                hitFarbe.g = 1;
            }
            if (hitFarbe.b < 0) {
                hitFarbe.b = 0;
            }
            if (hitFarbe.b > 1) {
                hitFarbe.b = 1;
            }
            addFarbe = addFarbe.add(hitFarbe);

        }

        //divide color with pixels of grid
        addFarbe = addFarbe.mul(1f/rays.size());

        if (addFarbe.r < 0) {
            addFarbe.r = 0;
        }
        if (addFarbe.r > 1) {
            addFarbe.r = 1;
        }
        if (addFarbe.g < 0) {
            addFarbe.g = 0;
        }
        if (addFarbe.g > 1) {
            addFarbe.g = 1;
        }
        if (addFarbe.b < 0) {
            addFarbe.b = 0;
        }
        if (addFarbe.b > 1) {
            addFarbe.b = 1;
        }
        //System.out.println(addFarbe);
        return new Color(addFarbe.r, addFarbe.g, addFarbe.b, 1);
    }

    /**
     * Initializes the menubar.
     * @param primaryStage needs to be given in oder to call the saveFile method in the lamda expression
     */
    private void initializeMenu (Stage primaryStage) {

        final MenuBar menubar = new MenuBar();
        final Menu fileMenu = new Menu("File");
        final Menu geometries = new Menu("Geometries");
        final Menu camera = new Menu("Camera");
        final Menu render = new Menu("Render");
        final Menu light = new Menu("Light");
        final Menu worldMenu = new Menu("World");
        final Menu demo = new Menu("Demo Scenes");
        final Menu objLoader = new Menu("ObjLoader");


        final MenuItem world = new MenuItem("New");

        final MenuItem cameraMenu = new MenuItem("Select Camera");


        final MenuItem plane = new MenuItem("Plane");
        final MenuItem sphere = new MenuItem("Sphere");
        final MenuItem triangle = new MenuItem("Triangle");
        final MenuItem box = new MenuItem("Box");

        final MenuItem save = new MenuItem("Save");
        final MenuItem load = new MenuItem("LOAD");

        final MenuItem renBut = new MenuItem("GO");

        final MenuItem pointLight = new MenuItem("Point Light");
        final MenuItem spotLight = new MenuItem("Spot Light");
        final MenuItem directionalLight = new MenuItem("directional Light");


        final MenuItem abbildung5_beleuchtung_II = new MenuItem("Abbildung 5 Beleuchtung II - Refraktion");
        final MenuItem abbildung3_beluechtung_II = new MenuItem("Abbildung 3 Beleuchtung II - Kugeln auf Ebene");
        final MenuItem abbildung4_beleuchtung_II = new MenuItem("Abbildung 4 Beleuchtung II - Rote Box auf Ebene");


        final MenuItem abbildung1_transformationen = new MenuItem("Abbildung 1 Transformationen - Transformierte kugel");
        final MenuItem abbildung2_transformationen = new MenuItem("Abbildung 2 Transformationen - Transformierte Box");

        final MenuItem eigeneSzene_zusatzaufgabe_imageTexture = new MenuItem("Eigene Szene - Zusatzaufgabe imageTexture");

        final MenuItem shapeFRomFile = new MenuItem("Files");

        final  MenuItem imageTexture_earth = new MenuItem("imageTexture_Earth");
        final  MenuItem imageTexture_downsampled320 = new MenuItem("imageTextture_downsampled320");
        final  MenuItem imageTexture_downsampled320_interpolated =
                new MenuItem("imageTextture_downsampled320_interpolated");

        final MenuItem saturn = new MenuItem("Saturn");
        final MenuItem verketteteT = new MenuItem("Verkettete_Transformationen");

        final MenuItem kamera_vektoren = new MenuItem("Kamera_Vektoren_bestimmen");
        menubar.getMenus().addAll(fileMenu,worldMenu,geometries,camera,render,light,demo,objLoader);

        fileMenu.getItems().addAll(save, load);
        worldMenu.getItems().add(world);
        geometries.getItems().addAll(plane,sphere,triangle,box);
        camera.getItems().addAll(cameraMenu);
        light.getItems().addAll(pointLight,spotLight,directionalLight);
        objLoader.getItems().add(shapeFRomFile);

        demo.getItems().addAll(abbildung5_beleuchtung_II, abbildung3_beluechtung_II, abbildung4_beleuchtung_II,
                abbildung1_transformationen, abbildung2_transformationen,eigeneSzene_zusatzaufgabe_imageTexture,
                imageTexture_earth,imageTexture_downsampled320,imageTexture_downsampled320_interpolated,saturn,verketteteT,kamera_vektoren);

        render.getItems().add(renBut);

        pointLight.setOnAction(e->new PointLightWindow());
        spotLight.setOnAction(e->new SpotLightWindow());
        directionalLight.setOnAction(e->new DirectionalLightWindow());

        world.setOnAction(e -> new WorldWindow(primaryStage));
        camera.setOnAction(e -> new CameraWindow());
        plane.setOnAction(e -> new PlaneWindow(primaryStage));
        sphere.setOnAction(e-> new SphereWindow());
        triangle.setOnAction(e -> new TriangleWindow());
        box.setOnAction(e -> new BoxWindow());

        abbildung1_transformationen.setOnAction(e -> new DemoScenes().abbildung1_transformationen());
        abbildung2_transformationen.setOnAction(e -> new DemoScenes().abbildung2_transformationen());
        abbildung3_beluechtung_II.setOnAction(e -> new DemoScenes().abbildung3_beleuchtung_II());
        abbildung4_beleuchtung_II.setOnAction(e -> new DemoScenes().abbildung4_beleuchtung_II());
        abbildung5_beleuchtung_II.setOnAction(e -> new DemoScenes().abbildung5_beleuchtung_II());
        eigeneSzene_zusatzaufgabe_imageTexture.setOnAction(e -> new DemoScenes().additional_ImageTextureScene());
        imageTexture_earth.setOnAction( e -> new DemoScenes().imageTextur_Earth());
        imageTexture_downsampled320.setOnAction(e -> new DemoScenes().imageTextur_downSapled340());
        imageTexture_downsampled320_interpolated.setOnAction(e -> new DemoScenes().imageTextur_downSapled340_interpolated());

        saturn.setOnAction(e -> new DemoScenes().saturn());
        verketteteT.setOnAction(e -> new DemoScenes().verkettete_Rotationen());
        shapeFRomFile.setOnAction(e -> new ObjWindow());

        kamera_vektoren.setOnAction(e-> new DemoScenes().bestimme_Kameravektoren());
        renBut.setOnAction(e -> {
            try {
                drawPicture(primaryStage);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        save.setOnAction(e -> saveFile(primaryStage));
        load.setOnAction(e -> new ImageLoader());






        root.getChildren().addAll(progress,lab,menubar);
    }

    /**
     * Initializes the save dialog window and gives the user the possibility to
     * save the drawn picture as jpg or png. Before being able to save it, we
     * need to transform the writableimage into a bufferedimage.
     */
    private void saveFile(Stage primaryStage) {

        final FileChooser fileChooser = new FileChooser();
        final ExtensionFilter png = new ExtensionFilter("PNG File", "*.png");
        final ExtensionFilter jpg = new ExtensionFilter("JPG File", "*.jpg");

        fileChooser.getExtensionFilters().addAll(png, jpg);
        fileChooser.setTitle("Save Image");
        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {

            String fileName = file.getName();
            BufferedImage buff = SwingFXUtils.fromFXImage(writableimage, null);

            if (fileName.contains("png")) {
                try {
                    ImageIO.write(buff, "png", file);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Bild kann nicht gespeichert werden. Fehler: " + e.getMessage());
                }
            }

            else {
                BufferedImage image = new BufferedImage(buff.getWidth(), buff.getHeight(),
                        BufferedImage.TYPE_BYTE_INDEXED);
                Graphics2D graphics = image.createGraphics();
                graphics.drawImage(buff, 0, 0, null);
                try {
                    ImageIO.write(image, "jpg", file);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Bild kann nicht gespeichert werden. Fehler: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}