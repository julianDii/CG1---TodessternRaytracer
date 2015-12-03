package material;


import raytracer.Color;
import raytracer.World;
import raytracer.geometrie.Hit;

/**
 * This class is the base class for all materials
 * Created by Juliand on 17.11.15.
 */
public abstract class Material {

    /**
     * This is the abstract base class for all materials
     * The world is needed for calculating the lights.
     * @param hit
     * @param world
     * @return The implementations of this method are returning the color for an hit object.
     */
    public abstract Color colorFor(final Hit hit, final World world,final Tracer tracer);



}
