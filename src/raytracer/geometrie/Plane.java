package raytracer.geometrie;

import raytracer.matVecLib.Normal3;
import raytracer.matVecLib.Point3;
import raytracer.Color;
import raytracer.Ray;
import raytracer.geometrie.Geometry;
import raytracer.geometrie.Hit;

/**
 * Created by Julian on 03.11.15.
 * Developer Charline Waldrich
 */
public class Plane extends Geometry {

    final Point3 a;
    final Normal3 n;

    public Plane(final Point3 a, final Normal3 n, final Color color) {
        super(color);
        this.a=a;
        this.n=n;
    }

    @Override
    public Hit hit(Ray r) {
        return null;
    }
}
