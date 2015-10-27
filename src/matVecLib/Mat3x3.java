package matVecLib;

/**
 * This class represents a 3x3 matrix.
 * Created by Juliand on 13.10.15.
 */
public class Mat3x3 {

    /**
     * the m11 component of the 3x3 Matrix.
     */

    public final double m11;

    /**
     * the m12 component of the 3x3 Matrix.
     */

    public final double m12;

    /**
     * the m13 component of the 3x3 Matrix.
     */

    public final double m13;

    /**
     * the m21 component of the 3x3 Matrix.
     */

    public final double m21;

    /**
     * the m22 component of the 3x3 Matrix.
     */

    public final double m22;

    /**
     * the m23 component of the 3x3 Matrix.
     */

    public final double m23;

    /**
     * the m31 component of the 3x3 Matrix.
     */

    public final double m31;

    /**
     * the m32 component of the 3x3 Matrix.
     */

    public final double m32;

    /**
     * the m33 component of the 3x3 Matrix.
     */

    public final double m33;

    /**
     * the component for the determinant.
     */

    public double determinant;

    /**
     * This Constructor creates a new Matrix with the given parameters and sets the value ot the determinant.
     *
     * @param m11
     * @param m12
     * @param m13
     * @param m21
     * @param m22
     * @param m23
     * @param m31
     * @param m32
     * @param m33
     */

    public Mat3x3(final double m11,final double m12,final double m13
            ,final double m21,final double m22,final double m23,
                  final double m31,final double m32, final double m33){

        this.m11=m11;
        this.m12=m12;
        this.m13=m13;
        this.m21=m21;
        this.m22=m22;
        this.m23=m23;
        this.m31=m31;
        this.m32=m32;
        this.m33=m33;
        this.determinant=m11 * m22 * m33
                + m21 * m32 * m13
                + m31 * m12 * m23
                - m13 * m22 * m31
                - m23 * m32 * m11
                - m33 * m12 * m21;


    }

    /**
     * This Method multiplies the Matrix with another Matrix (MMP).
     * @param m
     * @return The Result of the multiplication (MMP).
     */

    public Mat3x3 mul(Mat3x3 m){

        if(m==null)throw new IllegalArgumentException("m have to be not null");

        return new Mat3x3(

                m11 * m.m11 + m21 * m.m12 + m31 * m.m13,
                m12 * m.m11 + m22 * m.m12 + m32 * m.m13,
                m13 * m.m11 + m23 * m.m12 + m33 * m.m13,

                m11 * m.m21 + m21 * m.m22 + m31 * m.m23,
                m12 * m.m21 + m22 * m.m22 + m32 * m.m23,
                m13 * m.m21 + m23 * m.m22 + m33 * m.m23,

                m11 * m.m31 + m21 * m.m32 + m31 * m.m33,
                m12 * m.m31 + m22 * m.m32 + m32 * m.m33,
                m13 * m.m31 + m23 * m.m32 + m33 * m.m33
        );
    }

    /**
     * This Method multiplies the Matrix with a Vevtor (MVP)
     * @param v
     * @return The Result of the multiplication (MVP)
     */

    public Vector3 mul(Vector3 v){

        if (v == null) throw new IllegalArgumentException("v must be not null." );

        return new Vector3(


                m11 * v.x + m21 * v.y + m31 * v.z,
                m12 * v.x + m22 * v.y + m32 * v.z,
                m13 * v.x + m23 * v.y + m33 * v.z


        );
    }

    /**
     * This Method multiplies the Matrix with a  Point (MPP)
     * @param p
     * @return The Result of the multiplication (MPP)
     */

    public Point3 mul(Point3 p) {

        if (p == null) throw new IllegalArgumentException("p must be not null.");

        return new Point3(

                m11 * p.x + m21 * p.y + m31 * p.z,
                m12 * p.x + m22 * p.y + m32 * p.z,
                m13 * p.x + m23 * p.y + m33 * p.z


        );
    }

    /**
     * ThisMethod changes the first column of the 3*3Matrix.
     * @param v
     * @return the changed 3*3 Matrix.
     */

    public Mat3x3 changeCol1(Vector3 v){

        if (v == null) throw new IllegalArgumentException("v must be not null." );

        return new Mat3x3(

                v.x, m21, m31,
                v.y, m22, m32,
                v.z, m23, m33
        );
    }

    /**
     * ThisMethod changes the second column of the 3*3Matrix.
     * @param v
     * @return the changed 3*3 Matrix.
     */


    public Mat3x3 changeCol2(Vector3 v){

        if (v == null) throw new IllegalArgumentException("v must be not null." );

        return new Mat3x3(
                m11, v.x, m31,
                m12, v.y, m32,
                m13, v.z, m33
        );
    }

    /**
     * ThisMethod changes the third column of the 3*3Matrix.
     * @param v
     * @return the changed 3*3 Matrix.
     */


    public Mat3x3 changeCol3(Vector3 v){

        if (v == null) throw new IllegalArgumentException("v must be not null." );

        return new Mat3x3(
                m11, m21, v.x,
                m12, m22, v.y,
                m13, m33, v.z
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mat3x3 mat3x3 = (Mat3x3) o;

        if (Double.compare(mat3x3.m11, m11) != 0) return false;
        if (Double.compare(mat3x3.m12, m12) != 0) return false;
        if (Double.compare(mat3x3.m13, m13) != 0) return false;
        if (Double.compare(mat3x3.m21, m21) != 0) return false;
        if (Double.compare(mat3x3.m22, m22) != 0) return false;
        if (Double.compare(mat3x3.m23, m23) != 0) return false;
        if (Double.compare(mat3x3.m31, m31) != 0) return false;
        if (Double.compare(mat3x3.m32, m32) != 0) return false;
        if (Double.compare(mat3x3.m33, m33) != 0) return false;
        return Double.compare(mat3x3.determinant, determinant) == 0;

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
        temp = Double.doubleToLongBits(m21);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m22);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m23);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m31);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m32);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m33);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(determinant);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Mat3x3{" +
                "m11=" + m11 +
                ", m12=" + m12 +
                ", m13=" + m13 +
                ", m21=" + m21 +
                ", m22=" + m22 +
                ", m23=" + m23 +
                ", m31=" + m31 +
                ", m32=" + m32 +
                ", m33=" + m33 +
                ", determinant=" + determinant +
                '}';
    }
}
