package raytracer.camera;


import raytracer.Ray;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * Created by Juliand on 03.11.15.
 */
public class PerspectiveCamera extends Camera {

    final double angle;

    public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, final double angle) {
        super(e, g, t);
        this.angle=angle;
    }

    @Override
    public Ray rayFor(int width, int height, int x, int y) {
        return null;
    }
}
