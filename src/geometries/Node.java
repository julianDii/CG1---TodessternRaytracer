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

import raytracer.Constants;
import raytracer.Hit;
import raytracer.Ray;
import raytracer.Transform;

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
    public final List<Geometry> g;


    /**
     * This constructor builds a new node.
     * @param transT The Transform object of the node.
     * @param g The geometry list the Geometries in this node.
     */
    public Node (final Transform transT,final List<Geometry>g ) {

        if (transT == null) throw new IllegalArgumentException("t have to be not null");
        if (g == null) throw new IllegalArgumentException("list have to be not null");

        this.transT = transT;
        this.g = g;
    }

    /**
     * This method calculates hit's with a given ray for all geometries.
     * @param r The given ray.
     * @return A new hit.
     */
    public Hit hit (final Ray r) {
        if(r == null) throw new IllegalArgumentException("r have to be not null");

        Ray transRay = transT.mul(r);
        double t1 = Double.MAX_VALUE;

        Hit lhit = null;

        for(Geometry geometry : g ){

            Hit h = geometry.hit(transRay);

            if (h == null) continue;
            if (h.t < t1 && h.t > Constants.EPSILON){
                t1 = h.t;
                lhit = h;
            }
        }

        if (lhit == null) return null;

        return new Hit (lhit.t, r, lhit.geo, transT.mul(lhit.nor), lhit.tex2d);
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
