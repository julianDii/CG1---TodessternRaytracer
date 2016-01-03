package texture;

import raytracer.Color;

/**
 * This is the base class for all textures.
 * Created by Juliand on 03.01.16.
 */
public abstract class Texture {

    /**
     * This methos calculates the color for a certain position.
     * @param u The u value of the mapped texture.
     * @param v The v value of the mapped texture.
     * @return The color for the given u,v from the method.
     */

    public abstract Color colorFor(final double u,final double v);


}
