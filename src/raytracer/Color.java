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

/**
 * The Color-class represents a Color. It has three Double values (RGB) with
 * the a range from 0 ( = 0 ) to 1 ( = 255 ).
 * @author Robert Ullmann
 */
public class Color {
	/**
	 * Red (0 to 1)
	 */
	public double r;
	
	/**
	 * Green (0 to 1)
	 */
	public double g;
	
	/**
	 * Blue (0 to 1)
	 */
	public double b;

	
	/**
	 * The constructor creates a new Color object
	 * @param r value of red between 0 to 1
	 * @param g value of green between 0 to 1
	 * @param b value of blue between 0 to 1
	 */
    public Color(final double r,final double g,final double b){
    	
    	this.r=r;
        this.g=g;
        this.b=b;


    }
	
    /**
	 * add Color to this Color 
	 * @param add c to this Color
	 * @return Result as Color
	 */
    public Color add(final Color c){

		if (c == null) throw new IllegalArgumentException("c must be not null." );
		return new Color(this.r+c.r, this.g+c.g, this.b+c.b);
    }

    
	/**
	 * subtract a Color from this Color 
	 * @param subtract c from this Color
	 * @return Result as Color
	 */
    public Color sub(final Color c){

		if (c == null) throw new IllegalArgumentException("c must be not null." );
		return new Color(this.r-c.r, this.g-c.g, this.b-c.b);
    }
    
	/**
	 * Multiplies a Color with this Color 
	 * @param Multiplies c  with this Color
	 * @return Result as Color
	 */
    public Color mul(final Color c){

		if (c == null) throw new IllegalArgumentException("c must be not null." );
		return new Color(this.r*c.r, this.g*c.g, this.b*c.b);
    }
    
	/**
	 * Multiplies a Color with v
	 * @param multiplies v with this Color
	 * @return Result as Color
	 */
    public Color mul(final double v){return new Color(this.r*v, this.g*v, this.b*v);}
	
    @Override
	public String toString() {
		return "Color [r=" + r + ", g=" + g + ", b=" + b + "]";
	}
	
	/**
	 *
	 *
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(b);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(g);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(r);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	

	/**
	 *
	 *
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Color other = (Color) obj;
		if (Double.doubleToLongBits(b) != Double.doubleToLongBits(other.b))
			return false;
		if (Double.doubleToLongBits(g) != Double.doubleToLongBits(other.g))
			return false;
		return Double.doubleToLongBits(r) == Double.doubleToLongBits(other.r);
	}
}
