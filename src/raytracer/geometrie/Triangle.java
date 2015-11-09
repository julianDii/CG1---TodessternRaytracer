package raytracer.geometrie;

import raytracer.matVecLib.Mat3x3;
import raytracer.matVecLib.Point3;
import raytracer.Color;
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
	 * @param a the first Point
	 * @param b the second Point
	 * @param c the third Point
	 */
    public Triangle(final Point3 a, final Point3 b, final Point3 c, final Color color) {
        super(color);
        this.a=a;
        this.b=b;
        this.c=c;
    }
    
    
    @Override
    public Hit hit(Ray r) {
    	// Variablen für cramersche Regel
		double beta = 0;
		double gamma = 0;
		double t = 0;
		
		//TODO Lösung des GLeichungssystems determinanten
		
    	// lineares Gleichungssystem als Matrix
		Mat3x3 A = new Mat3x3(
				a.x-b.x, a.x-c.x, r.d.x,
				a.y-b.y, a.y-c.y, r.d.y,
				a.z-b.z, a.z-c.z, r.d.z);
		
		if(gamma>=0 && gamma<=1 && beta>=0 && beta<=1){
			return new Hit(t, r, this);
		}
		return null;
    }
}
