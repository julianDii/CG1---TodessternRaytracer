package lights;

import raytracer.*;
import geometries.Geometry;
import matVecLib.Point3;
import matVecLib.Vector3;


/**
 * This class represents a directional light.
 * Developer Julian Dobrot
 * @author Charlie
 */

public class DirectionalLight extends Light {

	/**
	 * The direction Vector of the light.
	 */
	final Vector3 direction;

	/**
	 * This constructor defines a new Directional light.
	 * @param color
	 * @param direction
	 * @param castShadows
	 */

	public DirectionalLight (Color color, Vector3 direction, boolean castShadows) {

		super(color,castShadows);
		this.direction = direction;

	}

	/**
	 *
	 * @param point The point that we want to check.
	 * @return true as lon we implement no shadows
	 */

	public boolean illuminates (final Point3 point, final World world) {

		Ray r = new Ray(point, directionFrom(point));

		if (castShadows == true) {
			for (Geometry g : world.list) {

				double t2=0;
				Hit h=g.hit(r);

				if(h!=null){
					t2=h.t;
				}
				if (t2 >= Constants.EPSILON && h!=null) {

					return false;
				}

			}
			return true;

		}return false;
	}

	/**
	 * This method calculates the direction of the directional light.
	 * @param point The illuminated point.
	 * @return The vector where the light comes from.
	 */
	
	public Vector3 directionFrom (final Point3 point) {

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
