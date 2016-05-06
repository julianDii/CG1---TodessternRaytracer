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

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Juliand on 12.01.16.
 */
public class ImageLoader extends Stage {

    public ImageLoader () {




        final BorderPane borderPane = new BorderPane();
        Scene loadedImageScene = new Scene(borderPane);

        final ImageView imageView;

        Image image = null;
        final File file = openFileDialog(this);

        if (file == null){
            return;
        }

        try {
            image = new Image(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        final double img_height = image.getHeight();
        final double img_width = image.getWidth();

        this.setTitle("ImageViewer");
        imageView = new ImageView(image);
        borderPane.setCenter(imageView);

        this.setWidth(image.getWidth());
        this.setHeight(image.getHeight());
        this.setTitle(file.getName());
        this.setScene(loadedImageScene);
        this.initModality(Modality.APPLICATION_MODAL);
        this.showAndWait();






    }
    /**
     * The openFileDialog method open a Window to choose a File as PNG or JPG

     * @return 	the chosen File
     */
    private File openFileDialog(Stage primaryStage) {
        final FileChooser fileChooser = new FileChooser();
        final FileChooser.ExtensionFilter png = new FileChooser.ExtensionFilter("PNG File", "*.png");
        final FileChooser.ExtensionFilter jpg = new FileChooser.ExtensionFilter("JPG File", "*.jpg");

        fileChooser.getExtensionFilters().addAll(png,jpg);

        final File file = fileChooser.showOpenDialog(this);

        return file;
    }
}
