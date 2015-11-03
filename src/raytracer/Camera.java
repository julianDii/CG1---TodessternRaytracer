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
    final Vector3 u;
    final Vector3 v;
    final Vector3 w;

    /**
     * The constructor needs the 3 vectors of the camera position and generates a new coordinate 
     * system with the origion e and the new axial vectors u, w and v. 
     * These three vectors are needed to to generate the rays of the camera.
     * @param e position of camera (eye position)
     * @param g gaze direction
     * @param t Up-Vector
     */
    
    public Camera(final Point3 e, final Vector3 g, Vector3 t){
        this.e=e;
        this.g=g;
        this.t=t;
        
        double gBetrag = g.magnitude;
        this.w = g.mul((gBetrag)*(1/gBetrag)).mul(-1);
        double txwBetrag = t.x(w).magnitude;
        this.u = (t.x(w)).mul(1/txwBetrag);
        this.v = w.x(u);
    }
    
    public abstract Ray rayFor(int width,int height, int x, int y);


}
