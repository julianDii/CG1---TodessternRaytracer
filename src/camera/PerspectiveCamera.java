package camera;


import raytracer.Ray;
import matVecLib.Point2;
import matVecLib.Point3;
import matVecLib.Vector3;
import sampling.SamplingPattern;

import java.util.*;

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
	 * This constructor creates a new Perspective Camera.
	 * @param e position of camera (eye position)
	 * @param g gaze direction
	 * @param t Up-Vector
	 * @param samplingPattern
	 * @param angle The angle of the Perspective Camera
	 */

    public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, SamplingPattern samplingPattern, final double angle) {
        super(e, g, t,samplingPattern);

		if(e == null)throw new IllegalArgumentException("e has to be not null");
		if(g == null)throw new IllegalArgumentException("g has to be not null");
		if(t == null)throw new IllegalArgumentException("m has to be not null");
		if(samplingPattern == null)throw new IllegalArgumentException("samplingPattern has to be not null");

		this.angle = angle;
		this.samplingPattern.regularPattern2();
    }

	/**
	 * This method calculates the the ray for the the given parameters.
	 * @param width The width of the screen.
	 * @param height The height of the screen.
	 * @param x The x-coordinate of the pixel.
	 * @param y The y-coordinate of the pixel
	 * @return The new Ray for a special pixel.
	 */

	@Override
	public Set<Ray> rayFor (int width, int height, int x, int y) {

		Vector3 vXy;
		Vector3 uXx;
		Vector3 r;
		List<Ray> rayArr = new ArrayList<>();
		Set<Ray> raySet;


		for (final Point2 p : samplingPattern.allPoints) {


				uXx = u.mul(x - ((width - 1) / 2));
				vXy = v.mul(y - ((height - 1) / 2));
				r = w.mul(-1).mul((height / 2) / Math.tan(angle / 2)).add(uXx).add(vXy).add(u.mul(p.x)).add(v.mul(p.y));
				Ray ray = new Ray(e, r.normalized());
				rayArr.add(ray);

		}

		raySet = new HashSet<>(rayArr);

		return raySet;

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
		return Double.doubleToLongBits(angle) == Double.doubleToLongBits(other.angle);
	}
    
    
}
