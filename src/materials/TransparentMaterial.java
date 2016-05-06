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
package materials;

import raytracer.Color;
import raytracer.Ray;
import raytracer.Tracer;
import raytracer.World;
import raytracer.Hit;
import matVecLib.Normal3;
import matVecLib.Vector3;

/**
 * This class represents a TransparentMaterial.
 * Created by Juliand on 02.01.16.
 */
public class TransparentMaterial extends Material {

    /**
     * The value of the refractive index.
     */
    private double refIndex;

    /**
     * This constructor creates a new TransparentMaterial.
     * @param refIndex The refIndex component of the medium.
     */

    public TransparentMaterial ( final double refIndex) {

        this.refIndex = refIndex;
    }

    /**
     * @param hit The hit object.
     * @param world The world object.
     * @param tracer The tracer object.
     * @return The Color for the transparent materials.
     */
    @Override
    public Color colorFor (final Hit hit, final World world, final Tracer tracer) {

        Normal3 normal = hit.nor;
        double epsi = 0.00001;


                // rd = reflected vector d
                final Vector3 rd = hit.ray.d.mul(-1).normalized().reflectedOn(normal).normalized();

                // The relative index of refraction.
                double rIor;

                if (rd.dot(normal) < 0) {

                    rIor = refIndex / world.refractionIndex;
                    normal = normal.mul(-1);

                } else {

                    rIor = world.refractionIndex / refIndex;

                }

                // angle between the direction vector and the normal or the angle between normal and the reflected
                // vector rd. (normal x vector = double)
                final double alpha = normal.dot(rd);

                if ((1 - (Math.pow(rIor, 2.0) * (1 - Math.pow(alpha, 2.0)))) < 0) {

                    return tracer.colorFor(new Ray(hit.ray.at(hit.t - epsi), rd));

                } else {

                    // angle between normal and the refracted vector t.
                    final double beta = Math.sqrt(1 - (Math.pow(rIor, 2.0) * (1 - Math.pow(alpha, 2.0))));

                    final Vector3 t = hit.ray.d.mul(rIor).sub(normal.mul(beta - rIor * alpha));


                    final double r0 = Math.pow((world.refractionIndex - refIndex) / (world.refractionIndex + refIndex), 2);
                    // amount of the reflexion.
                    final double r = r0 + (1 - r0) * Math.pow(1 - alpha, 5);
                    // amount of the transmission
                    final double t1 = 1 - r;


                    return tracer.colorFor(new Ray(hit.ray.at(hit.t - epsi), rd)).mul(r)
                            .add(tracer.colorFor(new Ray(hit.ray.at(hit.t + epsi), t)).mul(t1));

                }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransparentMaterial that = (TransparentMaterial) o;

        return Double.compare(that.refIndex, refIndex) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(refIndex);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return "TransparentMaterial{" +
                "refIndex=" + refIndex +
                '}';
    }
}
