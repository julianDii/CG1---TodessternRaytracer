package raytracer.camera;


import raytracer.Ray;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * Created by Juliand on 03.11.15.
 * Developer Robert Ullmann
 */
public class OrthographicCamera extends Camera {

    final double s;

    public OrthographicCamera(Point3 e, Vector3 g, Vector3 t,double s) {
        super(e, g, t);
        this.s=s;
    }


    @Override
    public Ray rayFor(int width, int height, int x, int y) {
        return null;
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
