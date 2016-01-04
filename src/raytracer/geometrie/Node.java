package raytracer.geometrie;

import material.SingleColorMaterial;
import raytracer.Color;
import raytracer.Ray;

import java.util.List;

/**
 * This class represents a node object.
 * A Node can have multiple transformations for a list of geometries.
 * Created by Juliand on 15.12.15.
 */
public class Node extends Geometry {

    /**
     * The transform component of the node.
     */
    public final Transform transT;

    /**
     * The geometry list of the node.
     */
    public final List<Geometry>g;


    /**
     * This constructor builds a new node.
     * @param t The Transform object of the node.
     * @param list  list of the Geometries in this node.
     */
    public Node(final Transform transT,final List<Geometry>g ){

        if(transT==null)throw new IllegalArgumentException("t have to be not null");
        if(g==null)throw new IllegalArgumentException("list have to be not null");

        this.transT=transT;
        this.g=g;
    }

    /**
     * This method calculates hit's with a given ray for all geometries.
     * @param r The given ray.
     * @return A new hit.
     */
    public Hit hit(final Ray r) {
        if(r==null)throw new IllegalArgumentException("r have to be not null");

        Ray transRay = transT.mul(r);
        double t1 = Double.MAX_VALUE;
        double t2 = 0.000001;
        Hit lhit = null;

        for(Geometry geometry:g ){
            Hit h= geometry.hit(transRay);
            if (h==null)continue;
            if (h.t<t1 && h.t > t2){
                t1= h.t;
                lhit = h;
            }
        }

        if(lhit==null)return null;
        return new Hit(lhit.t,r,lhit.geo,transT.mul(lhit.nor),lhit.tex2d);
    }

    @Override
    public String toString() {
        return "Node{}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Node node = (Node) o;

        if (!transT.equals(node.transT)) return false;
        return g.equals(node.g);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + transT.hashCode();
        result = 31 * result + g.hashCode();
        return result;
    }
}
