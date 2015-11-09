package raytracer.geometrie;

import raytracer.Color;
import raytracer.Ray;

/**
 * Created by Juliand on 03.11.15.
 */
public abstract class Geometry {

    public final Color color;

    public Geometry(final Color color){
        this.color=color;
    }
    
    public abstract Hit hit(final Ray r);

}
