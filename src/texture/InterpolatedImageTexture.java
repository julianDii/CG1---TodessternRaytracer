package texture;

import raytracer.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class represents a InterpolatedImageTexture.
 * Created by Juliand on 11.01.16.
 */
public class InterpolatedImageTexture extends Texture{

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
     * This constructor builds a new InterpolatedImageTexture.
     * @param color The color component.
     * @param imgName The image component.
     */

    public InterpolatedImageTexture (final Color color, final String imgName) {

        this.color = color;
        this.image = null;

        try{

            properties.load(new FileInputStream("todesstern.properties"));
            BASE_URL_IMG = properties.get("BASE_URL_IMG").toString();
            this.image = ImageIO.read(new File(BASE_URL_IMG + imgName));

        } catch (IOException e) {

        }

    }

    /**
     * This method calculates the color for the given u,v.
     * @param u The u value of the mapped texture.
     * @param v The v value of the mapped texture.
     * @return The calculated color.
     */
    @Override
    public Color colorFor(final double u, final double v) {

        final double co1 = ImageTexture.getRelCoord(u);
        final double co2 = ImageTexture.getRelCoord(v);

        final double x = (this.image.getWidth() - 1) * co1;
        final double y = (this.image.getHeight() - 1) - ((image.getHeight() - 1) * co2);

        final double xInterpolated = x - Math.floor(x);
        final double yInterpolated = y - Math.floor(y);

        final Color cFF = ImageTexture.getPositionColor(this.image, (int) Math.floor(x), (int) Math.floor(y));
        final Color cFC = ImageTexture.getPositionColor(this.image, (int) Math.ceil(x), (int) Math.floor(y));
        final Color cCF = ImageTexture.getPositionColor(this.image, (int) Math.floor(x), (int) Math.ceil(y));
        final Color cCC = ImageTexture.getPositionColor(this.image, (int) Math.ceil(x), (int) Math.ceil(y));

        final Color cFFFC = cFF.mul(1.0 - xInterpolated).add(cFC.mul(xInterpolated));
        final Color cCFCC = cCF.mul(1.0 - xInterpolated).add(cCC.mul(xInterpolated));

        return cFFFC.mul(1.0 - yInterpolated).add(cCFCC.mul(yInterpolated));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterpolatedImageTexture that = (InterpolatedImageTexture) o;

        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        return !(image != null ? !image.equals(that.image) : that.image != null);

    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InterpolatedImageTexture{" +
                "color=" + color +
                ", image=" + image +
                '}';
    }
}
