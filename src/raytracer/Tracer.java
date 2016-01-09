package raytracer;

/**
 * This class represents a Tracer for recursive ray tracing.
 * Created by Juliand on 02.12.15.
 */
public class Tracer {

    /**
     * The depth component of the Tracer.
     */
    public int depth;

    /**
     * The world component of the Tracer.
     */
    public final World world;



    /**
     * This constructor builds a new Tracer.
     *
     * @param depth Tracer depth component.
     * @param world Tracer world component
     */
    public Tracer(final int depth, final  World world) {

        if (depth < 0) throw new IllegalArgumentException("depth has to be not smaller then 0");
        if (world == null) throw new IllegalArgumentException("World has to be not null");


        this.depth = depth;
        this.world = world;


    }

    /**
     * This method calculates the color of a reflection.
     *
     * @param r the given ray.
     * @return The reflection color.
     */

    public Color colorFor (final Ray r) {

        if (r == null)throw new IllegalArgumentException("The ray cannot be null!");
        if (depth <= 0) {

            return world.backgroundcolor;

        } else {

            double t2 = 0.0;

            Hit hit = world.hitt(r);

            if(hit != null) {

                t2=hit.t;

            }

            if (hit != null && t2 >= Constants.EPSILON) {

                return hit.geo.material.colorFor(hit, world, new Tracer(depth-1,world));

            } else {

                return world.backgroundcolor;

            }
        }
    }


}
