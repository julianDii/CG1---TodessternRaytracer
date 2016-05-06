/*
 * MIT License
 *
 * Copyright (c) 2016 Julian Dobrot
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package matVecLib;

/**
 * This class represents a vector in the three dimensional space.
 * Created by Julian Dobrot on 13.10.15.
 */

public class Vector3 {

    /**
     * The x coordinate of the Vector.
     */

    public final double x;

    /**
     * The y coordinate of the Vector.
     */

    public final double y;

    /**
     * The z coordinate of the Vector.
     */

    public final double z;

    /**
     * The length of the Vector.
     */

    public final double magnitude;

    /**
     * This constructor creates a new Vector and calculates its length.
     * @param x The x coordinate of the vector.
     * @param y The y coordinate of the vector.
     * @param z The z coordinate of the vector.
     */

    public Vector3 (final double x, final double y, final double z) {

        this.x = x;
        this.y = y;
        this.z = z;
        this.magnitude = Math.sqrt(x * x + y * y + z * z);

    }


    /**
     * This method adds a vector to the given vector.
     * @param v The vector which will be added.
     * @return A new Vector.
     */
    public Vector3 add (Vector3 v) {

        if (v == null) throw new IllegalArgumentException("v must be not null." );

        return new Vector3 (

                x + v.x,
                y + v.y,
                z + v.z
        );
    }

    /**
     * This mehod adds a normal to the given vector.
     * @param n The normal which will be added.
     * @return A new vector.
     */
    public Vector3 add (final Normal3 n) {

        if (n == null) throw new IllegalArgumentException("n must be not null." );

        return new Vector3 (

                x + n.x,
                y + n.y,
                z + n.z
        );
    }

    /**
     * This class subtracts a normal from the given vector.
     * @param n The normal which will be subtracted.
     * @return A new vector.
     */
    public Vector3 sub (Normal3 n) {

        if (n == null) throw new IllegalArgumentException("n must be not null." );

        return new Vector3(

                x - n.x,
                y - n.y,
                z - n.z
        );
    }

    /**
     * This method multiplies the given vector with a double value.
     * @param c The double value which will be multiplied.
     * @return A new vector.
     */
    public Vector3 mul (double c) {

        return new Vector3 (
                x * c,
                y * c,
                z * c
        );
    }


    /**
     * This method multiplies the given vector with another vector.
     * @param v The vector to multiply with.
     * @return the result of a Vector * vector multiplication = double.
     */

    public double dot (final Vector3 v) {

        if (v == null) throw new IllegalArgumentException("v must be not null.");

        return x * v.x + y * v.y + z * v.z ;
    }

    /**
     * this method multiplies the given vector with a normal.
     * @param n The normal to multiply with.
     * @return the result of the Vector * Normal operation.
     */

    public double dot (final Normal3 n) {

        if (n == null) throw new IllegalArgumentException("n must be not null." );

        return x * n.x + y * n.y + z * n.z ;

    }

    /**
     * This Method normalizes the given Vector.
     * @return The normalized Vector.
     */

    public Vector3 normalized () {

        return new Vector3 (

                x/magnitude,
                y/magnitude,
                z/magnitude
        );
    }

    /**
     * The given vector as normal.
     * @return The Normal.
     */

    public Normal3 asNormal () {

        return new Normal3(x,y,z);

    }


    /**
     * This Method reflects a Vector on a Normal.
     * @param n The normal to reflect on.
     * @return The on the Normal reflected Vector.
     */

    public Vector3 reflectedOn ( final Normal3 n) {

        if (n == null) throw new IllegalArgumentException("n must be not null." );

        return this.mul(-1).add(n.mul(n.dot(this) * 2));
    }

    /**
     * The cross product between the given Vector and the Vector from the object.
     * @param v The vector to build the cross product.
     * @return The result of the cross product.
     */

    public Vector3 x (final Vector3 v){

        if (v == null) throw new IllegalArgumentException("v must be not null." );

        return new Vector3 (

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
