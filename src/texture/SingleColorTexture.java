package texture;

import raytracer.Color;

/**
 * This Texture simply gets the given color from the parameter.
 * Created by Juliand on 03.01.16.
 */
public class SingleColorTexture extends Texture {

    /**
     * The color component of the texture.
     */
    public final Color color;

    /**
     * This contructor builds a new SingleColorTexture with a color.
     * @param color The color for the Texture.
     */
    public SingleColorTexture(final Color color){
        this.color=color;
    }

    /**
     * This method gives every point of the mapped texture the color of the singleColorMAterial.
     * @param u The u value of the mapped texture.
     * @param v The v value of the mapped texture.
     * @return The color of the SingleColorMaterial.
     */
    @Override
    public Color colorFor(double u, double v) {
        return this.color;
    }


    @Override
    public String toString() {
        return "SingleColorTexture{" +
                "color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleColorTexture that = (SingleColorTexture) o;

        return !(color != null ? !color.equals(that.color) : that.color != null);

    }

    @Override
    public int hashCode() {
        return color != null ? color.hashCode() : 0;
    }
}
