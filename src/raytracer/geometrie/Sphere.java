package raytracer.geometrie;

import matVecLib.Point3;
import raytracer.Color;
import raytracer.Ray;

/**
 * Created by Juliand on 03.11.15.
 */
public class Sphere extends Geometry {

    final Point3 c;
    final double r;

    public Sphere(final Point3 c, final double r, final Color color) {
        super(color);
        this.c=c;
        this.r=r;
    }

    @Override
    public Hit hit(Ray r) {
        return null;
    }
}
