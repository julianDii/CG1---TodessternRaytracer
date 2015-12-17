package raytracer.geometrie;

import material.Material;
import raytracer.matVecLib.Normal3;
import raytracer.matVecLib.Point3;
import raytracer.Ray;
import raytracer.matVecLib.Vector3;

import java.util.ArrayList;
import java.util.List;

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

    public AxisAlignedBox(final Material material) {
        super(material);

        this.lbf= new Point3(-0.5,-0.5,-0.5);
        this.run= new Point3(0.5,0.5,0.5);
    }

    /**
     * This method calculates the intersections of the ray with the AxisAlignedBox.
     * @param r
     * @return if the ray hits the box this method returns a new hit, else the return is null.
     */
    public Hit hit(Ray r) {

        if(r==null)throw new IllegalArgumentException("m has to be not null");

        List<Geometry> geo = new ArrayList<>();

        final Node leftSide = new Node(new Transform().translate(this.lbf).rotZ(Math.PI / 2), geo);
        final Node backSide = new Node(new Transform().translate(this.lbf).rotZ(Math.PI).rotX(-Math.PI / 2), geo);
        final Node bottomSide = new Node(new Transform().translate(this.lbf).rotX(Math.PI), geo);
        final Node rightSide = new Node(new Transform().translate(this.run).rotZ(-Math.PI / 2), geo);
        final Node topSide = new Node(new Transform().translate(this.run), geo);
        final Node frontSide = new Node(new Transform().translate(this.run).rotZ(Math.PI).rotX(Math.PI / 2), geo);

        geo.add(leftSide);
        geo.add(backSide);
        geo.add(bottomSide);
        geo.add(rightSide);
        geo.add(topSide);
        geo.add(frontSide);


        Hit hit = null;

        for(final Geometry g : geo){


            final double visible = r.o.sub(plane.a).normalized().dot(plane.n);

            if(visible > 0) {
                final double t = plane.a.sub(r.o).dot(plane.n) / r.d.dot(plane.n);
                if(hit==null||t> hit.t){
                    hit = new Hit(t,r,this,plane.n);
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


            final Point3 p = h.ray.at(h.t);
            final double e = 0.00000000001;

            if (    (lbf.x <= p.x+e && p.x <= run.x+e) &&
                    (lbf.y <= p.y+e && p.y <= run.y+e) &&
                    (lbf.z <= p.z+e && p.z <= run.z+e)
                    )
                return h;

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
