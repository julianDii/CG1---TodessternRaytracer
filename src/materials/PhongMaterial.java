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
package materials;

import lights.Light;
import raytracer.Color;
import raytracer.Tracer;
import raytracer.World;
import raytracer.Hit;
import matVecLib.Normal3;
import matVecLib.Vector3;

/**
 * This class represents a PhongMaterial.
 * Developers Robert Ullmann, Julian Dobrot.
 * @author Charlie
 *
 */

public class PhongMaterial extends Material {

	/**
	 * The diffuse color component of the material.
	 */
	final Color diffuse;
	/**
	 * The specular color component of the material.
	 */
	final Color specular;
	/**
	 * The exponent component of the material..
	 */
	final int exponent;

	/**
	 * This constructor builds a new PhongMaterial with it's components.
	 * @param diffuse  The diffuse color component of the material.
	 * @param specular The specular color component of the material.
	 * @param exponent The exponent component of the material.
	 */

	public PhongMaterial(final Color diffuse, final Color specular, final int exponent){

		this.diffuse = diffuse;
		this.exponent = exponent;
		this.specular = specular; 
		
	}

	/**
	 * This method calculates the color of an hit object.
	 * @param hit
	 * @param world
	 * @return The color the hit.
	 */
	public Color colorFor (final Hit hit,final World world, final Tracer tracer) {

		// e = vector to the observer
		// r = reflected Vector l

		Vector3 e;
		Vector3 lref;

		Color c2 = new Color(0,0,0);
		Color c3;
		Color ambient = world.ambient;
		Normal3 normal = hit.nor;

		for (Light li : world.lightList) {

			if (li.illuminates(hit.ray.at(hit.t),world)) {

				e = hit.ray.o.sub(hit.ray.at(hit.t)).normalized();
				Vector3 l = li.directionFrom(hit.ray.at(hit.t)).normalized();
				lref = l.reflectedOn(normal).normalized();
				c2 = diffuse.mul(li.color).mul(Math.max(0, normal.dot(l)));
				c3 = specular.mul(li.color).mul(Math.pow(Math.max(0,e.dot(lref)),exponent));
				c2 = c2.add(c3);
			}
		}
		return diffuse.mul(ambient).add(c2);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PhongMaterial that = (PhongMaterial) o;

		if (exponent != that.exponent) return false;
		if (!diffuse.equals(that.diffuse)) return false;
		return specular.equals(that.specular);

	}

	@Override
	public int hashCode() {
		int result = diffuse.hashCode();
		result = 31 * result + specular.hashCode();
		result = 31 * result + exponent;
		return result;
	}

	@Override
	public String toString() {
		return "PhongMaterial{" +
				"diffuse=" + diffuse +
				", specular=" + specular +
				", exponent=" + exponent +
				'}';
	}
}
