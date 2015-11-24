package licht;

import raytracer.Color;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;


/**
 *
 * Developer Julian Dobrot
 * @author Charlie
 *
 */

public class DirectionalLight extends Light {

	/**
	 * The direction Vector of the light.
	 */
	final Vector3 direction;
	
	public DirectionalLight(Color color, Vector3 direction) {
		super(color);
		this.direction = direction;
	}

	public boolean illuminates(Point3 point){
		return true;
		
	}

	/**
	 *
	 * @param point The illuminated point.
	 * @return
	 */
	
	public Vector3 directionFrom(Point3 point){
		return direction.mul(-1).normalized();
		
	}
}
