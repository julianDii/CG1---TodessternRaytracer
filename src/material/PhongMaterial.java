package material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometrie.Hit;

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
		return null; 
	}
	
	
}
