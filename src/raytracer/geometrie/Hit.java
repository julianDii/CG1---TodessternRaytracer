package raytracer.geometrie;

import raytracer.Ray;
import raytracer.geometrie.Geometry;

/**
 * Created by Juliand on 03.11.15.
 * Developer Charline Waldrich
 */
public class Hit {

    final double t;
    final Ray ray;
    final Geometry geo;

    public Hit(final double t, final Ray ray, final Geometry geo){
        this.t=t;
        this.ray=ray;
        this.geo=geo;
    }
}
