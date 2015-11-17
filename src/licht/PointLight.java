package licht;

import raytracer.Color;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * Developer Charline Waldrich
 * 
 * @author Charlie
 *
 */

public class PointLight extends Light {

	final Point3 position;

	public PointLight(Color color, Point3 position) {
		super(color);
		this.position = position;
	}
	
	public Vector3 directionFrom(Point3 point) {
		Vector3 l = position.sub(point);
		return l;
	}

}
