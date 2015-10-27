/**
 * 
 */
package raytracer;

import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.awt.Graphics2D;
import java.awt.color.*;

/**
 * The Image Saver Class opens a window thanks to the JavaFX application and
 * generates a black image pixel by pixel with a diagonal red line. The window
 * size should be editable by the user.
 * 
 * 
 * @author Charline Waldrich, Robert Ullmann, Julian Dobrot
 *
 */
public class ImageSaver extends Application {

	/**
	 * Drawing Surface
	 */
	private VBox root = new VBox();
	private ImageView imageview;
	private WritableImage writableimage;

	/**
	 * Window property at initial point. Title added as well as initial size is set. 
	 * We need to add a listener to the VBox in oder to use the lamda expressions to call the 
	 * drawPicture method each time the height and width property of the VBox changes.
	 */
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Image Saver");
		primaryStage.setWidth(640);
		primaryStage.setHeight(530);
		initializeMenu(primaryStage);
		
		root.widthProperty().addListener(e -> {
			drawPicture(primaryStage);
		});
		root.heightProperty().addListener(e ->{
			drawPicture(primaryStage);
		});

		primaryStage.show();
	}

	/**
	 * Draws a picture pixel by pixel using a writablePicture which is stored in a imageview. 
	 * 
	 * @param 	primaryStage is needed to get the width and height property for the 
	 * 			image to be drawn
	 *
	 * In order to be able to draw a completely new picture each time the method is called,
	 * we need to remove the earlier added imageview from the VBox. 
	 * The width and height property cannot be bound directly because neither the writableimage
	 * nor the imageview provides a property binding method. 
	 */
	public void drawPicture(Stage primaryStage) {
		
		root.getChildren().remove(imageview);
		
		int height = (int) primaryStage.getHeight() - 50;
		int width = (int) primaryStage.getWidth();

		this.imageview = new ImageView();
		this.writableimage = new WritableImage(width, height);
		
		PixelWriter writer = writableimage.getPixelWriter();

		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {

				writer.setColor(x, y, getColor(x, y));
			}
		}

		imageview.setImage(writableimage);
		root.getChildren().add(imageview);
	}

	/**
	 * The getColor method is called in order to decide which color to give the pixel at
	 * the given point (x,y).
	 * @param 	x: x coordinate of the picture
	 * @param 	y: y coordinate of the picture
	 * @return 	Color RED for each pixel where x and y are the same in order to draw a 
	 * 			red diagonal 
	 * @return 	Color BLACK for every other 
	 */
	public Color getColor(int x, int y) {
		if (x == y) { return Color.RED; }
		return Color.BLACK;
	}

	/**
	 * Initializes the menubar with the MenuItem Save and Draw.
	 * @param Stage needs to be given in oder to call the saveFile method in the lamda expression
	 */
	public void initializeMenu(Stage primaryStage) {

		final MenuBar menubar = new MenuBar();
		final Menu fileMenu = new Menu("File");
		final MenuItem save = new MenuItem("Save");

		fileMenu.getItems().add(save);
		menubar.getMenus().add(fileMenu);
		save.setOnAction(e -> saveFile(primaryStage));

		root.getChildren().add(menubar);
	}

	/**
	 * Initializes the save dialog window and gives the user the possibility to save the
	 * drawn picture as jpg or png.
	 * Before being able to save it, we need to transform the writableimage into a bufferedimage.
	 */
	public void saveFile(Stage primaryStage) {
		final FileChooser fileChooser = new FileChooser();
		final ExtensionFilter png = new ExtensionFilter("PNG File", "*.png");
		final ExtensionFilter jpg = new ExtensionFilter("JPG File", "*.jpg");

		fileChooser.getExtensionFilters().addAll(png, jpg);
		fileChooser.setTitle("Save Image");
		File file = fileChooser.showSaveDialog(primaryStage);

		if (file != null) {

			String fileName = file.getName();
			BufferedImage buff =  SwingFXUtils.fromFXImage(writableimage, null);

			if (fileName.contains("png")) {
				try {
					ImageIO.write(buff, "png", file);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Bild kann nicht gespeichert werden. Fehler: " + e.getMessage());
				}
			}

			else {
				BufferedImage image = new BufferedImage(buff.getWidth(), buff.getHeight(), BufferedImage.TYPE_BYTE_INDEXED);
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
