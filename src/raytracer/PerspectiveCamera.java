package raytracer;

import matVecLib.Point3;
import matVecLib.Vector3;

/**
 * Created by Juliand on 03.11.15.
 */
public class PerspectiveCamera extends Camera {

    final double angle;

    public PerspectiveCamera(Point3 e, Vector3 g, Vector3 t, double angle) {
        super(e, g, t);
        this.angle=angle;
    }

    @Override
    public Ray rayFor(int width, int height, int x, int y) {
        return null;
    }
}
