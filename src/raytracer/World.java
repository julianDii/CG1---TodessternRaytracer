package raytracer;

import java.util.ArrayList;
import java.util.List;

import raytracer.geometrie.Geometry;
import raytracer.geometrie.Hit;

/**
 * The world class is used to create a world with specific background color and the 
 * from the user chosen objects.
 * @author Charlie
 */
public class World {

	/**
	 * Color background color which is chosen by the user when initializing his world
	 */
	private final Color backgroundcolor;
	/**
	 * List contains all geometric objects chosen by the user.
	 */
	public final List<Geometry> list = new ArrayList<>();
	
	public World (Color color){
		this.backgroundcolor = color;
	}
	
	/**
	 * The method hit tests where and when ray hits the object. 
	 * @param r: ray which we want to check
	 * @return Color object, background color if ray has no hit on the object. if it does, it 
	 * returns the color of the hit object
	 */
	public Color hit (Ray r){
		Hit hit0 = null;
		for (Geometry g: list){
			Hit hit1 = g.hit(r);
			if (hit0 == null|| (hit1 != null && hit1.t < hit0.t)) hit0 = hit1;
		}
		if (hit0 == null) return backgroundcolor;
		return hit0.geo.color;
	}
	
}
