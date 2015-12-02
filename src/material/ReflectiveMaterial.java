package material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometrie.Hit;

/**
 * This class represents a reflective material.
 * Created by Juliand on 02.12.15.
 */
public class ReflectiveMaterial extends Material {

    /**
     * The diffuse component of the material.
     */
    private final Color diffuse;

    /**
     * The specular component of the material.
     */
    private final Color specular;

    /**
     * The exponent component of the material
     */
    private final int exponent;

    /**
     * The reflection component of the material.
     */
    private final Color reflection;

    /**
     * This constructor builds a Reflective material.
     * @param diffuse
     * @param specular
     * @param exponent
     * @param reflection
     */

    public ReflectiveMaterial(final Color diffuse, final Color specular, final int exponent, final Color reflection ){

        this.diffuse=diffuse;
        this.specular=specular;
        this.exponent=exponent;
        this.reflection=reflection;
    }

    /**
     * This method calculates the color for the certain pixel if there is a hit.
     * @param hit
     * @param world
     * @param tracer
     * @return
     */
    public Color colorFor(Hit hit, World world, Tracer tracer) {
        return null;
    }
}
