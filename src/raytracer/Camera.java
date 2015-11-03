package raytracer;

import matVecLib.Point3;
import matVecLib.Vector3;

/**
 * Created by Juliand on 03.11.15.
 */
public abstract class Camera {

    final Point3 e;
    final Vector3 g;
    final Vector3 t;
    final Vector3 u=null;
    final Vector3 v=null;
    final Vector3 w=null;

    public Camera(final Point3 e, final Vector3 g, Vector3 t){
        this.e=e;
        this.g=g;
        this.t=t;
    }
    public abstract Ray rayFor(int width,int height, int x, int y);


}
