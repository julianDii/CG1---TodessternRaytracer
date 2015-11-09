package raytracer.geometrie;

import raytracer.Ray;
import raytracer.geometrie.Geometry;

/**
 * Created by Juliand on 03.11.15. Developer Charline Waldrich
 */
public class Hit {

	final double t;
	final Ray ray;
	final Geometry geo;

	public Hit(final double t, final Ray ray, final Geometry geo) {

		this.t = t;
		this.ray = ray;
		this.geo = geo;

		if (ray == null) {
			throw new IllegalArgumentException("The Ray should not be null.");
		}
		if (geo == null) {
			throw new IllegalArgumentException("Choose the Geometry you want to see.");
		}
	}

	@Override
	public String toString() {
		return "Hit [t=" + t + ", ray=" + ray + ", geo=" + geo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((geo == null) ? 0 : geo.hashCode());
		result = prime * result + ((ray == null) ? 0 : ray.hashCode());
		long temp;
		temp = Double.doubleToLongBits(t);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hit other = (Hit) obj;
		if (geo == null) {
			if (other.geo != null)
				return false;
		} else if (!geo.equals(other.geo))
			return false;
		if (ray == null) {
			if (other.ray != null)
				return false;
		} else if (!ray.equals(other.ray))
			return false;
		if (Double.doubleToLongBits(t) != Double.doubleToLongBits(other.t))
			return false;
		return true;
	}

}
