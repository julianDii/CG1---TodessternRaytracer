package licht;

import raytracer.Color;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

public abstract class Light {

	final Color color; 
	
	public Light (Color color){
		this.color = color;
	}

	public boolean illuminates (Point3 point){
		return false;
		
	}
	
	public Vector3 directionFrom(Point3 point){
		return null;
	}
}
