package material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometrie.Hit;

/**
 * This class represents a material with a given color.
 * Developer Julian Dobrot
 * @author Charlie
 *
 */
public class SingleColorMaterial extends Material {
	
	final Color color;

	/**
	 * This constructor builds a new SingleColormaterial with it's color.
	 * @param color
	 */
	public SingleColorMaterial(final Color color){

		if(color==null)throw new IllegalArgumentException("color has to be not null");
		this.color= color; 
		
	}

	/**
	 * this implementation of the colorFor method just returns the color from the object.
	 * @param hit
	 * @param world
	 * @return the given color.
	 */

	public Color colorFor (Hit hit, World world, Tracer tracer){
		return this.color;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SingleColorMaterial that = (SingleColorMaterial) o;

		return color.equals(that.color);

	}

	@Override
	public int hashCode() {
		return color.hashCode();
	}

	@Override
	public String toString() {
		return "SingleColorMaterial{" +
				"color=" + color +
				'}';
	}
}
