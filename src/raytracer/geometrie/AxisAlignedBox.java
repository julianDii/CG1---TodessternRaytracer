package raytracer.geometrie;

import raytracer.matVecLib.Point3;
import raytracer.Color;
import raytracer.Ray;
import raytracer.geometrie.Geometry;
import raytracer.geometrie.Hit;

/**
 * Created by Juliand on 03.11.15.
 * Developer Julian Dobrot
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
