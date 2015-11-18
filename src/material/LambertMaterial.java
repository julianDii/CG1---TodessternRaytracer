package material;
import raytracer.Color;
import raytracer.World;

import raytracer.geometrie.Hit;
import raytracer.matVecLib.Normal3;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * This class represents a diffuse reflecting material.
 * Developer Charline Waldrich
 * @author Charlie
 * @return
 */

public class LambertMaterial extends Material {

	/**
	 * The color component of the material
	 */
	final Color color;

	/**
	 * This constructor represents a new LambertMaterial with its color.
	 * @param color
	 */
	public LambertMaterial(Color color){
		this.color = color; 

	}

	/**
	 * This method calculates the color for an hit object.
	 * @param hit
	 * @param world
	 * @return The color for a hit object
	 */
	
	public Color colorFor (Hit hit, World world){

		//l = Vektor zur lichtquelle---ray origin
		//n = Normale der Oberfläche
		// = cr*clmax(0,n*l)
		final Point3 lightOrigin= hit.ray.o;
		final Point3 crossPoint = hit.ray.at(hit.t);
		final Vector3 l = crossPoint.sub(lightOrigin);
		System.out.println(l);

		final Normal3 normal = hit.nor;
		final double p = hit.t;

		if (hit!= null){
			return new Color(1,1,1);
		}


		return null;
	}
}
