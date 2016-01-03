package texture;

import raytracer.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class loads an image as texture for a geometry.
 * To use this u have to: 1. create a property file in your directory (raytracer-todesstern).
 * 2. Write BASE_URL="/Users/..here you have to enter your specific path to the img folder.../src/assets/img/"
 * Created by Juliand on 03.01.16.
 */
public class ImageTexture extends Texture {

    /**
     * The empty String hull for the path to the img folder loaded from the properties.
     */
    private static  String BASE_URL_IMG = "";

    /**
     * The properties object to load individual base paths for different users.
     */
    private static Properties properties = new Properties();


    /**
     * The texture color.
     */
    public final Color color;

    /**
     * The image component.
     */
    public BufferedImage image;


    /**
     * This constructor builds a new ImageTexture.
     * @param color
     * @param imgName
     */
    public ImageTexture(final Color color, final String imgName){
        if (color==null)throw new IllegalArgumentException("color has to be not null");
        if (imgName==null)throw new IllegalArgumentException("imgName has to be not null");

        this.color = color;
        this.image = null;
        try {

            properties.load(new FileInputStream("todesstern.properties"));
            BASE_URL_IMG = properties.get("BASE_URL_IMG").toString();

            this.image = ImageIO.read(new File(BASE_URL_IMG + imgName));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method calculates the color for a given u,v
     * @param u The u value of the mapped texture.
     * @param v The v value of the mapped texture.
     * @return The color for the given u,v.
     */

    @Override
    public Color colorFor(double u, double v) {

         double coo1 = ImageTexture.getRelCoord(u);
         double coo2 = ImageTexture.getRelCoord(v);
         double x = (this.image.getWidth() - 1) * coo1;
         double y = (this.image.getHeight() - 1) - ((image.getHeight() - 1) * coo2);

        return ImageTexture.getPositionColor(this.image,(int) Math.round(x), (int) Math.round(y));
    }
    public static double getRelCoord(final double in) {
        double out = in % 1.0;
        if (out < 0.0) {
            out = out + 1.0;
        }
        return out;
    }


    public static Color getPositionColor(final BufferedImage image, final int x, final int y) {



        final java.awt.Color c = new java.awt.Color(image.getRGB(x, y));
        double e = 0.00001;
        return new Color((c.getRed()+e)/255,((c.getGreen()+e)/255),((c.getBlue()+e)/255));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageTexture that = (ImageTexture) o;

        if (!color.equals(that.color)) return false;
        return image.equals(that.image);

    }

    @Override
    public int hashCode() {
        int result = color.hashCode();
        result = 31 * result + image.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ImageTexture{" +
                "color=" + color +
                ", image=" + image +
                '}';
    }
}
