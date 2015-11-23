package licht;

import raytracer.Color;
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
	 * This constructor defines a light and it's color.
	 * @param color
	 */

	public Light (Color color){

		if(color==null)throw new IllegalArgumentException("color has to be not null");

		this.color = color;
	}

	/**
	 * This method checks if a point is illuminated from the light or not.
	 * @param point The point that we want to check.
	 * @return true if the point is illuminated or false if not.
	 */

	public abstract boolean illuminates (Point3 point);

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
