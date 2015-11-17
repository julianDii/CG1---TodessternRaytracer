package licht;

import raytracer.Color;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * Developer Robert Ullmann
 * @author Charlie
 *
 */

public class SpotLight extends Light {

	final Point3 position; 
	final Vector3 direction; 
	final double halfAngle;
	
	public SpotLight(Color color, Point3 position, Vector3 direction, double halfAngle) {
		super(color);
		this.direction = direction; 
		this.halfAngle = halfAngle; 
		this.position = position; 
		
	}
	
	public boolean illuminates(Point3 point){
		return false;
		
	}
	
	public Vector3 directionFrom(Point3 point) {
		Vector3 l = position.sub(point);
		return l;
	}

}
