package materials;

import lights.Light;
import raytracer.Color;
import raytracer.Ray;
import raytracer.Tracer;
import raytracer.World;
import raytracer.Hit;
import matVecLib.Point3;
import matVecLib.Vector3;

/**
 * This class represents a reflective materials.
 * Created by Juliand on 02.12.15.
 */
public class ReflectiveMaterial extends Material {

    /**
     * The diffuse component of the materials.
     */
    private final Color diffuse;

    /**
     * The specular component of the materials.
     */
    private final Color specular;

    /**
     * The exponent component of the materials
     */
    private final int exponent;

    /**
     * The reflection component of the materials.
     */
    private final Color reflection;

    /**
     * This constructor builds a Reflective materials.
     * @param diffuse The color for the diffuse component of the reflectiveMaterial.
     * @param specular The color for the specular component of the reflectiveMaterial.
     * @param exponent The exponent component of the reflectiveMaterial.
     * @param reflection The color for the reflection component of the reflectiveMaterial.
     */

    public ReflectiveMaterial(final Color diffuse, final Color specular, final int exponent, final Color reflection ){

        if(diffuse==null)throw new IllegalArgumentException("diffuse can not be null");
        if(specular==null)throw new IllegalArgumentException("diffuse can not be null");
        if(reflection==null)throw new IllegalArgumentException("diffuse can not be null");

        this.diffuse=diffuse;
        this.specular=specular;
        this.exponent=exponent;
        this.reflection=reflection;
    }

    /**
     * This method calculates the color for the certain pixel if there is a hit.
     * @param hit The given hit.
     * @param world The given world.
     * @param tracer Teh given tracer.
     * @return The color.
     */
    public Color colorFor(final Hit hit, final World world,final Tracer tracer) {

        if(hit==null)throw new IllegalArgumentException("Diffuse can not be null");
        if(world==null)throw new IllegalArgumentException("World can not be null");
        if(tracer==null)throw new IllegalArgumentException("Tracer can not be null");

        Color returnColor = diffuse.mul(world.ambient);
        final Point3 hitPoint = hit.ray.at(hit.t);
        final double factor = hit.nor.dot(hit.ray.d.mul(-1))*2;
        final Vector3 e = (hit.ray.d.mul(-1));

        for (final Light li : world.lightList){

            if(li.illuminates(hitPoint,world)){


                final Vector3 lightVector = li.directionFrom(hitPoint).normalized();
                final Color lightColor = li.color;
                final Vector3 reflectedVector = lightVector.reflectedOn(hit.nor);
                final double max = Math.max(0,hit.nor.dot(lightVector));
                returnColor = returnColor.add(diffuse.mul(lightColor.mul(max)));
                returnColor = returnColor.add(specular.mul(lightColor.mul(Math.pow(Math.max(0,reflectedVector.dot(e)),exponent))));
            }
        }
        final Color reflColor = tracer.colorFor(new Ray(hitPoint,hit.ray.d.add(hit.nor.mul(factor)).normalized()),world);
        returnColor = (reflection.mul(reflColor.add(returnColor)));

        return returnColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReflectiveMaterial that = (ReflectiveMaterial) o;

        if (exponent != that.exponent) return false;
        if (!diffuse.equals(that.diffuse)) return false;
        if (!specular.equals(that.specular)) return false;
        return reflection.equals(that.reflection);

    }

    @Override
    public int hashCode() {
        int result = diffuse.hashCode();
        result = 31 * result + specular.hashCode();
        result = 31 * result + exponent;
        result = 31 * result + reflection.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ReflectiveMaterial{" +
                "diffuse=" + diffuse +
                ", specular=" + specular +
                ", exponent=" + exponent +
                ", reflection=" + reflection +
                '}';
    }
}
