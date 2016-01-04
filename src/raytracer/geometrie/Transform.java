package raytracer.geometrie;
import raytracer.Ray;
import raytracer.matVecLib.Mat4x4;
import raytracer.matVecLib.Normal3;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

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

        m = new Mat4x4(

                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
        i = m;

    }

    /**
     * This constructor gets the given matrix an it's inverse
     * @param m The Mat4x4 m.
     * @param i The Mat4x4 i.
     */
    private Transform(final Mat4x4 m, final Mat4x4 i) {

        if(m==null) throw new IllegalArgumentException("m have to be not null");
        if(i==null) throw new IllegalArgumentException("i have to be not null");

        this.m = m;
        this.i = i;

    }

    /**
     * This method translates a given point.
     * @param p The point for the translation.
     * @return A new transformed point.
     */
    public Transform translate (final Point3 p) {

        if(p==null) throw new IllegalArgumentException("p have to be not null");

        final Transform trans = new Transform(

                new Mat4x4( 1, 0, 0, p.x,
                            0, 1, 0, p.y,
                            0, 0, 1, p.z,
                            0, 0, 0, 1),

                new Mat4x4( 1, 0, 0, -p.x,
                            0, 1, 0, -p.y,
                            0, 0, 1, -p.z,
                            0, 0, 0, 1));

        return new Transform(m.mul(trans.m), i.mul(trans.i));
    }

    /**
     * This method rotates the x with a given angle.
     * @param angle The rotation angle.
     * @return The transform object.
     */
    public Transform rotX (final double angle) {

        Transform trans = new Transform(

                new Mat4x4(
                        1, 0, 0, 0,
                        0, Math.cos(angle), -Math.sin(angle), 0,
                        0, Math.sin(angle),  Math.cos(angle), 0,
                        0, 0, 0, 1),
                new Mat4x4(
                        1, 0, 0, 0,
                        0,  Math.cos(angle), Math.sin(angle), 0,
                        0, -Math.sin(angle), Math.cos(angle), 0,
                        0, 0, 0, 1));

        return new Transform(m.mul(trans.m),trans.i.mul(i));
    }

    /**
     * This method rotates the y with a given angle.
     * @param angle The rotation angle.
     * @return The transform object.
     */
    public Transform rotY (final double angle) {

        Transform trans = new Transform(

                new Mat4x4(
                        Math.cos(angle), 0, Math.sin(angle), 0,
                        0, 1, 0, 0,
                        -Math.sin(angle), 0, Math.cos(angle), 0,
                        0, 0, 0, 1),
                new Mat4x4(
                        Math.cos(angle),0, -Math.sin(angle), 0,
                        0, 1, 0, 0,
                        Math.sin(angle), 0, Math.cos(angle), 0,
                        0, 0, 0, 1));

        return new Transform(m.mul(trans.m),trans.i.mul(i));
    }

    /**
     * This method rotates the z with a given angle.
     * @param angle The rotation angle.
     * @return The transform object.
     */
    public Transform rotZ(final double angle){

        Transform trans = new Transform(

                new Mat4x4(
                        Math.cos(angle),-Math.sin(angle), 0, 0,
                        Math.sin(angle), Math.cos(angle), 0, 0,
                        0, 0, 1, 0,
                        0, 0, 0, 1),
                new Mat4x4(
                        Math.cos(angle), Math.sin(angle), 0, 0,
                        -Math.sin(angle), Math.cos(angle), 0, 0,
                        0, 0, 1, 0,
                        0, 0, 0, 1));

        return new Transform(m.mul(trans.m),trans.i.mul(i));
    }

    /**
     * This method transforms the scale.
     * @param x the value of the x scale.
     * @param y the value of the y scale.
     * @param z the value of the z scale.
     * @return the transform object.
     */
    public Transform scale (final double x, final double y, final double z) {

        final Transform trans = new Transform(

                new Mat4x4(
                        x, 0, 0, 0,
                        0, y, 0, 0,
                        0, 0, z, 0,
                        0, 0, 0, 1),
                new Mat4x4(
                        1.0/x, 0, 0, 0,
                        0, 1.0/y, 0, 0,
                        0, 0, 1.0/z, 0,
                        0, 0, 0, 1));

        return new Transform(m.mul(trans.m),trans.i.mul(i));
    }


    /**
     * This method transforms a given ray.
     * @param r The ray for the transformation.
     * @return the transformed ray.
     */
    public Ray mul (final Ray r) {

        if(r==null)throw new IllegalArgumentException("r can not be null");

        return new Ray(i.mul(r.o),i.mul(r.d));
    }

    /**
     * This method transforms a given normal.
     * @param n The normal for the transformation.
     * @return The transformed normal.
     */
    public Normal3 mul(final Normal3 n){

        if(n==null)throw new IllegalArgumentException("n can not be null");

        return i.transposed().mul(new Vector3(n.x,n.y,n.z)).normalized().asNormal();

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
