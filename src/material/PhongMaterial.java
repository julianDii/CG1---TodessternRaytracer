package material;

import licht.Light;
import raytracer.Color;
import raytracer.World;
import raytracer.geometrie.Hit;
import raytracer.matVecLib.Normal3;
import raytracer.matVecLib.Vector3;

/**
 * Developer Robert Ullmann
 * @author Charlie
 *
 */

public class PhongMaterial extends Material {

	final Color diffuse; 
	final Color specular; 
	final int exponent; 
	
	public PhongMaterial(Color diffuse, Color specular, int exponent){
		this.diffuse = diffuse; 
		this.exponent = exponent;
		this.specular = specular; 
		
	}
	
	public Color colorFor(Hit hit, World world){

		// e = vector zum Betrachter
		// r = reflectierter Vecrtor l
		Vector3 e = new Vector3(0,0,0);

		Vector3 lref = new Vector3(0,0,0);

		Color c2 = new Color(0,0,0);
		Color c3 = new Color(0,0,0);
		Color ambient =world.ambient;
		Normal3 normal = hit.nor;
		for (Light li:world.lightList){
			if(li.illuminates(hit.ray.at(hit.t))) {
				e= hit.ray.o.sub(hit.ray.at(hit.t)).normalized();
				Vector3 l = li.directionFrom(hit.ray.at(hit.t)).normalized();
				lref=l.reflectedOn(normal).normalized();
				c2 = diffuse.mul(li.color).mul(Math.max(0, normal.dot(l)));
				c3 = specular.mul(li.color).mul(Math.pow(Math.max(0,e.dot(lref)),exponent));
				c2 = c2.add(c3);

			}
		}
		return diffuse.mul(0).add(c2);
	}

}
