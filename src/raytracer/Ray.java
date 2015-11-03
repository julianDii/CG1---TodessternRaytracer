package raytracer;

import matVecLib.Point3;
import matVecLib.Vector3;

/**
 * Created by Juliand on 03.11.15.
 */
public class Ray {

    final Point3 o;
    final Vector3 d;

    public Ray(final Point3 o, final Vector3 d){

        this.o=o;
        this.d=d;
    }
    public Point3 at(double t){
        return null; //TODO
    }
    public double tOf(Point3 p){
        return 0; //TODO
    }

}
