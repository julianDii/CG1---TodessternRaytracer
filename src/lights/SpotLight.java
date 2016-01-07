package lights;

import raytracer.Color;
import raytracer.World;
import matVecLib.Point3;
import matVecLib.Vector3;

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
	 * The value of the angle.
	 */
	final double halfAngle;

	/**
	 * This constructor builds a new spot light with it's components.
	 * @param color
	 * @param position
	 * @param direction
	 * @param castShadows
	 * @param halfAngle
	 */
	public SpotLight (final Color color, final Point3 position, boolean castShadows, final Vector3 direction, final double halfAngle) {

		super(color,castShadows);
		this.direction = direction; 
		this.halfAngle = halfAngle; 
		this.position = position; 
		
	}

	/**
	 * Checks if the given point is illuminated.
	 * @param point The point that we want to check.
	 * @return true if yes, false if not.
	 */
	
	public boolean illuminates ( final Point3 point, final World world) {


		final double w = Math.acos(direction.normalized().dot(directionFrom(point).mul(-1)));

		if (w <= halfAngle) {

			return true;
		}

		else {

			return false;
		}

	}

	/**
	 * Calculates the direction of the Vector from the light source.
	 * @param point The illuminated point.
	 * @return The vector of the light source.
	 */
	public Vector3 directionFrom (final Point3 point) {

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
