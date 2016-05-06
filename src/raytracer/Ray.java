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

import matVecLib.Point3;
import matVecLib.Vector3;

/**
 * Created by Charline Waldrich, 3.11.2015
 */
public class Ray {

	public final Point3 o;
	public final Vector3 d;
	
	/**
	 * Initializes an instance of ray.
	 * @param o: starting point of the rays origin
	 * @param d: vector of its direction
	 */
	public Ray  (final Point3 o, final Vector3 d) {

		this.o = o;
		this.d = d;

	}

	/**
	 * This method returns a point with its coordinates after the
	 * direction vector was multiplied with it, and added to the starting point of the ray.
	 * 
	 * @param t: is the given double value we need in order to get to the point
	 * @returns the point and its new coordinates
	 */
	public Point3 at (double t) {

		return o.add(d.mul(t));
	}

	/**
	 * This method is used to get the double value which is needed in order to
	 * get from the starting point of the ray in its given direction to get the Point.
	 * 
	 * @param p:	is given point
	 * @returns  	a double which is the factor one needs to multiply the direction vector and 
	 * 				add to the starting point of the ray, in order to get to the given Point p 
	 */
	public double tOf (Point3 p) {

		if (p == null) throw new IllegalArgumentException("The Point for which you search the Faktor t should not be undefined.");

		return (p.sub(o)).magnitude / d.magnitude;

	}

	@Override
	public String toString() {
		return "Ray [o=" + o + ", d=" + d + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((d == null) ? 0 : d.hashCode());
		result = prime * result + ((o == null) ? 0 : o.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		if (d == null) {
			if (other.d != null)
				return false;
		} else if (!d.equals(other.d))
			return false;
		if (o == null) {
			if (other.o != null)
				return false;
		} else if (!o.equals(other.o))
			return false;
		return true;
	}

}
