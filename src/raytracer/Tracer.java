/*
 * MIT License
 *
 * Copyright (c) 2016 Julian Dobrot
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package raytracer;

/**
 * This class represents a Tracer for recursive ray tracing.
 * Created by Juliand on 02.12.15.
 */
public class Tracer {

    /**
     * The depth component of the Tracer.
     */
    public int depth;

    /**
     * The world component of the Tracer.
     */
    public final World world;



    /**
     * This constructor builds a new Tracer.
     *
     * @param depth Tracer depth component.
     * @param world Tracer world component
     */
    public Tracer(final int depth, final  World world) {

        if (depth < 0) throw new IllegalArgumentException("depth has to be not smaller then 0");
        if (world == null) throw new IllegalArgumentException("World has to be not null");


        this.depth = depth;
        this.world = world;


    }

    /**
     * This method calculates the color of a reflection.
     *
     * @param r the given ray.
     * @return The reflection color.
     */

    public Color colorFor (final Ray r) {

        if (r == null)throw new IllegalArgumentException("The ray cannot be null!");
        if (depth <= 0) {

            return world.backgroundcolor;

        } else {

            double t2 = 0.0;

            Hit hit = world.hitt(r);

            if(hit != null) {

                t2=hit.t;

            }

            if (hit != null && t2 >= Constants.EPSILON) {

                return hit.geo.material.colorFor(hit, world, new Tracer(depth-1,world));

            } else {

                return world.backgroundcolor;

            }
        }
    }


}
