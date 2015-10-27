package matVecLib;

import java.util.Vector;

/**
 * This Class creates a new V3 Vector
 * Created by Juliand on 13.10.15.
 */

public class Vector3 {

    /**
     * The x component of the Vector.
     */

    public final double x;

    /**
     * The y component of the Vector.
     */

    public final double y;

    /**
     * The z component of the Vector.
     */

    public final double z;

    /**
     * The length of he Vector
     */

    public final double magnitude;

    /**
     * This constructor creates a new Vector and calculates its length.
     * @param x
     * @param y
     * @param z
     */

    public Vector3(final double x,final double y, final double z){

        this.x=x;
        this.y=y;
        this.z=z;
        this.magnitude= Math.sqrt(x * x + y * y + z * z);

    }



    public Vector3 add(Vector3 v){

        if (v == null) throw new IllegalArgumentException("v must be not null." );

        return new Vector3(

                x + v.x,
                y + v.y,
                z + v.z
        );
    }
    public Vector3 add(Normal3 n){

        if (n == null) throw new IllegalArgumentException("n must be not null." );

        return new Vector3(

                x + n.x,
                y + n.y,
                z + n.z
        );
    }
    public Vector3 sub(Normal3 n){

        if (n == null) throw new IllegalArgumentException("n must be not null." );

        return new Vector3(

                x - n.x,
                y - n.y,
                z - n.z
        );
    }
    public Vector3 mul(double c){

        return new Vector3(
                x * c,
                y * c,
                z * c
        );
    }

    /**
     * Given Vector multiplied by another Vector.
     * @param v
     * @return the result of a Vector * vector multiplication = double.
     */

    public double dot(Vector3 v){

        if (v == null) throw new IllegalArgumentException("v must be not null.");

        return x * v.x * y * v.y + z * v.z ;
    }

    /**
     * Given Vector multiplied by a normal.
     * @param n
     * @return the result of the Vector * Normal operation.
     */

    public double dot(Normal3 n){

        if (n == null) throw new IllegalArgumentException("n must be not null." );

        return x * n.x + y * n.y + z * n.z ;

    }

    /**
     * This Method normalizes the given Vector.
     * @return The normalized Vector.
     */

    public Vector3 normalized(){

        return new Vector3(

                x/magnitude,
                y/magnitude,
                z/magnitude
        );
    }

    /**
     * The Normal
     * @return The Normal
     */

    public Normal3 asNormal(){

        return new Normal3(x,y,z);

    }

    /**
     * This Method reflects a Vector on a Normal.
     * @param n
     * @return The on the Normal reflected Vector.
     */

    public Vector3 reflectedOn(Normal3 n) {

        if (n == null) throw new IllegalArgumentException("n must be not null." );

        return this.mul(-1).add(n.mul(n.dot(this) * 2));
    }

    /**
     * The cross product between the given Vector and the Vector from the object.
     * @param v
     * @return The result of the cross product.
     */

    public Vector3 x(Vector3 v){

        if (v == null) throw new IllegalArgumentException("v must be not null." );

        return new Vector3(

                y * v.z - z * v.y,
                z * v.x - x * v.z,
                x * v.y - y * v.x

        );

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector3 vector3 = (Vector3) o;

        if (Double.compare(vector3.x, x) != 0) return false;
        if (Double.compare(vector3.y, y) != 0) return false;
        if (Double.compare(vector3.z, z) != 0) return false;
        return Double.compare(vector3.magnitude, magnitude) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(magnitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Vector3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", magnitude=" + magnitude +
                '}';
    }
}
