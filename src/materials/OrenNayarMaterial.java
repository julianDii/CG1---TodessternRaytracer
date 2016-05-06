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

import lights.Light;
import matVecLib.Point3;
import matVecLib.Vector3;
import raytracer.Color;
import raytracer.Hit;
import raytracer.Tracer;
import raytracer.World;
import texture.Texture;

/**
 * /**
 * This class represents a OrenNayarMaterial.
 * Created by Juliand on 15.01.16.
 */
public class OrenNayarMaterial extends Material {


    /**
     * the diffusing color
     */
    public final Texture diffuse;

    /**
     * the specular color
     */
    public final Texture specular;

    /**
     * the exponent
     */
    public final int exponent;


    /**
     * This contructor creates a new OrenNayarMaterial.
     * @param diffuse
     * @param specular
     * @param exponent
     */
    public OrenNayarMaterial(final Texture diffuse, final Texture specular, final int exponent) {

        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
    }

    /**
     * This method calculates the color for a given git.
     * @param hit The hit object.
     * @param world The world object.
     * @param tracer
     * @return The color.
     */

    @Override
    public Color colorFor(final Hit hit, final World world, final Tracer tracer) {

        final Color c = this.diffuse.colorFor(hit.tex2d.u, hit.tex2d.v).mul(world.ambient);

        final Point3 pHit = hit.ray.at(hit.t);
        final Vector3 viewVector = hit.ray.d.mul(-1);

        Color returnColor = world.backgroundcolor;
        final double rough = Math.pow(this.exponent, 2);
        final double a = 1.0f - 0.5f * (rough / (rough + 0.57f));
        final double b = 0.45f * (rough / (rough + 0.09f));

        for (final Light li : world.lightList) {

            if (li.illuminates(pHit, world)) {

                final Vector3 lightVector = li.directionFrom(pHit).normalized();
                final double alpha = Math.max(Math.acos(viewVector.dot(hit.nor)), Math.acos(lightVector.dot(hit.nor)));
                final double beta = Math.min(Math.acos(viewVector.dot(hit.nor)), Math.acos(viewVector.dot(hit.nor)));
                returnColor = this.diffuse.colorFor(hit.tex2d.u, hit.tex2d.v).mul(hit.nor.dot(lightVector))
                        .mul(a + b * Math.max(0, hit.nor.dot(lightVector)) * Math.sin(alpha) * Math.tan(beta));
            }
        }

        return c.add(returnColor);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrenNayarMaterial that = (OrenNayarMaterial) o;

        if (exponent != that.exponent) return false;
        if (diffuse != null ? !diffuse.equals(that.diffuse) : that.diffuse != null) return false;
        return !(specular != null ? !specular.equals(that.specular) : that.specular != null);

    }

    @Override
    public int hashCode() {
        int result = diffuse != null ? diffuse.hashCode() : 0;
        result = 31 * result + (specular != null ? specular.hashCode() : 0);
        result = 31 * result + exponent;
        return result;
    }

    @Override
    public String toString() {
        return "OrenNayarMaterial{" +
                "diffuse=" + diffuse +
                ", specular=" + specular +
                ", exponent=" + exponent +
                '}';
    }
}