package raytracer.geometrie;

import raytracer.matVecLib.Normal3;
import raytracer.matVecLib.Point3;
import raytracer.Ray;
import raytracer.geometrie.Geometry;
import raytracer.geometrie.Hit;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import material.Material;

/**
 * This class represents a AxisAlignedBox.
 * Created by Juliand on 03.11.15.
 * Developer Julian Dobrot
 */
public class AxisAlignedBox extends Geometry {

    /**
     * The lower left corner of the box.
     */
    final Point3 lbf;

    /**
     * The upper right near corner of the box.
     */
    final Point3 run;

    /**
     * This constructor builds a new AxisAlignedBox.
     * @param lbf
     * @param run
     * @param material
     */

    public AxisAlignedBox(final Point3 lbf, final Point3 run,final Material material) {
        super(material);

        if(lbf==null)throw new IllegalArgumentException("lbf has to be not null");
        if(run==null)throw new IllegalArgumentException("run has to be not null");

        this.lbf=lbf;
        this.run=run;
    }

    /**
     * This method calculates the intersections of the ray with the AxisAlignedBox.
     * @param r
     * @return if the ray hits the box this method returns a new hit, else the return is null.
     */
    public Hit hit(Ray r) {

        if(r==null)throw new IllegalArgumentException("m has to be not null");

        List<Plane> planes = new ArrayList<>();

        Plane leftSide = new Plane(lbf,new Normal3(-1, 0, 0),material);
        Plane backSide = new Plane(lbf,new Normal3(0, 0, -1),material);
        Plane bottomSide = new Plane(lbf,new Normal3(0, -1, 0),material);
        Plane rightSide = new Plane(run,new Normal3(1, 0, 0),material);
        Plane topSide = new Plane(run,new Normal3(0, 1, 0),material);
        Plane frontSide = new Plane(run,new Normal3(0, 0, 1),material);

        planes.add(leftSide);
        planes.add(backSide);
        planes.add(bottomSide);
        planes.add(rightSide);
        planes.add(topSide);
        planes.add(frontSide);


        Hit hit = null;

        for(final Plane plane : planes){

            final double visible = r.o.sub(plane.a).dot(plane.n);

            if(visible > 0) {
                final double t = plane.a.sub(r.o).dot(plane.n) / r.d.dot(plane.n);
                if(hit==null||t> hit.t){
                    hit = new Hit(t,r,this);
                }


            }
        }

        return compareDis(hit);
    }

    /**
     * This method compares if the hits really on the box .
     * @param h
     * @return return is a hit when the point is really in the box otherwise the return is null.
     */
    private Hit compareDis(final Hit h) {


        if(h != null) {

            final double fix = 0.00001;
            final Point3 inOut=h.ray.at(h.t);

            if(inOut.x >= lbf.x+fix && inOut.y+fix >= lbf.y && inOut.z >= lbf.z+fix &&
                    inOut.x<=run.x+fix && inOut.y <= run.y+fix && inOut.z <= run.z+fix){
                return h;
            }

        }

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AxisAlignedBox that = (AxisAlignedBox) o;

        if (!lbf.equals(that.lbf)) return false;
        return run.equals(that.run);

    }

    @Override
    public int hashCode() {
        int result = lbf.hashCode();
        result = 31 * result + run.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AxisAlignedBox{" +
                "lbf=" + lbf +
                ", run=" + run +
                '}';
    }
}
