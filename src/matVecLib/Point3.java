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
 * This class represents a mathematical point object in the three dimensional space.
 * Created by Juliand on 13.10.15.
 */
public class Point3 {

    /**
     * The x Component of the point
     */
    public final double x;

    /**
     * The y Component of the point
     */

    public final double y;

    /**
     * The z Component of the point
     */

    public final double z;

    /**
     * This Constructor builds a Point3 Object with the given parameters.
     *
     * @param x The x component of the point.
     * @param y The y component of the point.
     * @param z The z component of the point.
     */

    public Point3 (final double x, final double y, final double z) {

        this.x = x;
        this.y = y;
        this.z = z;

    }

    /**
     * This Method subtract the Point3 components from the constructor with the given Point3
     * @param p The point which will be subtracted.
     * @return a new Vector3.
     */
    public Vector3 sub (final Point3 p) {

        if(p == null) throw new IllegalArgumentException("'p'have to be not null");

        return new Vector3 (

                x - p.x,
                y - p.y,
                z - p.z
        );

    }

    /**
     * This Method subtracts the given point with a vector.
     * @param v The vector which will be subtracted.
     * @return new Point3.
     */
    public Point3 sub (final Vector3 v) {

        if(v == null)throw new IllegalArgumentException("'v'have to be not null");

        return new Point3 (

                x - v.x,
                y - v.y,
                z - v.z
        );
    }

    /**
     * this Method adds a Verctor3 to the given Point3
     * @param v The vector which will be added.
     * @return a new Point3.
     */

    public Point3 add ( final Vector3 v) {

        if(v == null) throw new IllegalArgumentException("'v'have to be not null");

        return new Point3 (

                x + v.x,
                y + v.y,
                z + v.z
        );

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point3 point3 = (Point3) o;

        if (Double.compare(point3.x, x) != 0) return false;
        if (Double.compare(point3.y, y) != 0) return false;
        return Double.compare(point3.z, z) == 0;

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
        return result;
    }

    @Override
    public String toString() {
        return "Point3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
