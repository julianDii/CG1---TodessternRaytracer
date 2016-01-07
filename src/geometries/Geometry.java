package geometries;

import materials.Material;
import materials.SingleColorMaterial;
import raytracer.Color;
import raytracer.Hit;
import raytracer.Ray;

/**
 * This Class represents the absract geometry.
 * Created by Juliand on 03.11.15.
 */
public abstract class Geometry {

    /**
     * The materials component of the geometry.
     */

    public final Material material;

    /**
     * This constructor builds a geometry with a singlecolormaterial.
     */
    public Geometry(){

        material = new SingleColorMaterial(new Color(0, 0, 0));
    }

    /**
     * This contructor builds a geometry with a materials.
     * @param material
     */

    public Geometry( Material material){

        if(material==null)throw new IllegalArgumentException("materials have to be not null");
        this.material = material;
    }

    /**
     * This class calculates hits with the given ray.
     * @param r
     */

    public abstract Hit hit(final Ray r);


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Geometry geometry = (Geometry) o;

        return !(material != null ? !material.equals(geometry.material) : geometry.material != null);

    }

    @Override
    public int hashCode() {
        return material != null ? material.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "materials=" + material +
                '}';
    }
}
