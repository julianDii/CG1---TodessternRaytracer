package raytracer;

/**
 * Created by Juliand on 03.11.15.
 * Developer Robert Ullmann
 */
public class Color {

    final double r;
    final double g;
    final double b;

    public Color(final double r,final double g, final double b){
        this.r=r;
        this.g=g;
        this.b=b;
    }
    public Color add(final Color c){
        return null;//TODO
    }
    public Color sub(final Color c){
        return null;//TODO
    }
    public Color mul(final Color c){
        return null;//TODO
    }
    public Color mul(final double v){
        return null;//TODO
    }
	
    @Override
	public String toString() {
		return "Color [r=" + r + ", g=" + g + ", b=" + b + "]";
	}
	
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
