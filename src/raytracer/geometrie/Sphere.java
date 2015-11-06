package raytracer.geometrie;

import raytracer.matVecLib.Point3;
import raytracer.Color;
import raytracer.Ray;

/**
 * Created by Julian on 03.11.15.
 * Developer Julian Dobrot
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
