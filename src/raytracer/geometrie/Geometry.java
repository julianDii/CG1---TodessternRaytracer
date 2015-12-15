package raytracer.geometrie;

import material.Material;
import material.SingleColorMaterial;
import raytracer.Color;
import raytracer.Ray;

/**
 * Created by Juliand on 03.11.15.
 */
public abstract class Geometry {

    public final Material material;

    public Geometry(){

        material = new SingleColorMaterial(new Color(0, 0, 0));
    }

    public Geometry(final Material material){

        if(material==null)throw new IllegalArgumentException("material have to be not null");
        this.material = material;
    }

    public abstract Hit hit(final Ray r);

}
