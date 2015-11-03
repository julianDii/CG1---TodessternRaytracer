package raytracer;

/**
 * Created by Juliand on 03.11.15.
 */
public abstract class Geometry {

    final Color color;

    public Geometry(final Color color){
        this.color=color;
    }
    public abstract Hit hit(final Ray r);
}
