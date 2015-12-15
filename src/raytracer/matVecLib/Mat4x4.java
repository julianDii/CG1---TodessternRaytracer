package raytracer.matVecLib;

/**
 * This class represents a 4x4 Matrix and operations to multiply it with other objects.
 * Created by Juliand on 15.12.15.
 */
public class Mat4x4 {

    /**
     * The m11 component of the matrix.
     */
    public final double m11;
    /**
     * The m12 component of the matrix.
     */
    public final double m12;
    /**
     * The m13 component of the matrix.
     */
    public final double m13;
    /**
     * The m14 component of the matrix.
     */
    public final double m14;
    /**
     * The m21 component of the matrix.
     */
    public final double m21;
    /**
     * The m22 component of the matrix.
     */
    public final double m22;
    /**
     * The m23 component of the matrix.
     */
    public final double m23;
    /**
     * The m24 component of the matrix.
     */
    public final double m24;
    /**
     * The m31 component of the matrix.
     */
    public final double m31;
    /**
     * The m32 component of the matrix.
     */
    public final double m32;
    /**
     * The m33 component of the matrix.
     */
    public final double m33;
    /**
     * The m34 component of the matrix.
     */
    public final double m34;
    /**
     * The m41 component of the matrix.
     */
    public final double m41;
    /**
     * The m42 component of the matrix.
     */
    public final double m42;
    /**
     * The m43 component of the matrix.
     */
    public final double m43;
    /**
     * The m44 component of the matrix.
     */
    public final double m44;

    /**
     * This contructor creates a new 4x4 matrix with the given components.
     * @param m11
     * @param m12
     * @param m13
     * @param m14
     * @param m21
     * @param m22
     * @param m23
     * @param m24
     * @param m31
     * @param m32
     * @param m33
     * @param m34
     * @param m41
     * @param m42
     * @param m43
     * @param m44
     */
    public Mat4x4(final double m11,final double m12,final double m13,final double m14,
                  final double m21,final double m22,final double m23,final double m24,
                  final double m31,final double m32,final double m33,final double m34,
                  final double m41,final double m42,final double m43,final double m44){

        this.m11=m11;
        this.m12=m12;
        this.m13=m13;
        this.m14=m14;
        this.m21=m21;
        this.m22=m22;
        this.m23=m23;
        this.m24=m24;
        this.m31=m31;
        this.m32=m32;
        this.m33=m33;
        this.m34=m34;
        this.m41=m41;
        this.m42=m42;
        this.m43=m43;
        this.m44=m44;

    }

    /**
     * Multiplies the given matrix with another one.
     * @param m
     * @return The product of the matrix multiplication.
     */

    public Mat4x4 mul(final Mat4x4 m){

        if(m==null)throw new IllegalArgumentException("m have to be not null");

        return new Mat4x4(

                m11*m.m11+m12*m.m21+m13*m.m31+m14*m.m41,
                m11*m.m12+m12*m.m22+m13*m.m32+m14*m.m42,
                m11*m.m13+m12*m.m23+m13*m.m33+m14*m.m43,
                m11*m.m14+m12*m.m24+m13*m.m34+m14*m.m44,
                m21*m.m11+m22*m.m21+m23*m.m31+m24*m.m41,
                m21*m.m12+m22*m.m22+m23*m.m32+m24*m.m42,
                m21*m.m13+m22*m.m23+m23*m.m33+m24*m.m43,
                m21*m.m14+m22*m.m24+m23*m.m34+m24*m.m44,
                m31*m.m11+m32*m.m21+m33*m.m31+m34*m.m41,
                m31*m.m12+m32*m.m22+m33*m.m32+m34*m.m42,
                m31*m.m13+m32*m.m23+m33*m.m33+m34*m.m43,
                m31*m.m14+m32*m.m24+m33*m.m34+m34*m.m44,
                m41*m.m11+m42*m.m21+m43*m.m31+m44*m.m41,
                m41*m.m12+m42*m.m22+m43*m.m32+m44*m.m42,
                m41*m.m13+m42*m.m23+m43*m.m33+m44*m.m43,
                m41*m.m14+m42*m.m24+m43*m.m34+m44*m.m44
        );
    }

    /**
     * Multiplies the given matrix with a vector.
     * @param v
     * @return The matrix vector product.
     */
    public Vector3 mul(final Vector3 v){

        if(v==null)throw new IllegalArgumentException("m have to be not null");
        return new Vector3(

                m11 * v.x + m12 * v.y + m13 * v.z,
                m21 * v.x + m22 * v.y + m23 * v.z,
                m31 * v.x + m32 * v.y + m33 * v.z
        );
    }

    /**
     * Multiplies the given matrix with a point.
     * @param p
     * @return The matrix point product.
     */

    public Point3 mul(final Point3 p){

        if(p==null)throw new IllegalArgumentException("m have to be not null");
        return new Point3(

                m11 * p.x + m12 * p.y + m13 * p.z + m14,
                m21 * p.x + m22 * p.y + m23 * p.z + m24,
                m31 * p.x + m32 * p.y + m33 * p.z + m34
        );
    }

    /**
     * Transposes the matrix.
     * @return The transposed matrix.
     */
    public Mat4x4 transposed(){
        return new Mat4x4(

                m11, m21, m31, m41,
                m12, m22, m32, m42,
                m13, m23, m33, m43,
                m14, m24, m34, m44
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mat4x4 mat4x4 = (Mat4x4) o;

        if (Double.compare(mat4x4.m11, m11) != 0) return false;
        if (Double.compare(mat4x4.m12, m12) != 0) return false;
        if (Double.compare(mat4x4.m13, m13) != 0) return false;
        if (Double.compare(mat4x4.m14, m14) != 0) return false;
        if (Double.compare(mat4x4.m21, m21) != 0) return false;
        if (Double.compare(mat4x4.m22, m22) != 0) return false;
        if (Double.compare(mat4x4.m23, m23) != 0) return false;
        if (Double.compare(mat4x4.m24, m24) != 0) return false;
        if (Double.compare(mat4x4.m31, m31) != 0) return false;
        if (Double.compare(mat4x4.m32, m32) != 0) return false;
        if (Double.compare(mat4x4.m33, m33) != 0) return false;
        if (Double.compare(mat4x4.m34, m34) != 0) return false;
        if (Double.compare(mat4x4.m41, m41) != 0) return false;
        if (Double.compare(mat4x4.m42, m42) != 0) return false;
        if (Double.compare(mat4x4.m43, m43) != 0) return false;
        return Double.compare(mat4x4.m44, m44) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(m11);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m12);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m13);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m14);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m21);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m22);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m23);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m24);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m31);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m32);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m33);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m34);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m41);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m42);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m43);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m44);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Mat4x4{" +
                "m11=" + m11 +
                ", m12=" + m12 +
                ", m13=" + m13 +
                ", m14=" + m14 +
                ", m21=" + m21 +
                ", m22=" + m22 +
                ", m23=" + m23 +
                ", m24=" + m24 +
                ", m31=" + m31 +
                ", m32=" + m32 +
                ", m33=" + m33 +
                ", m34=" + m34 +
                ", m41=" + m41 +
                ", m42=" + m42 +
                ", m43=" + m43 +
                ", m44=" + m44 +
                '}';
    }
}
