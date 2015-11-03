package raytracer.matVecLib;

/**
 * This Main class is for testing of the raytracer.matVecLib.
 * It includes the test cases from the task.
 * Created by Julian on 14.10.15.
 */

public class Main {


    public static void main(String[] args) {

        System.out.println("1.Aufgabe");
        Normal3 nor = new Normal3(1,2,3);
        System.out.println(nor.mul(0.5).toString());
        System.out.println("\n");

        System.out.println("2.Aufgabe");
        Normal3 nor1 = new Normal3(1,2,3);
        System.out.println(nor1.add(new Normal3(3,2,1)).toString());
        System.out.println("\n");


        System.out.println("3.Aufgabe");

        Normal3 nor2 = new Normal3(1,0,0);
        System.out.println("nor(1,0,0)*Vec(1,0,0) = "+nor2.dot(new Vector3(1,0,0)));
        System.out.println("nor(1,0,0)*Vec(0,1,0) = "+nor2.dot(new Vector3(0,1,0)));

        Vector3 vec = new Vector3(1,0,0);
        System.out.println("Vec(1,0,0)*nor(1,0,0) = "+vec.dot(new Normal3(1,0,0)));
        System.out.println("Vec(1,0,0)*nor(0,1,0) = "+vec.dot(new Normal3(0,1,0)));
        System.out.println("\n");

        System.out.println("4.Aufgabe");

        Point3 point = new Point3(1,1,1);
        System.out.println("point(1,1,1)- point(2,2,0)  = "+point.sub(new Point3(2,2,0)));
        System.out.println("point(1,1,1)-vector(4,3,2) = "+point.sub(new Vector3(4,3,2)));
        System.out.println("point(1,1,1)+vector(4,3,2) = "+point.add(new Vector3(4,3,2)));

        System.out.println("\n");


        System.out.println("5.Aufgabe");
        test5();

        System.out.println("\n");

        System.out.println("6.Aufgabe");
        Vector3 vec1 = new Vector3(-0.707,0.707,0);
        System.out.println(vec1.reflectedOn(new Normal3(0,1,0)));
        System.out.println("\n");

        Vector3 vec2 = new Vector3(0.707,0.707,0);
        System.out.println(vec1.reflectedOn(new Normal3(1,0,0)));
        System.out.println("\n");

        System.out.println("7.Aufagabe");
        Mat3x3 mat = new Mat3x3(1,0,0,0,1,0,0,0,1);
        System.out.println(mat.mul(new Point3(3,2,1)));
        System.out.println(mat.mul(new Vector3(3,2,1)));

        System.out.println("8.Aufgabe");
        Mat3x3 mat1 = new Mat3x3(1,4,7,2,5,8,3,6,9);
        System.out.println(mat1.mul(new Mat3x3(1,0,0,0,1,0,0,0,1)));

        Mat3x3 mat2 = new Mat3x3(1,4,7,2,5,8,3,6,9);
        System.out.println(mat1.mul(new Mat3x3(0,0,1,0,1,0,1,0,0)));


        System.out.println("\n");

        System.out.println("9.Aufgabe");
        Mat3x3 mat3 = new Mat3x3(1,4,7,2,5,8,3,6,9);
        System.out.println(mat3.changeCol1(new Vector3(8,8,8)));
        System.out.println(mat3.changeCol2(new Vector3(8,8,8)));
        System.out.println(mat3.changeCol3(new Vector3(8,8,8)));






        




    }

    /**
     * This Method tests the task 5.
     * @return true if equal
     */

    public static boolean test5(){

        double x;
        double y;

        Vector3 vec2 = new Vector3(1,1,1);

        x = vec2.magnitude;
        y = Math.sqrt(3);
        if(x==y){
            System.out.println("The result is true.");
            return true;

        }
        System.out.println("The result is false.");
        return false;
    }

}
