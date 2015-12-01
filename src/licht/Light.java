package licht;

import raytracer.Color;
import raytracer.World;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * This class represents a light and defines it's functions and attributes. The different lights in the ray tracer
 * have to extend this base class.
 */
public abstract class Light {

	/**
	 * The color of the light.
	 */
	final public Color color;

	/**
	 * Defines if shadows are casted or not.
	 */

	final public boolean castShadows;

	/**
	 * This constructor defines a light and it's color.
	 * @param color
	 * @param castShadows
	 */

	public Light (Color color,boolean castShadows){

		if(color==null)throw new IllegalArgumentException("color has to be not null");

		this.color = color;
		this.castShadows=castShadows;
	}

	/**
	 * This method checks if a point is illuminated from the light or not.
	 * @param point The point that we want to check.
	 * @return true if the point is illuminated or false if not.
	 */

	public abstract boolean illuminates (Point3 point, World world);

	/**
	 * This method calculates the Vector from the illuminated point to the light.
	 * @param point The illuminated point.
	 * @return The direction from the point.
	 */
	
	public abstract Vector3 directionFrom(Point3 point);

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Light light = (Light) o;

		return color.equals(light.color);

	}

	@Override
	public int hashCode() {
		return color.hashCode();
	}

	@Override
	public String toString() {
		return "Light{" +
				"color=" + color +
				'}';
	}
}
