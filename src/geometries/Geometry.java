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
import materials.Material;
import materials.SingleColorMaterial;
import raytracer.Color;
import raytracer.Hit;
import raytracer.Ray;

/**
 * This Class represents the absract geometry.
 * Created by Juliand on 03.11.15.
 */
public abstract class Geometry {

    /**
     * The materials component of the geometry.
     */

    public final Material material;

    /**
     * This constructor builds a geometry with a singlecolormaterial.
     */
    public Geometry() {

        material = new SingleColorMaterial(new Color(0, 0, 0));
    }

    /**
     * This contructor builds a geometry with a materials.
     * @param material
     */

    public Geometry(final Material material) {

        if (material == null) throw new IllegalArgumentException("materials have to be not null");
        this.material = material;
    }

    /**
     * This class calculates hits with the given ray.
     * @param r
     */

    public abstract Hit hit (final Ray r);


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Geometry geometry = (Geometry) o;

        return !(material != null ? !material.equals(geometry.material) : geometry.material != null);

    }

    @Override
    public int hashCode() {
        return material != null ? material.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "materials=" + material +
                '}';
    }
}
