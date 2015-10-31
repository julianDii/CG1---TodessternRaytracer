/**
 * 
 */
package raytracer;

import java.io.File;
import java.io.FileInputStream;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * The class ImageViewer opens a window thanks to the FX Application and asks
 * the User to choose an image from his disk. After he has decided which, the
 * window opens the same size as the original picture.
 * 
 * @author Robert Ullmann
 *
 */
public class ImageViewer extends Application {
	
	/**
	 * Start method for FX-Window with BorderPane and ImageView, 
	 * to show the choosen Image in a ImageView. 
	 */
	public void start(Stage primaryStage) throws Exception {
		final ImageView imageView;
		final BorderPane borderPane = new BorderPane();
		final Image image = new Image(new FileInputStream(openFileDialog(primaryStage)));

		primaryStage.setTitle("ImageViewer");				
		imageView = new ImageView(image);		
		borderPane.setCenter(imageView);
		Scene scene = new Scene(borderPane, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * The openFileDialog method open a Window to choose a File as PNG 
	 * @param 	primaryStage to show the FileChooser
	 * @return 	the choosen File 
	 */
	private File openFileDialog(Stage primaryStage) {
		final FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images","*.png"));
		final File file = fileChooser.showOpenDialog(primaryStage);

		return file;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
