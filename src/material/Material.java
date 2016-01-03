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
     * This method calculates the color for hits with the material.
     * @param hit The hit object.
     * @param world The world object.
     * @return The implementations of this method are returning the color for an hit object.
     */
    public abstract Color colorFor(final Hit hit, final World world,final Tracer tracer);



}
