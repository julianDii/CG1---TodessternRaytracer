package raytracer.geometrie;

import material.Material;
import raytracer.Ray;

/**
 * Created by Juliand on 03.11.15.
 */
public abstract class Geometry {

    public final Material material;

    public Geometry(final Material material){
        this.material = material;
    }
    
    public abstract Hit hit(final Ray r);

}
