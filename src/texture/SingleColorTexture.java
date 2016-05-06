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
package texture;

import raytracer.Color;

/**
 * This Texture simply gets the given color from the parameter.
 * Created by Juliand on 03.01.16.
 */
public class SingleColorTexture extends Texture {

    /**
     * The color component of the texture.
     */
    public final Color color;

    /**
     * This contructor builds a new SingleColorTexture with a color.
     * @param color The color for the Texture.
     */
    public SingleColorTexture(final Color color){
        this.color=color;
    }

    /**
     * This method gives every point of the mapped texture the color of the singleColorMAterial.
     * @param u The u value of the mapped texture.
     * @param v The v value of the mapped texture.
     * @return The color of the SingleColorMaterial.
     */
    @Override
    public Color colorFor(double u, double v) {
        return this.color;
    }


    @Override
    public String toString() {
        return "SingleColorTexture{" +
                "color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleColorTexture that = (SingleColorTexture) o;

        return !(color != null ? !color.equals(that.color) : that.color != null);

    }

    @Override
    public int hashCode() {
        return color != null ? color.hashCode() : 0;
    }
}
