package raytracer.matVecLib;

/**
 * The Class Normal3 is a normale on a ground.
 * Created by Juliand on 13.10.15.
 */
public class Normal3 {
    /**
     * represents the x value of the Normal
     */
    public final double x;
    /**
     * represents the y value of the Normal
     */
    public final double y;
    /**
     * represents the z value of the Normal
     */
    public final double z;

    /**
     * This constructor creates a Normal3 with the x,y,z values
     * @param x
     * @param y
     * @param z
     */
    public Normal3(final double x, final double y, final double z){

        this.x=x;
        this.y=y;
        this.z=z;

    }

    /**
     * This Method  multiplies every component of the normal with an parameter
     * @param m
     * @return This Method returns a new Normal3 Object
     */

    public Normal3 mul(final double m){

        return new Normal3(
                x * m,y * m,z * m
        );
    }

    /**
     * This Method adds the given x,y,z components to the components of the parameters of the Object
     * @param a
     * @return
     */

    public Normal3 add(Normal3 a){

        if(a==null)throw new IllegalArgumentException("'a'have to be not null");

        return new Normal3(x+a.x,y+a.y,z+a.z);

    }

    public double dot(Vector3 v){

        if (v == null) throw new IllegalArgumentException("'v' have to be not null." );

        return x * v.x + y * v.y + z + v.z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Normal3 normal3 = (Normal3) o;

        if (Double.compare(normal3.x, x) != 0) return false;
        if (Double.compare(normal3.y, y) != 0) return false;
        return Double.compare(normal3.z, z) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;

    }

    @Override
    public String toString() {
        return "Normal3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';

    }
}
