package material;

import raytracer.Color;
import raytracer.Ray;
import raytracer.World;
import raytracer.geometrie.Hit;

/**
 * Created by Juliand on 02.12.15.
 */
public class Tracer {

    public int depth;
    private final World world;
    private final Ray ray;

    public Tracer(Ray ray, int depth,World world){

        this.world = world;
        this.ray = ray;

    }
    public Color reflectedColor(){
        if(depth<0){
            return new Color(0,0,1);
        }

        return null;
    }
}
