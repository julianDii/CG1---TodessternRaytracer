package raytracer.geometrie;

import raytracer.matVecLib.Point3;
import raytracer.Color;
import raytracer.Ray;

/**
 * This class represents a Sphere.
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
     * This constructor builds a new Sphere.
     * @param c The center point of the Sphere.
     * @param r The radius of the Sphere.
     * @param color The color of the Sphere.
     */
    public Sphere(final Point3 c, final double r, final Color color) {
        super(color);

        if(c==null)throw new IllegalArgumentException("c has to be not null");

        this.c=c;
        this.r=r;
    }

    /**
     * This method calculates the intersections of the ray with the sphere.
     * @param r
     * @return the nearest positive t.
     */
    public Hit hit(Ray r) {

        // a = d * d
        // b = d * [2(o-c)]
        // c = (o - c) * (o - c) - r^2

        // first we have to check if we have 0,1 or 2 results
        final double a;
        final double b;
        final double cNor;
        final double t1;
        final double t2;
        final double d;

        b=r.d.dot((r.o.sub(c)).mul(2));
        a=r.d.dot(r.d);
        cNor=r.o.sub(c).dot(r.o.sub(c))-(this.r*this.r);
        d = (b * b) - (4 * a * cNor);


        if(d>0) {

            t1 = (-b + Math.sqrt(d)) / (2 * a);
            t2 = (-b - Math.sqrt(d)) / (2 * a);

            if (t1 >= 0 & t2 >= 0) {
               return new Hit(Math.min(t1, t2), r, this);
            }else if (t1>=0){
                return new Hit(t1,r,this);
            }else if(t2>=0) {
                return new Hit(t2, r, this);
            }
        }else if (d==0){
            final double t3;
            t3=-b/(2*a);
            if (t3>=0){
                return new Hit(t3,r,this);
            }
        }
        return null;
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
