package materials;

import raytracer.Color;
import raytracer.Tracer;
import raytracer.World;
import raytracer.Hit;
import texture.Texture;

/**
 * This class represents a materials with a given color.
 * Developer Julian Dobrot
 * @author Charlie
 *
 */
public class SingleColorMaterial extends Material {

	/**
	 * The color of the SingleColorMaterial.
	 */
	
	final Color color;

	/**
	 * The texture of the SingleColorMaterial.
	 */
	final Texture texture;

	/**
	 * This constructor builds a new SingleColormaterial with it's color.
	 * @param color
	 */
	public SingleColorMaterial(final Color color){

		if(color==null)throw new IllegalArgumentException("color has to be not null");
		this.color= color;
		this.texture=null;
		
	}

	/**
	 * This contructor creates a new SingleColorMaterial with  texture.
	 * @param texture The texture of the materials
	 */
	public SingleColorMaterial(final Texture texture){
		this.texture=texture;
		this.color=null;
	}

	/**
	 * this implementation of the colorFor method just returns the color from the object.
	 * @param hit
	 * @param world
	 * @return the given color.
	 */

	public Color colorFor (final Hit hit,final World world,final Tracer tracer){

		if (hit==null)throw new IllegalArgumentException("hit has to be not null");
		if (world==null)throw new IllegalArgumentException("world has to be not null");
		if (tracer==null)throw new IllegalArgumentException("tracer has to be not null");

		if (texture !=null){
			return this.texture.colorFor(hit.tex2d.u,hit.tex2d.v);
		}

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
