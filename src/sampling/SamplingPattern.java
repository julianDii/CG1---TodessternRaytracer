package sampling;

import raytracer.matVecLib.Point2;

import java.util.*;

/**
 * Created by Juliand on 22.12.15.
 */
public class SamplingPattern{


    public ArrayList<Point2>pointList=new ArrayList<>();
    public Set<Point2>allPoints;
    int points;


    public SamplingPattern(int points){
        if(points<0)throw new IllegalArgumentException("points have to be bigger then over 0");
        this.points=points;

    }
    public Set<Point2> regularPattern(int x, int y){

        double xStep;
        double yStep;
        double xStart;
        double yStart;



        if(x > 1){
           xStep= 1.0/(x-1.0);
            System.out.println("xstep > 1 : "+ xStep);
        }else
            xStep=0.0;
        System.out.println("xstep < 1 : "+ xStep);

        if(y > 1){
            yStep= 1.0/(y-1.0);

        }else
            yStep=0.0;

        if(x > 1){
            xStart=-0.5;
        }else
            xStart=0.0;

        if(y > 1){
            yStart=-0.5;
        }else
            yStart=0.0;

        for (int i = 0; i < x ; i++) {
            for (int j = 0; j < y ; j++) {

                pointList.add(new Point2(xStart + i * xStep, yStart + j * yStep));

            }
        }

        allPoints = new HashSet<>(pointList);

        return allPoints;

    }


    public Set<Point2> genSamples() {

        int n = (int) Math.sqrt(points);


        for (int p = 0; p < n; p++) {

            for (int q = 0; q < n; q++) {


                    pointList.add(new Point2((q + 0.5f) / n, (p + 0.5f) / n));

            }

        }
        allPoints = new HashSet<>(pointList);

        return allPoints;
    }
    public Set<Point2> constant() {
        int n = (int) Math.sqrt(points);

            for (int p = 0; p < n; p++) {

                pointList.add(new Point2(0.5f, 0.5f));

            }
        allPoints = new HashSet<>(pointList);
        return allPoints;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SamplingPattern that = (SamplingPattern) o;

        if (points != that.points) return false;
        if (!pointList.equals(that.pointList)) return false;
        return allPoints.equals(that.allPoints);

    }

    @Override
    public int hashCode() {
        int result = pointList.hashCode();
        result = 31 * result + allPoints.hashCode();
        result = 31 * result + points;
        return result;
    }

    @Override
    public String toString() {
        return "SamplingPattern{" +
                "pointList=" + pointList +
                ", allPoints=" + allPoints +
                ", points=" + points +
                '}';
    }
}
