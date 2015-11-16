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
    	return new Color(this.r+c.r, this.g+c.g, this.b + c.b);
    }
    
	/**
	 * subtract a Color from this Color 
	 * @param subtract c from this Color
	 * @return Result as Color
	 */
    public Color sub(final Color c){
    	return new Color(this.r-c.r, this.g-c.g, this.b-c.b);
    }
    
	/**
	 * Multiplies a Color with this Color 
	 * @param Multiplies c  with this Color
	 * @return Result as Color
	 */
    public Color mul(final Color c){
    	return new Color(this.r*c.r, this.g*c.g, this.b*c.b);
    }
    
	/**
	 * Multiplies a Color with v
	 * @param multiplies v with this Color
	 * @return Result as Color
	 */
    public Color mul(final double v){
    	return new Color(this.r*v, this.g*v, this.b*v);	
    }
	
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
		if (Double.doubleToLongBits(r) != Double.doubleToLongBits(other.r))
			return false;
		return true;
	}
}
