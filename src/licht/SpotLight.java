package licht;

import raytracer.Color;
import raytracer.Ray;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * This class represents a spot light.
 * Developer Robert Ullmann,julian
 * @author Charlie
 */

public class SpotLight extends Light {

	/**
	 * The position of the point light.
	 */
	final Point3 position;

	/**
	 * The direction of the point light.
	 */
	final Vector3 direction;

	/**
	 * The value of hte angle.
	 */
	final double halfAngle;

	/**
	 * This constructor builds a new spot light with it's components.
	 * @param color
	 * @param position
	 * @param direction
	 * @param halfAngle
	 */
	public SpotLight(Color color, Point3 position, Vector3 direction, double halfAngle) {
		super(color);
		this.direction = direction; 
		this.halfAngle = halfAngle; 
		this.position = position; 
		
	}

	/**
	 * Checks if the given point is illuminated.
	 * @param point The point that we want to check.
	 * @return true if yes, false if not.
	 */
	
	public boolean illuminates(Point3 point){


		final double w = Math.acos(direction.normalized().dot(directionFrom(point).mul(-1)));

		if(w<=halfAngle)return true;
		else {return false;}
	}

	/**
	 * Calculates the direction of the Vector from the light source.
	 * @param point The illuminated point.
	 * @return The vector of the light source.
	 */
	public Vector3 directionFrom(Point3 point) {
		Vector3 l = position.sub(point).normalized();
		return l;
	}

}
