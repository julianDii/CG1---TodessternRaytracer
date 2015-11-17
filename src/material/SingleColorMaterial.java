package material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometrie.Hit;

/**
 * Developer Julian Dobrot
 * @author Charlie
 *
 */
public class SingleColorMaterial extends Material {
	
	final Color color; 
	
	public SingleColorMaterial(Color color){
		this.color= color; 
		
	}
	
	public Color colorFor (Hit hit, World world){
		return null; 
	}

}
