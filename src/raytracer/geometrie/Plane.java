package raytracer.geometrie;

import raytracer.matVecLib.Normal3;
import raytracer.matVecLib.Point3;
import raytracer.Color;
import raytracer.Ray;
import raytracer.geometrie.Geometry;
import raytracer.geometrie.Hit;

/**
 * Created by Julian on 03.11.15.
 * Developer Charline Waldrich
 */
public class Plane extends Geometry {

    final Point3 a;
    final Normal3 n;

    public Plane(final Point3 a, final Normal3 n, final Color color) {
        super(color);
        this.a=a;
        this.n=n;
    }

    @Override
    public Hit hit(Ray r) {
        return null;
    }

	@Override
	public String toString() {
		return "Plane [a=" + a + ", n=" + n + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((n == null) ? 0 : n.hashCode());
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
		Plane other = (Plane) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (n == null) {
			if (other.n != null)
				return false;
		} else if (!n.equals(other.n))
			return false;
		return true;
	}
    
    
}
