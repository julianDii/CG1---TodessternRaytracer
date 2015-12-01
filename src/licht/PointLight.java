package licht;

import raytracer.Color;
import raytracer.World;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * This class represents a light which illuminates the scene from a point in all
 * directions. Developer Charline Waldrich
 * 
 * @author Charlie
 *
 */

public class PointLight extends Light {

	/**
	 * The position of the point light.
	 */
	final Point3 position;

	public PointLight(Color color, Point3 position, boolean castShadows) {
		super(color,castShadows);

		this.position = position;
	}

	@Override
	public boolean illuminates(Point3 point, World world) {
		return true;
	}

	/**
	 * The Method returns the Vector l which points from the parameter Point3 "point" to 
	 * the source of the light (Point3 position). 
	 */
	public Vector3 directionFrom(Point3 point) {
		Vector3 l = position.sub(point).normalized();
		return l;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		PointLight that = (PointLight) o;

		return position.equals(that.position);

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + position.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "PointLight{" +
				"position=" + position +
				'}';
	}
}
