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

		final Normal3 an = b.sub(a).asNormal();
		final Normal3 bn = c.sub(b).asNormal();
		final Normal3 cn = a.sub(c).asNormal();
    	
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
			return new Hit(t, r, this,an.mul(t).add(bn.mul(beta)).add(cn.mul(gamma)));
		}
		return null;
    }
}
