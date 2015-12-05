package material;
import raytracer.Color;
import raytracer.Ray;
import raytracer.World;
import raytracer.geometrie.Geometry;
import raytracer.geometrie.Hit;

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
    private final World world;


    /**
     * This contructor builds a new Tracer.
     * @param depth
     * @param world
     */
    public Tracer(int depth, World world) {

        if (world==null)throw new IllegalArgumentException("World has to be not null");

        this.world = world;
        this.depth = depth;


    }

    /**
     * This method calculates the color of a reflection.
     * @param r
     * @return The reflection color.
     */

    public Color reflectedColor(Ray r) {

        if (r == null) {throw new IllegalArgumentException("The ray can not be null");}

        if (depth <= 0) {
            return world.backgroundcolor;
        }

        Hit h = null;
        double untereGrenze= 0.00001;
        double t2=0;
        for (Geometry g : world.list) {
            h = g.hit(r);

            if(h!=null){
                t2=h.t;
            }

            if (h != null && t2>=untereGrenze){
                return h.geo.material.colorFor(h, world, new Tracer(depth - 1, world));
            }

        }
        return world.backgroundcolor;
    }
}
