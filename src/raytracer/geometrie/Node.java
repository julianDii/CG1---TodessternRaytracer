package raytracer.geometrie;

import raytracer.Ray;

import java.util.List;

/**
 * This class represents a node object
 * Created by Juliand on 15.12.15.
 */
public class Node extends Geometry {

    /**
     * This constructor builds a new node.
     * @param t
     * @param list
     */
    public Node(Transform t,List<Geometry>list ){}

    /**
     * The given ray will be transformed.
     * @param r
     * @return
     */
    public Hit hit(Ray r) {
        return null;
    }

    @Override
    public String toString() {
        return "Node{}";
    }

}
