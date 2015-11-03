package raytracer.camera;


import raytracer.Ray;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * Created by Juliand on 03.11.15.
 */
public class OrthographicCamera extends Camera {

    final double s;

    public OrthographicCamera(Point3 e, Vector3 g, Vector3 t,double s) {
        super(e, g, t);
        this.s=s;
    }


    @Override
    public Ray rayFor(int width, int height, int x, int y) {
        return null;
    }
}
