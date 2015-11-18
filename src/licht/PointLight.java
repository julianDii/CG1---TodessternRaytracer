package licht;

import raytracer.Color;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * This class represents a light which illuminates the scene from a point in all directions.
 * Developer Charline Waldrich
 * 
 * @author Charlie
 *
 */

public class PointLight extends Light {


	/**
	 * The position of the point light
	 */
	final Point3 position;

	public PointLight(Color color, Point3 position) {
		super(color);
		this.position = position;
	}

	@Override
	public boolean illuminates(Point3 point) {

		return true;
	}


	public Vector3 directionFrom(Point3 point) {
		Vector3 l = position.sub(point).normalized();
		return l;
	}

}
