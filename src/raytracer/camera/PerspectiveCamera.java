package raytracer.camera;


import raytracer.Ray;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * This class represents a Perspective Camera
 * Created by Julian on 03.11.15.
 * Developer Julian Dobrot
 */
public class PerspectiveCamera extends Camera {

	/**
	 * The angle component of the Perspective Camera.
	 */

    final double angle;

	/**
	 * This contructor creates a new Perspective Camera.
	 * @param e position of camera (eye position)
	 * @param g gaze direction
	 * @param t Up-Vector
	 * @param angle The angle of the Perspective Camera
	 */

    public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, final double angle) {
        super(e, g, t);

		if(e==null)throw new IllegalArgumentException("e have to be not null");
		if(g==null)throw new IllegalArgumentException("g have to be not null");
		if(t==null)throw new IllegalArgumentException("m have to be not null");

		this.angle=angle;
    }

	@Override
    public Ray rayFor(int width, int height, int x, int y) {

		// The origin of the returned Ray is o = e
		// The direction (d) is r/|r|
		// r = -w * (h/2 / tan * alpha) + ( x - (w-1/2))*u + (y-(h-1/2))*v

		final Vector3 ux;
		final Vector3 vx;
		final Vector3 r;

		ux=u.mul(x-((width-1)/2));
		vx=v.mul(y-((height-1)/2));
		r=this.w.mul(-1).mul((height/2)/Math.tan(angle/2)).add(ux.add(vx));

		return new Ray(e,r.normalized());
    }

	@Override
	public String toString() {
		return "PerspectiveCamera [angle=" + angle + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(angle);
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
		PerspectiveCamera other = (PerspectiveCamera) obj;
		if (Double.doubleToLongBits(angle) != Double.doubleToLongBits(other.angle))
			return false;
		return true;
	}
    
    
}
