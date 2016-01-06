package raytracer;

import java.util.ArrayList;
import java.util.List;

import licht.Light;
import material.Tracer;
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
	public final Color backgroundcolor;
	public final Color ambient;
	/**
	 * List contains all geometric objects chosen by the user.
	 */
	public final List<Geometry> list = new ArrayList<>();
	
	/**
	 * List contains all object of light sources chosen by the user.
	 */
	public final List<Light> lightList = new ArrayList<>();

	/**
	 * The refractive index.
	 */
	public final double refractionIndex;


	public World (final Color color,final Color ambient,final double refractionIndex){
		this.backgroundcolor = color;
		this.ambient = ambient;
		this.refractionIndex = refractionIndex;
	}

	/**
	 * The method hit tests if ray hits the in "list" saved object. 
	 * @param r: ray which we want to check
	 * @return Color object, background color if ray has no hit on the object. if it does, it
	 * returns the color of the hit object
	 */
	public Color hit (Ray r){
		Hit hit0 = null;
		for (final Geometry g: list){
			Hit hit1 = g.hit(r);
			if (hit0 == null || (hit1 != null && hit1.t < hit0.t)) hit0 = hit1;
		}
		if (hit0 == null) return backgroundcolor;


		return hit0.geo.material.colorFor(hit0, this, new Tracer(6,this));
	}

	/**
	 * This method tests if the ray hits the in "list" saved object
	 * @param ray  ray which we want to check.
	 * @return the minHit with the Geometry.
	 */
	public Hit hitt(Ray ray){
		Hit minHit = null;
		double min_t = Double.MAX_VALUE;
		for (final Geometry element : list) {
			final Hit hit = element.hit(ray);
			if ((hit != null) && 0.0001 < hit.t && hit.t < min_t){
				minHit = hit;
				min_t = hit.t;
			}
		}
		return minHit;
	}
	
}
