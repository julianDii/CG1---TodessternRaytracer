package material;

import licht.Light;
import raytracer.Color;
import raytracer.Ray;
import raytracer.World;
import raytracer.geometrie.Hit;
import raytracer.matVecLib.Normal3;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * This class represents a reflective material.
 * Created by Juliand on 02.12.15.
 */
public class ReflectiveMaterial extends Material {

    /**
     * The diffuse component of the material.
     */
    private final Color diffuse;

    /**
     * The specular component of the material.
     */
    private final Color specular;

    /**
     * The exponent component of the material
     */
    private final int exponent;

    /**
     * The reflection component of the material.
     */
    private final Color reflection;

    /**
     * This constructor builds a Reflective material.
     * @param diffuse
     * @param specular
     * @param exponent
     * @param reflection
     */

    public ReflectiveMaterial(final Color diffuse, final Color specular, final int exponent, final Color reflection ){

        this.diffuse=diffuse;
        this.specular=specular;
        this.exponent=exponent;
        this.reflection=reflection;
    }

    /**
     * This method calculates the color for the certain pixel if there is a hit.
     * @param hit
     * @param world
     * @param tracer
     * @return
     */
    public Color colorFor(Hit hit, World world, Tracer tracer) {

        Normal3 nor = hit.nor;
        Point3 p= hit.ray.at(hit.t);
        Color c= world.ambient.mul(diffuse);
        Vector3 e =hit.ray.d.mul(-1).normalized();
        Color c2;
        for(Light li :world.lightList){

            Vector3 l=li.directionFrom(p);
            Vector3 r=l.reflectedOn(nor);
            c2 = new Color(0,0,0);
            if(li.illuminates(p,world)==true){
                c2 = c2.add(li.color.mul(diffuse.mul(Math.max(0,nor.dot(l))).add(li.color).mul(specular)
                        .mul(Math.pow(Math.max(0, r.dot(e)), exponent))));

            }
            c=c.add(c2);
            Ray r = new Ray(p,hit.ray.d.normalized().mul(-1).reflectedOn(nor));
            c=c + reflection.mul(Tracer.reflectiveColor(r, world));
        }




        return c;
    }


}
