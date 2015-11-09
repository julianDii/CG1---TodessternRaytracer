package raytracer.camera;


import raytracer.Ray;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * The OrthographicCamera class represents a OrthographicCamera.
 * @author Robert Ullmann
 */
public class OrthographicCamera extends Camera {

    final double s;

    
	/**
	 * Constructs an OrthographicCamera.
	 * @param e is the Position of Camera
	 * @param g is the Direction of Camera
	 * @param t is the Rotation of Camera 
	 * @param s is the Scaling Factor of Camera
	 */
    public OrthographicCamera(Point3 e, Vector3 g, Vector3 t,double s) {
        super(e, g, t);
        this.s=s;
    }


    @Override
    public Ray rayFor(int width, int height, int x, int y) {
    	// a is aspect ratio (width/height)
    	double a = ((double)width)/((double)height);
    	
    	// d = -w
    	Vector3 d=this.w.mul(-1);
    	// s*((x-(width-1)/2)/(width-1))*u
    	Vector3 sxwu = this.u.mul(this.s*((x-(width-1)/((double)2))/((double)(width-1))));
    	// s*((y-(height-1)/2)/(height-1))*v     		
    	Vector3 syhv = this.v.mul(this.s*((y-(height-1)/((double) 2))/((double)(height-1))));
    	// 
    	Vector3 xyVector = sxwu.mul(a).add(syhv);
    	// 
    	// e + ...
    	Point3 oPoint = e.add(xyVector);
    	return new Ray(oPoint,d);
    	
    }

	@Override
	public String toString() {
		return "OrthographicCamera [s=" + s + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(s);
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
		OrthographicCamera other = (OrthographicCamera) obj;
		if (Double.doubleToLongBits(s) != Double.doubleToLongBits(other.s))
			return false;
		return true;
	} 
    
}
