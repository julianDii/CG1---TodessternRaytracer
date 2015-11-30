package ourWindow.zusatzaufgabeGui;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
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
import raytracer.World;
import raytracer.camera.Camera;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The TodessternGUI Class opens a window thanks to the implemented JavaFX
 * application and gives the user the oportunity to
 * create his own world containing objects such as world, camera and geometry.
 * The window size is editable by the user.
 *
 * @author Charline Waldrich,Julian dobrot
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
        initializeMenu(primaryStage);

        primaryStage.show();

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
    public void drawPicture(Stage primaryStage) {

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
    private Color getColor(int width, int height, int x, int y) throws IllegalArgumentException {

        if (y > height || x > width) throw new IllegalArgumentException("Etwas stimmt mit der Höhe und Breite nicht.");
        raytracer.Color hitFarbe = welt.hit(cam.rayFor(width, height, x, height - 1 - y));

        if (hitFarbe.r<0){hitFarbe.r=0;}
        if (hitFarbe.r>1){hitFarbe.r=1;}
        if (hitFarbe.g<0){hitFarbe.g=0;}
        if (hitFarbe.g>1){hitFarbe.g=1;}
        if (hitFarbe.b<0){hitFarbe.b=0;}
        if (hitFarbe.b>1){hitFarbe.b=1;}

        return new Color(hitFarbe.r, hitFarbe.g, hitFarbe.b, 1);
    }

    /**
     * Initializes the menubar.
     * @param primaryStage needs to be given in oder to call the saveFile method in the lamda expression
     */
    private void initializeMenu(Stage primaryStage) {

        final MenuBar menubar = new MenuBar();
        final Menu fileMenu = new Menu("File");
        final Menu geometries = new Menu("Geometries");
        final Menu camera = new Menu("Camera");
        final Menu render = new Menu("Render");
        final Menu light = new Menu("Light");
        final Menu worldMenu = new Menu("World");


        final MenuItem world = new MenuItem("New");

        final MenuItem cameraMenu = new MenuItem("Select Camera");


        final MenuItem plane = new MenuItem("Plane");
        final MenuItem sphere = new MenuItem("Sphere");
        final MenuItem triangle = new MenuItem("Triangle");
        final MenuItem box = new MenuItem("Box");

        final MenuItem save = new MenuItem("Save");

        final MenuItem renBut = new MenuItem("GO");

        final MenuItem pointLight = new MenuItem("Point Light");
        final MenuItem spotLight = new MenuItem("Spot Light");
        final MenuItem directionalLight = new MenuItem("directional Light");

        menubar.getMenus().addAll(fileMenu,worldMenu,geometries,camera,render,light);

        fileMenu.getItems().add(save);
        worldMenu.getItems().add(world);
        geometries.getItems().addAll(plane,sphere,triangle,box);
        camera.getItems().addAll(cameraMenu);
        light.getItems().addAll(pointLight,spotLight,directionalLight);
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

        renBut.setOnAction(e->drawPicture(primaryStage));
        save.setOnAction(e -> saveFile(primaryStage));


        root.getChildren().addAll(menubar);
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