package material;

import licht.Light;
import raytracer.Color;
import raytracer.World;
import raytracer.geometrie.Geometry;
import raytracer.geometrie.Hit;

/**
 * Developer Charline Waldrich
 * @author Charlie
 *
 */

public class LambertMaterial extends Material {

	final Color color; 
	
	public LambertMaterial(Color color){
		this.color = color; 

	}
	
	public Color colorFor (Hit hit, World world){
		return null; 
	}
}
