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

        this.world = world;
        this.depth = depth;

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

            return this.world.backgroundcolor;

        } else {

            double epsi = 0.000001;
            double t2 = 0.0;
            Hit hit = world.hitt(r);

            if(hit != null) {

                t2=hit.t;

            }

            if (hit != null && t2 >= epsi) {

                return hit.geo.material.colorFor(hit, world, new Tracer(depth-1,world));

            } else {

                return this.world.backgroundcolor;

            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tracer tracer = (Tracer) o;

        if (depth != tracer.depth) return false;
        return !(world != null ? !world.equals(tracer.world) : tracer.world != null);

    }

    @Override
    public int hashCode() {
        int result = depth;
        result = 31 * result + (world != null ? world.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tracer{" +
                "depth=" + depth +
                ", world=" + world +
                '}';
    }
}
