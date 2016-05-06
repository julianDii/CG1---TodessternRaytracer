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

import raytracer.Color;
import raytracer.World;
import matVecLib.Point3;
import matVecLib.Vector3;

/**
 * This class represents a light and defines it's functions and attributes. The different lights in the ray tracer
 * have to extend this base class.
 */
public abstract class Light {

	/**
	 * The color of the light.
	 */
	final public Color color;

	/**
	 * Defines if shadows are casted or not.
	 */

	final public boolean castShadows;

	/**
	 * This constructor defines a light and it's color.
	 * @param color
	 * @param castShadows
	 */

	public Light (final Color color, boolean castShadows) {

		if(color == null) throw new IllegalArgumentException("color has to be not null");

		this.color = color;
		this.castShadows=castShadows;
	}

	/**
	 * This method checks if a point is illuminated from the light or not.
	 * @param point The point that we want to check.
	 * @return true if the point is illuminated or false if not.
	 */

	public abstract boolean illuminates (final Point3 point,final World world);

	/**
	 * This method calculates the Vector from the illuminated point to the light.
	 * @param point The illuminated point.
	 * @return The direction from the point.
	 */
	
	public abstract Vector3 directionFrom (final Point3 point);

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Light light = (Light) o;

		return color.equals(light.color);

	}

	@Override
	public int hashCode() {
		return color.hashCode();
	}

	@Override
	public String toString() {
		return "Light{" +
				"color=" + color +
				'}';
	}
}
