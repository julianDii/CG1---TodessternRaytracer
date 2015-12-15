package raytracer.geometrie;

import raytracer.Ray;
import raytracer.matVecLib.Mat4x4;
import raytracer.matVecLib.Normal3;
import raytracer.matVecLib.Point3;

/**
 * This class represents a transfprm object.
 * Created by Juliand on 15.12.15.
 */
public class Transform {
    /**
     * The m component of the transform object.
     */
    public final Mat4x4 m;
    /**
     * THe i component of the transform object
     */
    public final Mat4x4 i;

    /**
     * This contructor calculates the transformation with the unit matrix.
     */
    public Transform(){

        //TODO Transformation mit der Einheitsmatrix

    }

    /**
     * This contructor gets the given matrix an it's inverse
     * @param m
     * @param i
     */

    private Transform(final Mat4x4 m,final Mat4x4 i){
        if(m==null)throw new IllegalArgumentException("m have to be not null");
        if(i==null)throw new IllegalArgumentException("i have to be not null");

        this.m=m;
        this.i=i;

    }

    /**
     * This method translates a given point.
     * @param p
     * @return
     */
    public Transform translate(final Point3 p){
        if(p==null)throw new IllegalArgumentException("p have to be not null");
        return null;
    }

    /**
     * This method transforms the scale.
     * @param x
     * @param y
     * @param z
     * @return the transform object.
     */
    public  Transform scale(final double x,final double y,final double z){
        return null;
    }

    /**
     * This method rotates the x with a given angle.
     * @param angle
     * @return The transform object.
     */
    public Transform rotX(final double angle){
        return null;
    }

    /**
     * This method rotates the y with a given angle.
     * @param angle
     * @return The transform object.
     */
    public Transform rotY(final double angle){
        return null;
    }

    /**
     * This method rotates the z with a given angle.
     * @param angle
     * @return The transform object.
     */
    public Transform rotZ(final double angle){
        return null;
    }

    /**
     * This method transforms a given ray.
     * @param r
     * @return the transformed ray.
     */
    public Ray mul(final Ray r){
        if(r==null)throw new IllegalArgumentException("r have to be not null");
        return null;
    }

    /**
     * This method transforms a given normal.
     * @param n
     * @return The transformed normal.
     */
    public Normal3 mul(Normal3 n){
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transform transform = (Transform) o;

        if (!m.equals(transform.m)) return false;
        return i.equals(transform.i);

    }

    @Override
    public int hashCode() {
        int result = m.hashCode();
        result = 31 * result + i.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Transform{" +
                "m=" + m +
                ", i=" + i +
                '}';
    }
}
