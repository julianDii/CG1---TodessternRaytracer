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
package lights;

import raytracer.*;
import geometries.Geometry;
import matVecLib.Point3;
import matVecLib.Vector3;


/**
 * This class represents a directional light.
 * Developer Julian Dobrot
 * @author Charlie
 */

public class DirectionalLight extends Light {

	/**
	 * The direction Vector of the light.
	 */
	final Vector3 direction;

	/**
	 * This constructor defines a new Directional light.
	 * @param color
	 * @param direction
	 * @param castShadows
	 */

	public DirectionalLight (Color color, Vector3 direction, boolean castShadows) {

		super(color,castShadows);
		this.direction = direction;

	}

	/**
	 *
	 * @param point The point that we want to check.
	 * @return true as lon we implement no shadows
	 */

	public boolean illuminates (final Point3 point, final World world) {

		Ray r = new Ray(point, directionFrom(point));

		if (castShadows == true) {
			for (Geometry g : world.list) {

				double t2=0;
				Hit h=g.hit(r);

				if(h!=null){
					t2=h.t;
				}
				if (t2 >= Constants.EPSILON && h!=null) {

					return false;
				}

			}
			return true;

		}return false;
	}

	/**
	 * This method calculates the direction of the directional light.
	 * @param point The illuminated point.
	 * @return The vector where the light comes from.
	 */
	
	public Vector3 directionFrom (final Point3 point) {

		return direction.mul(-1).normalized();
		
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		DirectionalLight that = (DirectionalLight) o;

		return direction.equals(that.direction);

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + direction.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "DirectionalLight{" +
				"direction=" + direction +
				'}';
	}
}
