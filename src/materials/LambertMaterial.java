package materials;
import lights.Light;
import raytracer.Color;
import raytracer.Tracer;
import raytracer.World;
import raytracer.Hit;
import matVecLib.Normal3;
import matVecLib.Vector3;

/**
 * This class represents a diffuse reflecting material.
 * Developer Charline Waldrich
 * @author Charlie, Julian
 * @return
 */

public class LambertMaterial extends Material {

	/**
	 * The color component of the material
	 */
	final Color color;

	/**
	 * This constructor represents a new LambertMaterial with its color.
	 * @param color The color of the material.
	 */
	public LambertMaterial (final Color color) {

		if (color == null) throw new IllegalArgumentException("color has to be not null");
		this.color = color;
	}

	/**
	 * This method calculates the color for a hit object.
	 * @param hit The given Hit component for the color calculation.
	 * @param world The given world component for the color calculation.
	 * @return The color for a hit object,
	 */
	
	public Color colorFor (final Hit hit, final World world, final Tracer tracer) {

		Color c2 = new Color(0,0,0);
		Color ambient = world.ambient;
		Normal3 normal = hit.nor;

		for (final Light li : world.lightList) {

			if (li.illuminates(hit.ray.at(hit.t), world)) {

				Vector3 l = li.directionFrom(hit. ray.at(hit.t)).normalized();
				c2 = c2.add(color.mul(li.color).mul(Math.max(0, normal.dot(l))));

			}
		}
		return color.mul(ambient).add(c2);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		LambertMaterial that = (LambertMaterial) o;

		return color.equals(that.color);

	}

	@Override
	public int hashCode() {
		return color.hashCode();
	}

	@Override
	public String toString() {
		return "LambertMaterial{" +
				"color=" + color +
				'}';
	}
}