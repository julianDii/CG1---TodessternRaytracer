/**
 * 
 */
package raytracer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.*;
import javafx.scene.Parent;
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

	public void start(Stage primaryStage) throws Exception {
		final ImageView imageView;

		primaryStage.setTitle("ImageViewer");

		final FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images","*.png"));
		final File file = fileChooser.showOpenDialog(primaryStage);

		// Check if File chosen
		if (file == null)
			return;
		
		// Optional mit URI, dann ohne FileInputStream()
		// final File file =
		// fileChooser.showOpenDialog(primaryStage).toURI().toString();
		final Image image = new Image(new FileInputStream(file));
		// imageView = createImageView(file);
		imageView = new ImageView(image);
		final BorderPane borderPane = new BorderPane();
		borderPane.setCenter(imageView);
		Scene scene = new Scene(borderPane, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
