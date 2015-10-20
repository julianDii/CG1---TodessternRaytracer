/**
 * 
 */
package raytracer;

import javafx.application.Application;
import javafx.scene.image.*;
import javafx.stage.Stage;

/**
 * The Image Saver Class opens a window thanks to the JavaFX application and generates a black image pixel
 * by pixel with a diagonal red line. The window size should be editable by the user. 
 * 
 * 
 * @author Charline Waldrich
 *
 */
public class ImageSaver extends Application {
	

	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Image Saver");
		primaryStage.setWidth(640);
		primaryStage.setHeight(480);
		primaryStage.show();
		
	}

	public void draw(){
		
		
	}
	
	public static void main(String[] args) {
		launch(args);	
	}
	
}
