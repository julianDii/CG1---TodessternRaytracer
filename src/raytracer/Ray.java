package raytracer;

import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * Created by Charline Waldrich, 3.11.2015
 */
public class Ray {

	public final Point3 o;
	public final Vector3 d;
	
	/**
	 * Initializes an instance of ray.
	 * @param o: starting point of the rays origin
	 * @param d: vector of its direction
	 */
	public Ray(final Point3 o, final Vector3 d) {

		this.o = o;
		this.d = d;

	}

	/**
	 * This method returns a point with its coordinates after following the
	 * direction vector of the ray until we reach the given length.
	 * 
	 * @param t: is the given double value of the length we want the direction
	 *           vector to have
	 * @returns the point and its new coordinates
	 */
	public Point3 at(double t) {
		return o.add(d.mul(t));
	}

	/**
	 * This method is used to get the double value of the distance of two
	 * points.
	 * 
	 * @param p:	is the point of which we want to know the distance to our
	 *            	position vector of the ray
	 * @returns a 	double which is the distance of the given point p and o (starting
	 * 				point of the ray) from each other.
	 */
	public double tOf(Point3 p) {
		if (p == null)
			throw new IllegalArgumentException("The startinpoint of the ray should not be undefined.");
		return (p.sub(o)).magnitude / d.magnitude;
	}

	@Override
	public String toString() {
		return "Ray [o=" + o + ", d=" + d + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((d == null) ? 0 : d.hashCode());
		result = prime * result + ((o == null) ? 0 : o.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		if (d == null) {
			if (other.d != null)
				return false;
		} else if (!d.equals(other.d))
			return false;
		if (o == null) {
			if (other.o != null)
				return false;
		} else if (!o.equals(other.o))
			return false;
		return true;
	}

}
