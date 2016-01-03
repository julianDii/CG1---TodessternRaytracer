package material;

import raytracer.Color;
import raytracer.Ray;
import raytracer.World;
import raytracer.geometrie.Hit;
import raytracer.matVecLib.Normal3;
import raytracer.matVecLib.Vector3;

/**
 * Created by Juliand on 02.01.16.
 */
public class TransparentMaterial extends Material {

    private double refIndex;

    public TransparentMaterial(double refIndex){

        this.refIndex=refIndex;
    }

    @Override
    public Color colorFor(final Hit hit,final World world,final Tracer tracer) {

        Normal3 nor = hit.nor;
        Vector3 e = hit.ray.d.mul(-1).reflectedOn(nor);

        double epsi = 0.000001;
        double et=0;

        if (e.dot(nor) < 0) {
            et = refIndex/world.refractionIndex;
            nor = nor.mul(-1);
        }else{
            et = world.refractionIndex / refIndex;
        }

        final double cosT = nor.dot(e);
        final double h = 1 - (et * et) * (1 - cosT * cosT);

        if (h < 0){
            return tracer.reflectedColor(new Ray(hit.ray.at(hit.t - epsi), e));
        } else {

            final double cosTat = Math.sqrt(h);
            final Vector3 t = hit.ray.d.mul(et).sub(nor.mul(cosTat - et * cosT));

            final double r0 = Math.pow((world.refractionIndex - refIndex)/(world.refractionIndex + refIndex), 2);
            final double rr = r0 + (1 - r0) * Math.pow(1 - cosT, 5);
            final double tt = 1 - rr;

            return tracer.reflectedColor(new Ray(hit.ray.at(hit.t - epsi),
                    e)).mul(rr).add(tracer.reflectedColor(new Ray(hit.ray.at(hit.t + epsi), t)).mul(tt));
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
