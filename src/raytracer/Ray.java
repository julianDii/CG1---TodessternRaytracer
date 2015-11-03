package raytracer;


import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * Created by Charline Waldrich, 3.11.2015
 */
public class Ray {

    private final Point3 o;
    private final Vector3 d;

    public Ray(final Point3 o, final Vector3 d){

        this.o=o;
        this.d=d;
        
    }
    /** 
     * This Method returns a Point with its coordinates after following the direction Vector 
     * until we reach the given length.
     * @param 	t is the given double value of the lenght we want the direction Vector to have
     * @returns the point and its new coordinates
     */
    public Point3 at(double t){
        return o.add(d.mul(t));
    }
    /**
     * This Method is used to get the double value of the distance of two points.
     * @param p is the Point of which we want to know the distance to our position vector of the ray
     * @returns a double which is the distance of the given point p and o from each other 
     */
    public double tOf(Point3 p){return (p.sub(o)).magnitude;
    }

}
