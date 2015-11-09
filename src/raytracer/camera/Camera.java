package raytracer.camera;


import raytracer.Ray;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * This Class creates a new camera
 * Created by Julian & Charline on 03.11.15. 
 */
public abstract class Camera {

    /**
     * The Point3 object of the camera, which represents the position (x,y,z coordinates)
     */
    final Point3 e;

    /**
     * The Vector3 object of the camera, which represents the perspective (gaze direction)
     */
    final Vector3 g;

    /**
     * The Vector3 object of the camera, which represents the up-Vector.
     */
    final Vector3 t;

    /**
     * The Vector3 object, which represents the u-axis of the transformed coordinate system.
     */
    final Vector3 u;

    /**
     * The Vector3 object, which represents the v-axis of the transformed coordinate system.
     */
    final Vector3 v;

    /**
     * The Vector3 object, which represents the w-axis of the transformed coordinate system.
     */
    final Vector3 w;

    /**
     * The constructor needs the 3 vectors of the camera position and generates a new coordinate 
     * system with the origin e and the new axial vectors u, w and v.
     * These three vectors are needed to to generate the rays of the camera.
     * @param e position of camera (eye position)
     * @param g gaze direction
     * @param t Up-Vector
     */
    
    public Camera(final Point3 e, final Vector3 g, final Vector3 t){
        this.e=e;
        this.g=g;
        this.t=t;
        
        double gAbs = g.magnitude;
        this.w = g.mul((gAbs)*(1/gAbs)).mul(-1);
        double txwAbs = t.x(w).magnitude;
        this.u = (t.x(w)).mul(1/txwAbs);
        this.v = w.x(u);
    }

    /**
     * This method returns the ray for certain pixel
     * @param width The width of the screen.
     * @param height The height of the screen.
     * @param x The x-coordinate of the pixel.
     * @param y The y-coordinate of the pixel
     * @return abstract no return
     */
    public abstract Ray rayFor(final int width,final int height,final int x,final int y);


}