package material;
import raytracer.Color;
import raytracer.World;

import raytracer.geometrie.Hit;

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


		return new Color(3,3,3);
	}
}
