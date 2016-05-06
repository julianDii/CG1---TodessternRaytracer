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

/**
 * This class represents a TextureCoor2D. Is's purpose is to realize mapping of 3D ray-object hit
 * points to 2D normalized texture coordinates.
 * Created by Juliand on 03.01.16.
 */
public class TextureCoord2D {

    /**
     * The u coordinate of the TextureCoord2D object.
     */
    public final double u;

    /**
     * The v coordinate of the TextureCoord2D object.
     */
    public final double v;

    /**
     * This constructor creates a new TextureCoord2D object.
     * @param u The u value of the TextureCoord2d object.
     * @param v The v value of the TextureCoord2d object.
     */
    public TextureCoord2D (final double u,final double v) {

        this.u = u;
        this.v = v;

    }

    /**
     * This method adds two TextureCoord2D objects.
     * @param t The given TextureCoord2D.
     * @return a new TextureCoord2D from the two added objects.
     */
    public  TextureCoord2D add (final TextureCoord2D t) {
        return new TextureCoord2D( u + t.u, v + t.v);
    }
    /**
     * This method multiplies a TextureCoord2D with a given double value.
     * @param n The given double value to multiply with.
     * @return a new TextureCoord2D.
     */


    public  TextureCoord2D mul (final double n) {
        return new TextureCoord2D (u * n, v * n);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextureCoord2D that = (TextureCoord2D) o;

        if (Double.compare(that.u, u) != 0) return false;
        return Double.compare(that.v, v) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(u);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(v);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "TextureCoord2D{" +
                "u=" + u +
                ", v=" + v +
                '}';
    }
}
