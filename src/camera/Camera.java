package camera;


import raytracer.Ray;
import matVecLib.Point3;
import matVecLib.Vector3;
import sampling.SamplingPattern;

import java.util.Set;

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
     * the SamplingPattern component of the camera.
     */
    final SamplingPattern samplingPattern;

    /**
     * The constructor needs the 3 vectors of the camera position and generates a new coordinate 
     * system with the origin e and the new axial vectors u, w and v.
     * These three vectors are needed to to generate the rays of the camera.
     * @param e position of camera (eye position)
     * @param g gaze direction
     * @param t Up-Vector
     */
    
    public Camera(final Point3 e, final Vector3 g, final Vector3 t, final SamplingPattern samplingPattern){

        if(e==null)throw new IllegalArgumentException("e have to be not null");
        if(g==null)throw new IllegalArgumentException("g have to be not null");
        if(t==null)throw new IllegalArgumentException("t have to be not null");
        if(samplingPattern==null)throw new IllegalArgumentException("samplingPattern have to be not null");

        this.e=e;
        this.g=g;
        this.t=t;
        this.samplingPattern=samplingPattern;
        
        this.w = this.g.normalized().mul(-1.0);
        this.u = this.t.x(this.w).normalized();
        this.v = this.w.x(this.u);
    }

    /**
     * This method returns the ray for certain pixel
     * @param width The width of the screen.
     * @param height The height of the screen.
     * @param x The x-coordinate of the pixel.
     * @param y The y-coordinate of the pixel
     * @return abstract no return
     */
    public abstract Set<Ray> rayFor(final int width,final int height,final int x,final int y);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Camera camera = (Camera) o;

        if (!e.equals(camera.e)) return false;
        if (!g.equals(camera.g)) return false;
        if (!t.equals(camera.t)) return false;
        if (!u.equals(camera.u)) return false;
        if (!v.equals(camera.v)) return false;
        return !(w != null ? !w.equals(camera.w) : camera.w != null);

    }

    @Override
    public int hashCode() {
        int result = e.hashCode();
        result = 31 * result + g.hashCode();
        result = 31 * result + t.hashCode();
        result = 31 * result + u.hashCode();
        result = 31 * result + v.hashCode();
        result = 31 * result + (w != null ? w.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "e=" + e +
                ", g=" + g +
                ", t=" + t +
                ", u=" + u +
                ", v=" + v +
                ", w=" + w +
                '}';
    }
}
