package licht;

import raytracer.Color;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;


/**
 * This class represents a directional light.
 * Developer Julian Dobrot
 * @author Charlie
 *
 */

public class DirectionalLight extends Light {

	/**
	 * The direction Vector of the light.
	 */
	final Vector3 direction;
	
	public DirectionalLight(Color color, Vector3 direction) {
		super(color);
		this.direction = direction;
	}

	/**
	 *
	 * @param point The point that we want to check.
	 * @return true as lon we implement no shadows
	 */

	public boolean illuminates(Point3 point){
		return true;
		
	}

	/**
	 * This method calculates the direction of the directional light.
	 * @param point The illuminated point.
	 * @return The vector where the light comes from.
	 */
	
	public Vector3 directionFrom(Point3 point){
		return direction.mul(-1).normalized();
		
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		DirectionalLight that = (DirectionalLight) o;

		return direction.equals(that.direction);

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + direction.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "DirectionalLight{" +
				"direction=" + direction +
				'}';
	}
}
