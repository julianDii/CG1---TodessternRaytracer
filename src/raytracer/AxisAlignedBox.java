package raytracer;

import matVecLib.Point3;

/**
 * Created by Juliand on 03.11.15.
 */
public class AxisAlignedBox extends Geometry {

    final Point3 lbf;
    final Point3 run;

    public AxisAlignedBox(final Point3 lbf, final Point3 run, final Color color) {
        super(color);
        this.lbf=lbf;
        this.run=run;
    }

    @Override
    public Hit hit(Ray r) {
        return null;
    }
}
