package geometries;

import materials.Material;
import matVecLib.Point3;
import raytracer.Hit;
import raytracer.Ray;
import raytracer.Transform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * The left side component of the Box.
     */
    private final Node leftSide;

    /**
     * The back side component of the Box.
     */
    private final Node backSide;

    /**
     * The bottom side component of the Box.
     */
    private final Node bottomSide;

    /**
     * The right side component of the Box.
     */
    private final Node rightSide;

    /**
     * The top side component of the Box.
     */
    private final Node topSide;

    /**
     * The fron side component of the Box.
     */
    private final Node frontSide;


    /**
     * This constructor builds a new AxisAlignedBox with assigned values.
     * @param lbf The left near corner of the box.
     * @param run The right near corner of the box.
     * @param material The materials.
     */
    public AxisAlignedBox (final Point3 lbf, final Point3 run, final Material material) {

        super(material);
        this.lbf = lbf;
        this.run = run;

        final List<Geometry> geoList = new ArrayList<>();
        geoList.add(new Plane(material));

        leftSide = new Node(new Transform().translate(this.lbf).rotZ(Math.PI / 2), geoList);
        backSide = new Node(new Transform().translate(this.lbf).rotZ(Math.PI).rotX(-Math.PI / 2), geoList);
        bottomSide = new Node(new Transform().translate(this.lbf).rotX(Math.PI), geoList);
        rightSide = new Node(new Transform().translate(this.run).rotZ(-Math.PI / 2), geoList);
        topSide = new Node(new Transform().translate(this.run), geoList);
        frontSide = new Node(new Transform().translate(this.run).rotZ(Math.PI).rotX(Math.PI / 2), geoList);

    }
    /**
     * This constructor initializes a new box with ist standard values.
     * @param material the materials of the AxisAlignedBox.
     */
    public AxisAlignedBox (final Material material) {
        super(material);

        this.lbf  = new Point3(-0.5,-0.5,-0.5);
        this.run = new Point3(0.5,0.5,0.5);
        final List<Geometry> geoList = new ArrayList<>();
        geoList.add(new Plane(material));

        leftSide = new Node(new Transform().translate(this.lbf).rotZ(Math.PI / 2), geoList);
        backSide = new Node(new Transform().translate(this.lbf).rotZ(Math.PI).rotX(-Math.PI / 2), geoList);
        bottomSide = new Node(new Transform().translate(this.lbf).rotX(Math.PI), geoList);
        rightSide = new Node(new Transform().translate(this.run).rotZ(-Math.PI / 2), geoList);
        topSide = new Node(new Transform().translate(this.run), geoList);
        frontSide = new Node(new Transform().translate(this.run).rotZ(Math.PI).rotX(Math.PI / 2), geoList);

    }

    /**
     * This method calculates the intersections of the ray with the AxisAlignedBox.
     * @param r
     * @return if the ray hits the box this method returns a new hit, else the return is null.
     */
    public Hit hit (final Ray r) {

        if (r == null) throw new IllegalArgumentException("m has to be not null");


        final Hit[] x = new Hit[]{leftSide.hit(r), rightSide.hit(r)};
        final Hit[] y = new Hit[]{topSide.hit(r), bottomSide.hit(r)};
        final Hit[] z = new Hit[]{frontSide.hit(r), backSide.hit(r)};

        final Set<Hit> hitBox = new HashSet<>();

        for (int i = 0; i < 2; i++) {

            if (x[i] != null) {

                final Point3 p = r.at(x[i].t);
                if(p.y >= lbf.y && p.y <= run.y && p.z >= lbf.z && p.z <= run.z) hitBox.add(x[i]);

            }
        }

        for (int i = 0; i < 2; i++) {

            if (y[i] != null) {

                final Point3 p = r.at(y[i].t);
                if(p.x >= lbf.x && p.x <= run.x && p.z >= lbf.z && p.z <= run.z) hitBox.add(y[i]);

            }
        }

        for (int i = 0; i < 2; i++) {

            if (z[i] != null){

                final Point3 p = r.at(z[i].t);
                if(p.x >= lbf.x && p.x <= run.x && p.y >= lbf.y && p.y <= run.y) hitBox.add(z[i]);

            }
        }

        double t = Double.MAX_VALUE;
        final double t2 = 0.000001;
        Hit rHit = null;

        for (final Hit h : hitBox) {

            if (h == null)continue;
            if (h.t < t && t > 0 && h.t > t2) {

                t = h.t;
                rHit = h;
            }
        }

        return rHit;
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
