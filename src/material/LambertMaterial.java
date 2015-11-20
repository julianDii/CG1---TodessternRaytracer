package material;
import licht.Light;
import licht.PointLight;
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

		// cr = Farbe der Oberfläche
		// cl = Farbe der Lichtquelle
		//l = Vektor zur lichtquelle
		//n = Normale der Oberfläche

		Color c2 = new Color(0,0,0);
		Color cl=world.ambient;
		Normal3 normal = hit.nor;

		for (Light li:world.lightList){
			if(li.illuminates(hit.ray.at(hit.t))) {

				Vector3 l = li.directionFrom(hit.ray.at(hit.t));
				c2 = color.mul(cl).mul(Math.max(0, normal.dot(l.normalized())));

			}

			Color c = new Color(0,0,0);
			c=color.mul(cl).add(c2);
			System.out.println(c);


			return c;

		}


		return world.backgroundcolor;
	}
}
