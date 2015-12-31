package raytracer.camera;


import raytracer.Ray;
import raytracer.matVecLib.Point2;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;
import sampling.SamplingPattern;

import java.util.*;

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
	 * @param samplingPattern
	 * @param s is the Scaling Factor of Camera
	 */
    public OrthographicCamera(final Point3 e,final Vector3 g,final Vector3 t,final SamplingPattern samplingPattern,final double s) {
        super(e, g, t,samplingPattern);

		if(e==null)throw new IllegalArgumentException("e has to be not null");
		if(g==null)throw new IllegalArgumentException("g has to be not null");
		if(t==null)throw new IllegalArgumentException("m has to be not null");
		if(samplingPattern==null)throw new IllegalArgumentException("samplingPattern has to be not null");

		this.s=s;
		this.samplingPattern.regularPattern2();
    }

	@Override
	public Set<Ray> rayFor(int width, int height, int x, int y) {


		List<Ray> rayArr= new ArrayList<>();
		Set<Ray>raySet= null;


		// a is aspect ratio (width/height)
		double a = ((double)width)/((double)height);

		double pixelWidth=a*s/width;
		double pixelHeight=s/height;


		for (Point2 p :samplingPattern.allPoints) {

			if (p.x >= -0.5 && p.x <= 0.5 && p.y >= -0.5 && p.y <= 0.5) {

				// d = -w
				Vector3 d = this.w.mul(-1);
				// s*((x-(width-1)/2)/(width-1))*u
				Vector3 sxwu = this.u.mul(this.s * ((x - (width - 1) / ((double) 2)) / ((double) (width - 1)))).add(u.mul(p.x).mul(pixelWidth));
				// s*((y-(height-1)/2)/(height-1))*v
				Vector3 syhv = this.v.mul(this.s * ((y - (height - 1) / ((double) 2)) / ((double) (height - 1)))).add(v.mul(p.y).mul(pixelHeight));
				//
				Vector3 xyVector = sxwu.mul(a).add(syhv);
				//
				// e + ...
				Point3 oPoint = e.add(xyVector);

				Ray ray = new Ray(oPoint, d);
				rayArr.add(ray);
			}
		}
		raySet = new HashSet<>(rayArr);

		return raySet;
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
