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
package geometries;

import matVecLib.Point3;
import matVecLib.Solvers;
import matVecLib.Vector3;
import materials.Material;
import raytracer.Constants;
import raytracer.Hit;
import raytracer.Ray;
import texture.TextureCoord2D;
import static java.lang.Math.*;

/**
 * This class represents the geometry generic torus.
 *
 * Created by Juliand on 04.01.16.
 */
public class Torus extends Geometry {

    final  Point3 point;


    final double sweptRadius;

    final double tubeRadius;



    public Torus (final Point3 point, final double sweptRadius, final double tubeRadius, final Material material) {
        super (material);

        this.point = point;
        this.sweptRadius = sweptRadius;
        this.tubeRadius  = tubeRadius;

    }
    public Torus (final Material material) {
        super (material);

        this.point = new Point3(0,1,1);
        this.sweptRadius = 4;
        this.tubeRadius = 1;

    }


    @Override
    public Hit hit (final Ray r) {


        double x1 = r.o.x;
        double y1 = r.o.y;
        double z1 = r.o.z;

        double d1 = r.d.x;
        double d2 = r.d.y;
        double d3 = r.d.z;

        // The coefficient array for the quartic equation
        double coeffs[] = new double[5];
        // solution array for the quartic equation
        double roots[] = new double[4];

        // defining the coefficients of the quartic equation

        double sum_d_sqrd = d1 * d1 + d2 * d2 + d3 * d3;
        double e = x1 * x1 + y1 * y1 + z1 * z1 - sweptRadius * sweptRadius - tubeRadius * tubeRadius;
        double f = x1 * d1 + y1 * d2 + z1 * d3;
        double four_a_sqrd = 4.0 * sweptRadius * sweptRadius;

        coeffs[0] = e * e - four_a_sqrd * (tubeRadius * tubeRadius - y1 * y1);    // constant term
        coeffs[1] = 4.0 * f * e + 2.0 * four_a_sqrd * y1 * d2;
        coeffs[2] = 2.0 * sum_d_sqrd * e + 4.0 * f * f + four_a_sqrd * d2 * d2;
        coeffs[3] = 4.0 * sum_d_sqrd * f;
        coeffs[4] = sum_d_sqrd * sum_d_sqrd;                    // coefficient of t^4

        // find roots of the quartic equation
        int num_real_roots = Solvers.solveQuartic(coeffs, roots);

        boolean intersected = false;
        double t = Double.MAX_VALUE;

        if (num_real_roots == 0)  // ray misses the torus
            return null;

        // find the smallest root greater than kEpsilon, if any
        // the roots array is not sorted
        for (int j = 0; j < num_real_roots; j++)
            if (roots[j] > Constants.EPSILON) {
                intersected = true;
                if (roots[j] < t)
                    t = roots[j];
            }

        if (!intersected)
            return null;

        return new Hit(t, r, this, getNormal(r.at(t)).asNormal(), texFor(point));
    }

    public Vector3 getNormal(final Point3 p) {

        double param_squared = sweptRadius * sweptRadius + tubeRadius * tubeRadius;

        double x = p.x;
        double y = p.y;
        double z = p.z;

        double sum_squared = x * x + y * y + z * z;

        return new Vector3(

                4.0f * x * (sum_squared - param_squared),
                4.0f * y * (sum_squared - param_squared + 2.0f * sweptRadius * sweptRadius),
                4.0f * z * (sum_squared - param_squared)).normalized();
    }


    private TextureCoord2D texFor(Point3 p) {

        return new TextureCoord2D(0,0);
    }

}
