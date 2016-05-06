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
import matVecLib.Point3;
import raytracer.Hit;
import raytracer.Ray;
import texture.TextureCoord2D;

/**
 * This class represents the geometry Sphere.
 * Created by Julian on 03.11.15.
 * Developer Julian Dobrot
 */
public class Sphere extends Geometry {

    /**
     * The center point of the Sphere.
     */
    final Point3 c;

    /**
     * The Radius value of the Sphere.
     */
    final double r;


    /**
     * This contructor builds a new Sphere.
     * @param c The Point where the Sphere is located.
     * @param r The radius of the Sphere
     * @param material
     */
    public Sphere (final Point3 c, final double r, final Material material) {
        super(material);

        this.c = c;
        this.r = r;
    }


    /**
     * This constructor builds a new Sphere.
     * The Spere will be created with a radius of 1 @ the point 0,0,0
     * @param material The materials of the Sphere
     */
    public Sphere (final Material material) {
        super(material);

        this.c = new Point3(0,0,0);
        this.r = 1;
    }

    /**
     * This method calculates the intersections of the ray with the sphere.
     * @param r The Ray.
     * @return The hit if there is an intersection with the Sphere. Null if not.
     */
    public Hit hit (final Ray r) {

        final double a;
        final double b;
        final double cNor;
        final double t1;
        final double t2;
        final double d;
        final Point3 p;

        b = r.d.dot((r.o.sub(c)).mul(2));
        a = r.d.dot(r.d);
        cNor = r.o.sub(c).dot(r.o.sub(c))-(this.r*this.r);
        d = (b * b) - (4 * a * cNor);

        if(d > 0) {

            t1 = (-b + Math.sqrt(d)) / (2 * a);
            t2 = (-b - Math.sqrt(d)) / (2 * a);

            if (t1 >= 0 & t2 >= 0) {

                p = r.at(Math.min(t1, t2));

               return new Hit(Math.min(t1, t2), r, this,p.sub(c).normalized().asNormal(),texFor(p) );

            }else if (t1 >= 0){

                return new Hit(t1,r,this, r.at(t1).sub(c).normalized().asNormal(),texFor(r.at(t1)));

            }else if(t2 >= 0) {

                return new Hit(t2, r, this, r.at(t2).sub(c).normalized().asNormal(),texFor(r.at(t2)));
            }
        }else if (d == 0){

            final double t3;
            t3 = -b / (2 * a);

            if (t3 >= 0){

                return new Hit(t3, r, this, r.at(t3).sub(c).normalized().asNormal(), texFor(r.at(t3)));

            }
        }

        return null;
    }

    /**
     * This method calculates the texture for a given point.
     * @param point The point for calculate the texture coordinate.
     * @return The new texture coordinate.
     */

    public TextureCoord2D texFor (final Point3 point) {

        if (point == null)throw new IllegalArgumentException("The Point cannot be null!");


        double teta = Math.acos(point.y);
        double phi = Math.atan2(point.x, point.z);

        return new TextureCoord2D(phi / (Math.PI*2), -teta/Math.PI);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sphere sphere = (Sphere) o;

        if (Double.compare(sphere.r, r) != 0) return false;
        return c.equals(sphere.c);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = c.hashCode();
        temp = Double.doubleToLongBits(r);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "c=" + c +
                ", r=" + r +
                '}';
    }
}
