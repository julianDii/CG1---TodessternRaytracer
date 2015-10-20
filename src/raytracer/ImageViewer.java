/**
 * 
 */
package raytracer;
import javafx.application.*;
import javafx.stage.Stage;

/**
 * The class ImageViewer opens a window thanks to the FX Application and asks the User to choose 
 * an image from his disk. After he has decided which, the window opens the same size as the 
 * original picture. 
 *  
 * @author Robert Ullman
 *
 */
public class ImageViewer extends Application {


	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("ImageViewer");
		
		
	}

	public static void main(String[] args) {
		launch(args);	
	}
	
}
