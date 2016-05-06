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
package raytracer;

import matVecLib.Vector3;

/**
 * This class represents a bounding box.
 * Created by Juliand on 12.01.16.
 */
public class BoundingBox {

    private double x0 , y0 , z0;
    private double x1 , y1 , z1;

    public BoundingBox(Vector3 minCorner, Vector3 maxCorner) {
        this(minCorner.x, maxCorner.x,
                minCorner.y, maxCorner.y,
                minCorner.z, maxCorner.z);
    }

    public BoundingBox( final double x0, final double x1,
                        final double y0, final double y1,
                        final double z0, final double z1) {

        this.x0 = x0;
        this.y0 = y0;
        this.z0 = z0;

        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
    }

    public boolean hit(Ray ray) {
        double ox = ray.o.x;
        double oy = ray.o.y;
        double oz = ray.o.z;

        double dx = ray.d.x;
        double dy = ray.d.y;
        double dz = ray.d.z;

        double tx_min, ty_min, tz_min;
        double tx_max, ty_max, tz_max;

        double a = 1.0 / dx;
        if (a >= 0) {
            tx_min = (x0 - ox) * a;
            tx_max = (x1 - ox) * a;
        } else {
            tx_min = (x1 - ox) * a;
            tx_max = (x0 - ox) * a;
        }

        double b = 1.0 / dy;
        if (b >= 0) {
            ty_min = (y0 - oy) * b;
            ty_max = (y1 - oy) * b;
        } else {
            ty_min = (y1 - oy) * b;
            ty_max = (y0 - oy) * b;
        }

        double c = 1.0 / dz;
        if (c >= 0) {
            tz_min = (z0 - oz) * c;
            tz_max = (z1 - oz) * c;
        } else {
            tz_min = (z1 - oz) * c;
            tz_max = (z0 - oz) * c;
        }

        double t0, t1;

        // find largest entering t value

        if (tx_min > ty_min)
            t0 = tx_min;
        else
            t0 = ty_min;

        if (tz_min > t0)
            t0 = tz_min;

        // find smallest exiting t value

        if (tx_max < ty_max)
            t1 = tx_max;
        else
            t1 = ty_max;

        if (tz_max < t1)
            t1 = tz_max;

        return (t0 < t1 && t1 > Constants.EPSILON);
    }

    /**
     * Test if the given point is inside the bounding box.
     *
     * @param p The point
     * @return True if the point is inside.
     */
    public boolean inside(Vector3 p) {
        return p.x > x0 && p.x < x1 &&
                p.y > y0 && p.y < y1 &&
                p.z > z0 && p.z < z1;
    }



}
