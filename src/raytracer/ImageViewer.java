/*
 * MIT License
 *
 * Copyright (c) 2016 Julian Dobrot
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
import javafx.stage.FileChooser.ExtensionFilter;
import ourWindow.zusatzaufgabeGui.windows.TodessternGUI;

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
	 * to show the chosen Image in a ImageView. 
	 * If no File chosen, no Window is shown.
	 */
	public void start(Stage primaryStage) throws Exception {
		final ImageView imageView;
		final BorderPane borderPane = new BorderPane();
		final Image image ;
		final File file = openFileDialog(primaryStage);
		
		if (file == null){
			return;
		}
		
		image = new Image(new FileInputStream(file));
		
		primaryStage.setTitle("ImageViewer");				
		imageView = new ImageView(image);		
		borderPane.setCenter(imageView);
		Scene scene = new Scene(borderPane, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	/**
	 * The openFileDialog method open a Window to choose a File as PNG or JPG
	 * @param 	primaryStage to show the FileChooser
	 * @return 	the chosen File 
	 */
	private File openFileDialog(Stage primaryStage) {
		final FileChooser fileChooser = new FileChooser();
		final ExtensionFilter png = new ExtensionFilter("PNG File", "*.png");
		final ExtensionFilter jpg = new ExtensionFilter("JPG File", "*.jpg");
				
		fileChooser.getExtensionFilters().addAll(png,jpg);
		
		final File file = fileChooser.showOpenDialog(primaryStage);

		return file;		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
