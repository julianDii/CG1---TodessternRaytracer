/**
 * 
 */
package raytracer;

import java.io.File;

import javax.imageio.ImageWriter;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;




/**
 * The Image Saver Class opens a window thanks to the JavaFX application and generates a black image pixel
 * by pixel with a diagonal red line. The window size should be editable by the user. 
 * 
 * 
 * @author Charline Waldrich, Robert Ullmann, Julian Dobrot
 *
 */
public class ImageSaver extends Application {
	
	
	/**
	 * Drawing Surface 	
	 */
	private Group root; 
	private ImageView imageview;
	private WritableImage writableImage;
		
	public void start(Stage primaryStage) throws Exception {	
		/**
		 * Window property at initial point. 
		 */		
		imageview = new ImageView();
		root = new Group();
		root.getChildren().add(imageview);
		
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Image Saver");
		primaryStage.setWidth(640);
		primaryStage.setHeight(480);

		initializeMenu(primaryStage);
		primaryStage.show();
	}

	
	
	public void drawPicture(Stage primaryStage){
	
		writableImage = new WritableImage( (int) primaryStage.getHeight(), (int) primaryStage.getWidth());
		
		
	}

	/**
	 * Initializes the Menubar. 
	 */
	public void initializeMenu(Stage primaryStage){

		final MenuBar menubar = new MenuBar(); 
		final Menu fileMenu = new Menu("File");
		final MenuItem save = new MenuItem("Save");

		fileMenu.getItems().add(save);		
		menubar.getMenus().add(fileMenu);
		root.getChildren().add(menubar);

		save.setOnAction(e -> saveFile(primaryStage));
	}
	
	/**
	 * Initializes the Save Dialog window and gives the possibility to save the drawn picture as 
	 * jpg or png. 
	 */
	public void saveFile(Stage primaryStage){
		FileChooser fileChooser = new FileChooser();
		ExtensionFilter png = new ExtensionFilter("PNG File", "*.png");
		ExtensionFilter jpg = new ExtensionFilter("JPG File", "*.jpg");
		
		fileChooser.getExtensionFilters().addAll(png, jpg);
		fileChooser.setTitle("Save Image");
		File file = fileChooser.showSaveDialog(primaryStage);
		
		if (file == null ){ return; }
			
	}
	
	public static void main(String[] args) {
		launch(args);	
	}
	
}
