package raytracer;

import matVecLib.Normal3;
import matVecLib.Point3;

/**
 * Created by Juliand on 03.11.15.
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
