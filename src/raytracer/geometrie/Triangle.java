package raytracer.geometrie;

import matVecLib.Point3;
import raytracer.Color;
import raytracer.Ray;

/**
 * Created by Juliand on 03.11.15.
 */
public class Triangle extends Geometry {

    final Point3 a;
    final Point3 b;
    final Point3 c;

    public Triangle(final Point3 a, final Point3 b, final Point3 c, final Color color) {
        super(color);
        this.a=a;
        this.b=b;
        this.c=c;
    }

    @Override
    public Hit hit(Ray r) {
        return null;
    }
}
