/*
 * MIT License
 *
 * Copyright (c) 2016 Julian Dobrot
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package raytracer;

import geometries.Geometry;
import matVecLib.Normal3;
import texture.TextureCoord2D;

/**
 * An Hit object is created if the given ray (from the camera perspective) hits the chosen
 * object in our world. 
 * Created by Juliand on 03.11.15. 
 * Developer Charline Waldrich
 */
public class Hit {

	/**
	 * the t component of the hit.
	 */

	public final double t;

	/**
	 * The ray component of the hit.
	 */

	public final Ray ray;

	/**
	 * The Geometrie component of the hit.
	 */

	public final Geometry geo;

	/**
	 * The normal of the intesection point.
	 */
	public final Normal3 nor;


	/**
	 * The texture coordinate component of hte hit.
	 */
	public final TextureCoord2D tex2d;
	
	/**
	 * Initializes a new hit object.
	 * @param t: 	is the factor with which the direction vector of ray needs to be multiplied and 
	 * 				then added to the starting point of the ray in order to 'hit' the object
	 * @param ray	holds the ray (with its origin point and the direction vector)
	 * @param geo	holds the chosen geometry (with which we search the rays intersection)
	 */
	public Hit (final double t, final Ray ray, final Geometry geo, final Normal3 nor, final TextureCoord2D tex2d) {

		if (ray == null) throw new IllegalArgumentException("The Ray should not be null.");
		if (geo == null) throw new IllegalArgumentException("geo has to be not null");
		if (nor == null) throw new IllegalArgumentException("nor should ne not null");
		if (tex2d == null) throw new IllegalArgumentException("tex2d should be not null");

		this.t = t;
		this.ray = ray;
		this.geo = geo;
		this.nor = nor;
		this.tex2d = tex2d;

	}

	@Override
	public String toString() {
		return "Hit [t=" + t + ", ray=" + ray + ", geo=" + geo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((geo == null) ? 0 : geo.hashCode());
		result = prime * result + ((ray == null) ? 0 : ray.hashCode());
		long temp;
		temp = Double.doubleToLongBits(t);
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
		Hit other = (Hit) obj;
		if (geo == null) {
			if (other.geo != null)
				return false;
		} else if (!geo.equals(other.geo))
			return false;
		if (ray == null) {
			if (other.ray != null)
				return false;
		} else if (!ray.equals(other.ray))
			return false;
		return Double.doubleToLongBits(t) == Double.doubleToLongBits(other.t);
	}

}
