package sampling;

import matVecLib.Point2;

import java.util.*;

/**
 * This class provides different sampling algorithms.
 *TODO make all methods void...delete useless returns.
 * Created by Juliand on 22.12.15.
 */
public class SamplingPattern{


    /**
     * The list of sampling points.
     */
    public ArrayList<Point2>pointList = new ArrayList<>();

    /**
     * The points of the sampling pattern.
     */
    public Set<Point2>allPoints;

    /**
     * The amount of sampling points.
     */
    int points;

    /**
     * A random object to create random numbers inside the algorithms.
     */
    final Random random = new Random();

    /**
     * This constructor takes the number of sampling points as parameter and checks if the number
     * of sampling points is smaller then 1
     * @param points the sample points.
     */

    public SamplingPattern (final int points) {

        if(points < 0)throw new IllegalArgumentException("points have to be bigger then over 0");

        this.points = points;

    }


    /**
     * This is a regular SamplingPattern.
     * @return
     */

    public Set<Point2> regularPattern2() {

        double xStep;
        double xStart;

        if (points > 1) {

            xStep = 1.0 / (points - 1.0);

        } else

            xStep = 0.0;

        if (points > 1) {

            xStart = -0.5;

        } else

            xStart = 0.0;

        for (int i = 0; i < points ; i++) {

            for (int j = 0; j < points ; j++) {

                pointList.add(new Point2(xStart + i * xStep, xStart + j * xStep));

            }
        }

        allPoints = new HashSet<>(pointList);

        return allPoints;

    }

    /**
     *
     * @return
     */
    public Set<Point2> regularSampling () {

        int n = (int) Math.sqrt(points);


        for (int p = 0; p < n; p++) {

            for (int q = 0; q < n; q++) {

                    pointList.add(new Point2((q + 0.5f) / n, (p + 0.5f) / n));
            }

        }
        allPoints = new HashSet<>(pointList);

        return allPoints;
    }


    public Set<Point2> constantSampling () {

        int n = (int) Math.sqrt(points);

            for (int p = 0; p < n; p++) {

                pointList.add(new Point2(0.5f, 0.5f));

            }
        allPoints = new HashSet<>(pointList);

        return allPoints;
    }


    public Set<Point2>randomSampling () {

            for (int i = 0; i < points; i++) {

                pointList.add(new Point2(random.nextFloat(), random.nextFloat()));
            }
        allPoints = new HashSet<>(pointList);

        return allPoints;
    }

    public Set<Point2> jitteredSampling () {

        int n = (int) Math.sqrt(points);

            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++) {

                    pointList.add(new Point2((k + random.nextFloat()) / n, (j + random.nextFloat()) / n));
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
