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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		SpotLight spotLight = (SpotLight) o;

		if (Double.compare(spotLight.halfAngle, halfAngle) != 0) return false;
		if (!position.equals(spotLight.position)) return false;
		return direction.equals(spotLight.direction);

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		long temp;
		result = 31 * result + position.hashCode();
		result = 31 * result + direction.hashCode();
		temp = Double.doubleToLongBits(halfAngle);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "SpotLight{" +
				"position=" + position +
				", direction=" + direction +
				", halfAngle=" + halfAngle +
				'}';
	}
}
