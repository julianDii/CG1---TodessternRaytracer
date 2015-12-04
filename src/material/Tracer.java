package material;

import raytracer.Color;
import raytracer.Ray;
import raytracer.World;
import raytracer.geometrie.Geometry;
import raytracer.geometrie.Hit;

/**
 * Created by Juliand on 02.12.15.
 */
public class Tracer {

    public static int depth;
    private final World world;
    private final Ray ray;

    public Tracer(Ray ray, int depth,World world){

        this.world = world;
        this.ray = ray;

    }
    public Color reflectedColor(Ray r, World world) {
        Hit h = null;
        if (depth <= 0) {
            return world.backgroundcolor;
        } else {
            for (Geometry g : world.list) {
                h = g.hit(r);
                if (h != null) {
                    return h.geo.material.colorFor(h, world, new Tracer(r, depth - 1, world));
                }
            }
            return world.backgroundcolor;
        }
    }
}
