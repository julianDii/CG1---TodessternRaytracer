package geometries;

import materials.Material;
import matVecLib.Mat3x3;
import matVecLib.Normal3;
import matVecLib.Point3;
import matVecLib.Vector3;
import raytracer.Hit;
import raytracer.Ray;
import texture.TextureCoord2D;

/**
 * The Triangle class represents the geometry Triangle.
 *
 * @author Robert Ullmann,Julian Dobrot
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
	 * The first normal.
	 */
	public final Normal3 an;

	/**
	 * The second normal.
	 */
	public final Normal3 bn;

	/**
	 * the third normal.
	 */
	public final Normal3 cn;

	/**
	 * The first texture coordinate.
	 */
	public final TextureCoord2D at;

	/**
	 * The second texture coordinate.
	 */
	public final TextureCoord2D bt;

	/**
	 * The third texture coordinate.
	 */
	public final TextureCoord2D ct;



	/**
	 * This contructor creates a new triangle.
	 * @param a The first point component of the triangle.
	 * @param b The second point component of the triangle.
	 * @param c The third point component of the triangle.
	 * @param an The first normal component of the triangle.
	 * @param bn The second normal component of the triangle.
	 * @param cn The third normal component of the triangle.
	 * @param material The materials of the triangle.
	 * @param at The first texture coordinate component of the triangle.
	 * @param bt The second texture coordinate component of the triangle.
	 * @param ct The third texture coordinate component of the triangle.
	 */
    public Triangle (final Point3 a, final Point3 b, final Point3 c, final Normal3 an, final Normal3 bn, final Normal3 cn,
					final Material material, final TextureCoord2D at, final TextureCoord2D bt, final TextureCoord2D ct) {

		super(material);

		if (material == null)throw new IllegalArgumentException(" materials can not be null!");
    	if (a == null || b == null || c == null ) throw new IllegalArgumentException(" points can not be null!");
		if (an == null || bn == null || cn == null ) throw new IllegalArgumentException(" normals can not be null!");
		if (at == null || bt == null || ct == null ) throw new IllegalArgumentException("texture coordinates can not be null!");

        this.a = a;
        this.b = b;
        this.c = c;
		this.an = an;
		this.bn = bn;
		this.cn = cn;
		this.at = at;
		this.bt = bt;
		this.ct = ct;


	}

	/**
	 * This constructor creates a triangle.
	 * @param a The first point component of the triangle.
	 * @param b The second point component of the triangle.
	 * @param c The third point component of the triangle.
	 * @param material The materials of hte traingle.
	 */
	public Triangle (final Point3 a, final Point3 b, final Point3 c, final Material material) {

		this(a, b, c,
				b.sub(a).x(c.sub(a)).normalized().asNormal(),
				b.sub(a).x(c.sub(a)).normalized().asNormal(),
				b.sub(a).x(c.sub(a)).normalized().asNormal(),
				material,
				new TextureCoord2D(0, 1), new TextureCoord2D(1, 0), new TextureCoord2D(0, 0));

	}

    @Override
    public Hit hit (final Ray r) {

        if (r == null) {
            throw new IllegalArgumentException("Cannot be null!");
        }

		double beta = 0;
		double gamma = 0;
		double t;
		double alpha = 1.0 - beta - gamma;

		final Mat3x3 A = new Mat3x3(

				a.x-b.x, a.x-c.x, r.d.x,
				a.y-b.y, a.y-c.y, r.d.y,
				a.z-b.z, a.z-c.z, r.d.z);

		if (A.determinant == 0) {

			return null;

		}

		final Vector3 sv = new  Vector3(a.x-r.o.x, a.y-r.o.y, a.z-r.o.z);
		final Mat3x3 A1 = A.changeCol1(sv);
		final Mat3x3 A2 = A.changeCol2(sv);
		final Mat3x3 A3 = A.changeCol3(sv);

		beta  = A1.determinant/A.determinant;
		gamma = A2.determinant/A.determinant;
		t     = A3.determinant/A.determinant;

		if((beta > 0 && gamma > 0 ) && beta + gamma <= 1){
			final Normal3 nc = an.mul(alpha).add(bn.mul(beta)).add(cn.mul(gamma));
			final TextureCoord2D tc = at.mul(alpha).add(bt).mul(beta).add(ct).mul(gamma);
			return new Hit(t, r,this,nc ,tc);
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
