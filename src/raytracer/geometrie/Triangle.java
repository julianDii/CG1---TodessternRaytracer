package raytracer.geometrie;

import material.Material;
import raytracer.matVecLib.Mat3x3;
import raytracer.matVecLib.Normal3;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;
import raytracer.Ray;

/**
 * The Triangle class represents a Triangle.
 * @author Robert Ullmann
 */
public class Triangle extends Geometry {

	/**
	 * first Point of the Triangle
	 */
	public final Point3 a;

	/**
	 * second Point of the Triangle
	 */
	public final Point3 b;

	/**
	 * third Point of the Triange
	 */
	public final Point3 c;

	/**
	 * T constructor creates a new Triangle
	 * @param a the first Corner-Point, cannot be null.
	 * @param b the second Corner-Point, cannot be null.
	 * @param c the third Corner-Point, cannot be null.
	 */
    public Triangle(final Point3 a, final Point3 b, final Point3 c, final Material material) {
    	super(material);

    	if (a == null || b == null || c == null ) {
            throw new IllegalArgumentException("Cannot be null!");
    	}

        this.a=a;
        this.b=b;
        this.c=c;
    }


    @Override
    public Hit hit(Ray r) {

		final Vector3 v = b.sub(a);
		final Vector3 w = c.sub(a);


        if (r == null) {
            throw new IllegalArgumentException("Cannot be null!");
        }

    	// Variablen f�r cramersche Regel
		double beta = 0;
		double gamma = 0;
		double t = 0;


    	// lineares Gleichungssystem als Matrix
		final Mat3x3 A = new Mat3x3(
				a.x-b.x, a.x-c.x, r.d.x,
				a.y-b.y, a.y-c.y, r.d.y,
				a.z-b.z, a.z-c.z, r.d.z);

		final Vector3 sv = new  Vector3(a.x-r.o.x, a.y-r.o.y, a.z-r.o.z);
		final Mat3x3 A1 = A.changeCol1(sv);
		final Mat3x3 A2 = A.changeCol2(sv);
		final Mat3x3 A3 = A.changeCol3(sv);

		beta=A1.determinant/A.determinant;
		gamma=A2.determinant/A.determinant;
		t=A3.determinant/A.determinant;

		if((beta > 0 && gamma > 0 ) && beta + gamma <= 1){
			return new Hit(t, r, this,v.x(w).asNormal());
		}
		return null;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Triangle triangle = (Triangle) o;

		if (!a.equals(triangle.a)) return false;
		if (!b.equals(triangle.b)) return false;
		return c.equals(triangle.c);

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + a.hashCode();
		result = 31 * result + b.hashCode();
		result = 31 * result + c.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Triangle{" +
				"a=" + a +
				", b=" + b +
				", c=" + c +
				'}';
	}
}
