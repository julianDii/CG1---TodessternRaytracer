package material;

import licht.Light;
import raytracer.Color;
import raytracer.Ray;
import raytracer.World;
import raytracer.geometrie.Geometry;
import raytracer.geometrie.Hit;
import raytracer.matVecLib.Normal3;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

/**
 * This class represents a TransparentMaterial.
 * Created by Juliand on 02.01.16.
 */
public class TransparentMaterial extends Material {

    /**
     * The value of the refractive index.
     */
    private double refIndex;

    /**
     * This constructor creates a new TransparentMaterial.
     * @param refIndex
     */

    public TransparentMaterial(double refIndex){

        this.refIndex=refIndex;
    }

    /**
     * @param hit The hit object.
     * @param world The world object.
     * @param tracer The tracer object.
     * @return The Color for the transparent material.
     */


    @Override
    public Color colorFor(final Hit hit, final World world, final Tracer tracer) {

        Normal3 normal = hit.nor;

        Color baseColor = new Color(0,0,0);
        double epsi=0.00001;


                // rd = reflected vector d
                final Vector3 rd = hit.ray.d.mul(-1).normalized().reflectedOn(normal).normalized();

                // The relative index of refraction.
                double rIor;

                if (rd.dot(normal) < 0) {

                    rIor = refIndex / world.refractionIndex;
                    normal = normal.mul(-1);

                } else {

                    rIor = world.refractionIndex / refIndex;

                }

                // angle between the direction vector and the normal or the angle between normal and the reflected
                // vector rd. (normal x vector = double)
                final double alpha = normal.dot(rd);

                if ((1 - (Math.pow(rIor, 2.0) * (1 - Math.pow(alpha, 2.0)))) < 0) {
                    return tracer.colorFor(new Ray(hit.ray.at(hit.t - epsi), rd));
                } else {

                    // angle between normal and the refracted vector t.
                    final double beta = Math.sqrt(1 - (Math.pow(rIor, 2.0) * (1 - Math.pow(alpha, 2.0))));

                    final Vector3 t = hit.ray.d.mul(rIor).sub(normal.mul(beta - rIor * alpha));

                    final double r = Math.pow((world.refractionIndex - refIndex) / (world.refractionIndex + refIndex), 2);
                    final double rr = r + (1 - r) * Math.pow(1 - alpha, 5);
                    final double t1 = 1 - rr;


                    return tracer.colorFor(new Ray(hit.ray.at(hit.t - epsi), rd)).mul(rr)
                            .add(tracer.colorFor(new Ray(hit.ray.at(hit.t + epsi), t)).mul(t1));
                }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransparentMaterial that = (TransparentMaterial) o;

        return Double.compare(that.refIndex, refIndex) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(refIndex);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return "TransparentMaterial{" +
                "refIndex=" + refIndex +
                '}';
    }
}
