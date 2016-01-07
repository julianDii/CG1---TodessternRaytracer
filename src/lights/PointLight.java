package lights;

import raytracer.Color;
import raytracer.Ray;
import raytracer.World;
import geometries.Geometry;
import raytracer.Hit;
import matVecLib.Point3;
import matVecLib.Vector3;

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

	public PointLight(final Color color, final Point3 position, boolean castShadows) {

		super(color,castShadows);
		this.position = position;
	}

	/**
	 * This method checks if a point is illuminated or not (shadow).
	 * @param point The point that we want to check.
	 * @param world
	 * @return true if the point is illuminated or if for shadow.
	 */


	public boolean illuminates(final Point3 point, final World world) {

		Ray r = new Ray(point, directionFrom(point));

		double obereGrenze= r.tOf(position);
		double untereGrenze= 0.00001;

		if(castShadows==true) for (final Geometry g : world.list) {

			double t2 = 0;
			Hit h = g.hit(r);

			if (h != null) {
				t2 = h.t;
			}
			if (t2 >= untereGrenze && t2 <= obereGrenze) {

				return false;
			}

		}return true;
	}

	/**
	 * The Method returns the Vector l which points from the parameter Point3 "point" to 
	 * the source of the light (Point3 position). 
	 */
	public Vector3 directionFrom(final Point3 point) {

		Vector3 l = position.sub(point);

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
