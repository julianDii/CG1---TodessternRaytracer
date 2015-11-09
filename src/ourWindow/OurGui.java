package ourWindow;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
import raytracer.camera.PerspectiveCamera;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * OurGui Class opens a window thanks to the implemented JavaFX
 * application and gives the user the oportunity to 
 * create his own world containing objects such as world, camera and geometry.
 * The window size is editable by the user.
 * 
 * @author Charline Waldrich
 */
public class OurGui extends Application {

	/**
	 * Drawing Surface:
	 */
	public final static World welt = new World(new raytracer.Color(0, 0, 0));
	public final Camera camera = new PerspectiveCamera(new Point3(1,1,1), new Vector3(2,2,2), new Vector3(3,3,3), 0);
	private final VBox root = new VBox();
	private ImageView imageview;
	private WritableImage writableimage;

	/**
	 * The start method initializes the window property at initial point. The
	 * title is added and its initial size is set.
	 */
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Welcome");
		primaryStage.setMinHeight(51);
		primaryStage.setWidth(640);
		primaryStage.setHeight(480);
		initializeMenu(primaryStage);

		// root.widthProperty().addListener(e -> {
		// drawPicture(primaryStage);
		// });
		// root.heightProperty().addListener(e ->{
		// drawPicture(primaryStage);
		// });

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
	private void drawPicture(Stage primaryStage) {

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
		if (y > height || x > width) throw new IllegalArgumentException();
		raytracer.Color hitFarbe = welt.hit(camera.rayFor(width, height, x, y));
		return new Color(hitFarbe.r, hitFarbe.g, hitFarbe.b, 1);
	}

	/**
	 * Initializes the menubar.
	 * @param Stage needs to be given in oder to call the saveFile method in the lamda expression
	 */
	private void initializeMenu(Stage primaryStage) {

		final MenuBar menubar = new MenuBar();
		final Menu fileMenu = new Menu("File");
		final Menu addTo = new Menu("Add to window");
		final MenuItem world = new MenuItem("New world");
		final MenuItem camera = new MenuItem("New camera");
		final MenuItem plane = new MenuItem("New plane");
		final MenuItem sphere = new MenuItem("New sphere");
		final MenuItem triangle = new MenuItem("New triangle");
		final MenuItem box = new MenuItem("New axis aligned box");		
		final MenuItem save = new MenuItem("Save");

		addTo.getItems().addAll(world, camera, plane, sphere, triangle, box);
		fileMenu.getItems().add(save);
		menubar.getMenus().addAll(fileMenu, addTo);
		
		world.setOnAction(e -> new GetWorldWindow());
		camera.setOnAction(e -> new GetCameraWindow());
		plane.setOnAction(e -> new GetPlaneWindow());
		sphere.setOnAction(e-> new GetSphereWindow());
		triangle.setOnAction(e -> new GetTriangleWindow());
		box.setOnAction(e -> new GetBoxWindow());
		
		save.setOnAction(e -> saveFile(primaryStage));

		root.getChildren().add(menubar);
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