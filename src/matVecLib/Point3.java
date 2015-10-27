package matVecLib;

/**
 * The Class Point3 creates a new Point3
 * Created by Juliand on 13.10.15.
 */
public class Point3 {

    /**
     * The x Component of the point
     */
    public final double x;

    /**
     * The y Component of the point
     */

    public final double y;

    /**
     * The z Component of the point
     */

    public final double z;

    /**
     * This Constructor builds a Point3 Object with the given parameters.
     *
     * @param x
     * @param y
     * @param z
     */

    public Point3(final double x, final double y, final double z){

        this.x=x;
        this.y=y;
        this.z=z;

    }

    /**
     * This Method substracts the Point3 components from the constructor with the given Point3
     * @param p
     * @return a new Vector3
     */
    public Vector3 sub(Point3 p){

        if(p==null)throw new IllegalArgumentException("'p'have to be not null");

        return new Vector3(

                x - p.x,
                y - p.y,
                z - p.z
        );

    }

    /**
     * This Method takes the given Point3 multiplied by Vector3
     * @param v
     * @return new Point3
     */
    public Point3 sub(Vector3 v){

        if(v==null)throw new IllegalArgumentException("'v'have to be not null");

        return new Point3(

                x - v.x,
                y - v.y,
                z - v.z
        );
    }

    /**
     * this Method adds a Verctor3 to the given Point3
     * @param v
     * @return a new Point3
     */

    public Point3 add(Vector3 v){

        if(v==null)throw new IllegalArgumentException("'v'have to be not null");

        return new Point3(

                x + v.x,
                y + v.y,
                z + v.z
        );

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point3 point3 = (Point3) o;

        if (Double.compare(point3.x, x) != 0) return false;
        if (Double.compare(point3.y, y) != 0) return false;
        return Double.compare(point3.z, z) == 0;

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
        return "Point3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
