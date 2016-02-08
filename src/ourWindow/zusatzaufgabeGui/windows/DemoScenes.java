package ourWindow.zusatzaufgabeGui.windows;

import camera.Camera;
import camera.OrthographicCamera;
import camera.PerspectiveCamera;
import geometries.*;
import lights.DirectionalLight;
import lights.PointLight;
import lights.SpotLight;
import matVecLib.Normal3;
import matVecLib.Point3;
import matVecLib.Vector3;
import materials.*;
import raytracer.BoundingBox;
import raytracer.Color;
import raytracer.Transform;
import raytracer.World;
import sampling.SamplingPattern;
import texture.ImageTexture;
import texture.InterpolatedImageTexture;
import texture.TextureCoord2D;

import java.util.ArrayList;

/**
 * This class provides several demo scenes to show the power of the dark side. TODESSTERN RAYTRACER will destroy everything.
 * Created by Juliand on 09.01.16.
 */
public class DemoScenes {


    public final PointLight pointLight101 = new PointLight(new raytracer.Color(1,1,1), new Point3(8,8,8),true);

    public final PerspectiveCamera camTransformation = new PerspectiveCamera(new Point3(0,0,8),new Vector3(0,0,-1),new Vector3(0,1,0),new SamplingPattern(4),Math.PI/4);

    public final PerspectiveCamera camUniv = new PerspectiveCamera(new Point3(8,8,8),new Vector3(-1,-1,-1),new Vector3(0,1,0),new SamplingPattern(4),Math.PI/4);

    /**
     * Sphere component for transformation.
     */
    public final Sphere transSphere = new Sphere(new ReflectiveMaterial(new raytracer.Color(1,0,0),
            new raytracer.Color(1,1,1),64,new raytracer.Color(0.5,0.5,0.5)));
    /**
     * Box component for transformation.
     */
    public final AxisAlignedBox transBox = new AxisAlignedBox(new ReflectiveMaterial(new raytracer.Color(1,0,0),
            new raytracer.Color(1,1,1),64,new raytracer.Color(0.5,0.5,0.5)));

    public final static Node abb1 = new Node(new Transform().rotX(0.5).rotZ(-0.6).scale(2, 0.3, 2),new ArrayList<Geometry>());

    public final static Node abb2 = new Node(new Transform().rotX(0.6).rotZ(-0.3).rotY(0.6).scale(6, 0.4, 2).translate(new Point3(0.4,0,0)),new ArrayList<Geometry>());

    public final Sphere sRed = new Sphere(new ReflectiveMaterial(new raytracer.Color(1,0,0),
            new raytracer.Color(1,1,1),64,new raytracer.Color(0.5,0.5,0.5)));

    public final Sphere sGreen = new Sphere(new ReflectiveMaterial(new raytracer.Color(0,1,0),
            new raytracer.Color(1,1,1),64,new raytracer.Color(0.5,0.5,0.5)));

    public final Sphere sBlue = new Sphere(new ReflectiveMaterial(new raytracer.Color(0,0,1),
            new raytracer.Color(1,1,1),64,new raytracer.Color(0.5,0.5,0.5)));

    public final Plane blackPlane = new Plane(new ReflectiveMaterial(new raytracer.Color(0.1,0.1,0.1),
            new raytracer.Color(0,0,0),64,new raytracer.Color(0.5,0.5,0.5)));

    public final static Node abb3Plane = new Node(new Transform().rotX(0).translate(new Point3(0, 0, 0)),new ArrayList<Geometry>());
    public final static Node abb3sphere1 = new Node(new Transform().translate(new Point3(-3,1,0)),new ArrayList<Geometry>());
    public final static Node abb3sphere2 = new Node(new Transform().translate(new Point3(0,1,0)),new ArrayList<Geometry>());
    public final static Node abb3sphere3 = new Node(new Transform().translate(new Point3(3,1,0)),new ArrayList<Geometry>());

    public final PointLight pointLight100 = new PointLight(new raytracer.Color(1,1,1), new Point3(8,8,0),true);
    public final Plane plane10 = new Plane(new Point3(0,0,0), new Normal3(0,1,0), new LambertMaterial(new raytracer.Color(0.8,0.8,0.8)));
    public final AxisAlignedBox box10 = new AxisAlignedBox(new Point3(-0.5,0,-0.5), new Point3(0.5,1,0.5), new LambertMaterial(new raytracer.Color(1,0,0)));


    //ABBLIDUNG 5 Beleuchtung 2

    public final SpotLight spotLight = new SpotLight(new raytracer.Color(0.3,0.3,0.3),
            new Point3(0,5,-10),true,new Vector3(0,-1,0),Math.PI/8);

    public final PointLight pointLight = new PointLight(new raytracer.Color(0.3,0.3,0.3), new Point3(5,5,-10),true);

    public final DirectionalLight directionalLight = new DirectionalLight(new raytracer.Color(0.3,0.3,0.3),new Vector3(1,-1,0).normalized(),true);

    public final PerspectiveCamera camRef = new PerspectiveCamera(new Point3(8,8,8),
            new Vector3(-1,-1,-1),new Vector3(0,1,0),new SamplingPattern(1),Math.PI/4);



    public final Plane plane = new Plane(new ReflectiveMaterial(new raytracer.Color(1,1,1),
            new raytracer.Color(1,1,1),10,new raytracer.Color(1,1,1)));
    public final Node planeNode = new Node(new Transform().rotY(1),new ArrayList<>());

    public final Sphere s1 = new Sphere(new ReflectiveMaterial(new raytracer.Color(1,0,0),
            new raytracer.Color(1,1,1),10,new raytracer.Color(1,0.5,0.5)));

    public final Node n1 = new Node(new Transform().translate(new Point3(0,1,0)).scale(0.5,0.5,0.5),new ArrayList<>());

    public final Sphere s2 = new Sphere(new ReflectiveMaterial(new raytracer.Color(0,1,0),
            new raytracer.Color(1,1,1),10,new raytracer.Color(1,0.5,0.5)));

    public final Node n2 = new Node(new Transform().translate(new Point3(-1.5, 1, 0)).scale(0.5,0.5,0.5),new ArrayList<>());

    public final Sphere s3 = new Sphere(new ReflectiveMaterial(new raytracer.Color(0,0,1),
            new raytracer.Color(1,1,1),10,new raytracer.Color(1,0.5,0.5)));

    public final Node n3 = new Node(new Transform().translate(new Point3(1.5, 1, 0)).scale(0.5,0.5,0.5),new ArrayList<>());

    public final Sphere s4 = new Sphere(new ReflectiveMaterial(new raytracer.Color(0,1,1),
            new raytracer.Color(1,1,1),10,new raytracer.Color(1,0.5,0.5)));

    public final Node n4 = new Node(new Transform().translate(new Point3(0, 1, -1.5)).scale(0.5,0.5,0.5),new ArrayList<>());

    public final Sphere s5 = new Sphere(new ReflectiveMaterial(new raytracer.Color(1,0,1),
            new raytracer.Color(1,1,1),10,new raytracer.Color(1,0.5,0.5)));

    public final Node n5 = new Node(new Transform().translate(new Point3(-1.5, 1, -1.5)).scale(0.5,0.5,0.5),new ArrayList<>());

    public final Sphere s6 = new Sphere(new ReflectiveMaterial(new raytracer.Color(1,1,0),
            new raytracer.Color(1,1,1),10,new raytracer.Color(1,0.5,0.5)));

    public final Node n6 = new Node(new Transform().translate(new Point3(1.5,1,-1.5)).scale(0.5, 0.5, 0.5),new ArrayList<>());


    public final Sphere s1T = new Sphere(new TransparentMaterial(1.33));

    public final Node ns1T = new Node(new Transform().translate(new Point3(0,2,1.5)).scale(0.5,0.5,0.5),new ArrayList<>());

    public final Sphere s2T = new Sphere(new TransparentMaterial(1.33));

    public final Node ns2T = new Node(new Transform().translate(new Point3(-1.5,2,1.5)).scale(0.5, 0.5, 0.5),new ArrayList<>());

    public final Sphere s3T = new Sphere(new TransparentMaterial(1.33));

    public final Node ns3T = new Node(new Transform().translate(new Point3(1.5,2,1.5)).scale(0.5,0.5,0.5),new ArrayList<>());

    public final AxisAlignedBox boxT = new AxisAlignedBox(new Point3(-0.5,0,3),new Point3(0.5,1,4),new TransparentMaterial(1.33));

    public final Node nboxT = new Node(new Transform(),new ArrayList<>());

    public final Triangle tri = new Triangle(new Point3(0.7,0.5,3),new Point3(1.3,0.5,3),new Point3(0.7,0.5,4),new Normal3(0,1,0),new Normal3(0,1,0),new Normal3(0,1,0),
            new PhongMaterial(new raytracer.Color(0,1,0),
                    new raytracer.Color(0,1,0),20),new TextureCoord2D(0, 1), new TextureCoord2D(1, 0), new TextureCoord2D(0, 0));

    public final Node trino = new Node(new Transform(),new ArrayList<>());


    World world;

    public DemoScenes () {



    }
    public void verkettete_Rotationen(){

        world = new World(new Color(0,0,0),new Color(0.1,0.1,0.1),1);
        TodessternGUI.welt = world;
        PerspectiveCamera per = new PerspectiveCamera(new Point3(0,0,8), new Vector3(0,0,-1),new Vector3(0,1,0),new SamplingPattern(1),Math.PI/4);
        TodessternGUI.cam=per;
        PointLight point = new PointLight(new raytracer.Color(1,1,1),new Point3(8,8,8), true);


        ShapeFromFile teddy = new ShapeFromFile("teddy.obj", new ReflectiveMaterial(new raytracer.Color(1,1,0),
                new raytracer.Color(1,1,1),10,new raytracer.Color(1,0.5,0.5)));
        Node teddyNode = new Node(new Transform().rotZ(Math.PI/2).rotY(Math.PI/2).rotX(0),new ArrayList<>());

        teddyNode.g.add(teddy.OBJLoader());
        world.lightList.add(point);

        world.list.add(teddyNode);

    }

    public void bestimme_Kameravektoren(){

        world = new World(new Color(0,0,0),new Color(0.1,0.1,0.1),1);
        TodessternGUI.welt = world;
        //PerspectiveCamera per = new PerspectiveCamera(new Point3(0,6,6), new Vector3(0,-1,-1),new Vector3(0,1,0),new SamplingPattern(1),Math.PI/4);
        PerspectiveCamera per = new PerspectiveCamera(new Point3(-6,6,0), new Vector3(1,-1,0),new Vector3(0,1,0),new SamplingPattern(1),Math.PI/4);
        TodessternGUI.cam=per;
        PointLight point = new PointLight(new raytracer.Color(1,1,1),new Point3(4,4,4), true);

        Plane p = new Plane(new Point3(0,0,0),new Normal3(0,1,0),new PhongMaterial(new raytracer.Color(1,1,1),
                new raytracer.Color(0,1,0),20));

        Sphere s = new Sphere(new Point3(0,1,0),1, new PhongMaterial(new raytracer.Color(0,1,0),
                new raytracer.Color(0,1,0),20));

        AxisAlignedBox b = new AxisAlignedBox(new Point3(-0.5,-0.5,-0.5), new Point3(0.5,0.5,0.5),new PhongMaterial(new raytracer.Color(0,0,1),
                new raytracer.Color(0,1,0),20));


        Node boxnode = new Node(new Transform().translate(new Point3(2,1,0)),new ArrayList<>());
        boxnode.g.add(b);
        world.list.add(p);
        world.list.add(s);
        world.list.add(boxnode);



        world.lightList.add(point);



    }




    /**
     * This method builds the scene abbildung1_transformationen from the task illumination.
     */
    public void abbildung1_transformationen () {

        world = new World(new Color(0,0,0),new Color(0.1,0.1,0.1),1);
        TodessternGUI.cam = camTransformation;
        TodessternGUI.welt = world;

        abb1.g.add(transSphere);
        world.list.add(abb1);
        world.lightList.add(pointLight101);

    }

    public void abbildung2_transformationen () {

        world = new World(new Color(0,0,0),new Color(0.1,0.1,0.1),1);

        TodessternGUI.cam = camTransformation;
        TodessternGUI.welt = world;

        abb2.g.add(transBox);
        world.list.add(abb2);
        world.lightList.add(pointLight101);

    }


    public void abbildung3_beleuchtung_II () {


        world = new World(new Color(0,0,0),new Color(0.1,0.1,0.1),1);

        TodessternGUI.welt = world;
        TodessternGUI.cam = camUniv;


        world.lightList.add(pointLight101);

        abb3Plane.g.add(blackPlane);
        abb3sphere1.g.add(sRed);
        abb3sphere2.g.add(sGreen);
        abb3sphere3.g.add(sBlue);

        world.list.add(abb3Plane);
        world.list.add(abb3sphere1);
        world.list.add(abb3sphere2);
        world.list.add(abb3sphere3);
    }


    public void abbildung4_beleuchtung_II () {

        world = new World(new Color(0,0,0),new Color(0.1,0.1,0.1),1);

        TodessternGUI.welt = world;
        TodessternGUI.cam = camUniv;

        world.lightList.add(pointLight100);

		world.list.add(plane10);
        world.list.add(box10);

    }

    public void abbildung5_beleuchtung_II () {

        world = new World(new Color(0,0,0),new Color(0.1,0.1,0.1),1);

        TodessternGUI.welt =world;
        TodessternGUI.cam  = camRef;

        world.lightList.add(spotLight);
        world.lightList.add(pointLight);
        world.lightList.add(directionalLight);

        planeNode.g.add(plane);
        world.list.add(planeNode);

        n1.g.add(s1);
        n2.g.add(s2);
        n3.g.add(s3);
        n4.g.add(s4);
        n5.g.add(s5);
        n6.g.add(s6);
        world.list.add(n1);
        world.list.add(n2);
        world.list.add(n3);
        world.list.add(n4);
        world.list.add(n5);
        world.list.add(n6);
        ns1T.g.add(s1T);
        ns2T.g.add(s2T);
        ns3T.g.add(s3T);
        world.list.add(nboxT);
        trino.g.add(tri);
        world.list.add(trino);
        world.list.add(ns1T);
        world.list.add(ns2T);
        world.list.add(ns3T);
        nboxT.g.add(boxT);
    }

    public void additional_ImageTextureScene () {

        world = new World(new Color(0,0,0),new Color(0.1,0.1,0.1),1);


        final PointLight spaceLight = new PointLight(new Color(1,1,1),new Point3(70,300,500),false);



        final PerspectiveCamera hubble = new PerspectiveCamera(new Point3(0,0,350), new Vector3(0,0,-1), new Vector3(1,1,1),new SamplingPattern(4),Math.PI/4);

        Node p = new Node(new Transform().rotX(45).rotZ(5).translate(new Point3(0, 0, -800)),new ArrayList<>());
        final Plane spaceBackground = new Plane(new SingleColorMaterial(new ImageTexture(new Color(0,0,0),"space5.jpg")));

        Node p1 = new Node(new Transform().rotX(45).translate(new Point3(-50, 0, -800)),new ArrayList<>());
        //final Plane laser = new Plane(new ReflectiveMaterial());

        final Sphere earth = new Sphere(new SingleColorMaterial(new InterpolatedImageTexture(new Color(0,0,0),"earthmap1k.jpg")));
        final Sphere deathStar = new Sphere(new SingleColorMaterial(new InterpolatedImageTexture(new Color(0,0,0),"deathStar.jpg")));


        //final ShapeFromFile tie = new ShapeFromFile("TieFighter.obj",new SingleColorMaterial(new Color(0.8,0.8,0.8)));

        Node tieNode = new Node(new Transform().scale(5,5,5).translate(new Point3(40, 20, -70)),new ArrayList<>());
        Node sEarth = new Node(new Transform().rotY(10).scale(30, 30, 30).translate(new Point3(90, 0, -100)),new ArrayList<>());
        Node sDeathStar = new Node(new Transform().rotZ(5).scale(50, 50, 50).translate(new Point3(-40, 0,250)),new ArrayList<>());

        TodessternGUI.welt = world;
        TodessternGUI.cam  = hubble;
        world.lightList.add(spaceLight);


        //tieNode.g.add(tie.OBJLoader());

        world.list.add(tieNode);
        p.g.add(spaceBackground);
        world.list.add(p);

        sEarth.g.add(earth);
        world.list.add(sEarth);

        sDeathStar.g.add(deathStar);
        world.list.add(sDeathStar);


    }
    public void imageTextur_Earth () {

        PointLight point = new PointLight(new Color(1,1,1),new Point3(3,8,19),true);
        world = new World(new Color(0,0,0),new Color(0.1,0.1,0.1),1);
        TodessternGUI.welt = world;
        TodessternGUI.cam = camUniv;

        final Node no = new Node(new Transform().rotY(-0.8).rotZ(0).scale(4, 4, 4),new ArrayList<>());
        Sphere sphereTex = new Sphere(new SingleColorMaterial(new ImageTexture(new raytracer.Color(0,0,0),"earthmap1k.jpg")));

        no.g.add(sphereTex);
        world.list.add(no);
        world.lightList.add(point);


    }
    public void imageTextur_downSapled340 () {

        PointLight point = new PointLight(new Color(1,1,1),new Point3(3,8,19),true);
        world = new World(new Color(0,0,0),new Color(0.1,0.1,0.1),1);
        TodessternGUI.welt = world;
        TodessternGUI.cam = camUniv;

        final Node no = new Node(new Transform().rotY(-0.8).rotZ(0).scale(4, 4, 4),new ArrayList<>());
        Sphere sphereTex = new Sphere(new SingleColorMaterial(new ImageTexture(new raytracer.Color(0,0,0),"jupitermap320.jpg")));

        no.g.add(sphereTex);
        world.list.add(no);
        world.lightList.add(point);


    }
    public void imageTextur_downSapled340_interpolated() {



        PointLight point = new PointLight(new Color(1,1,1),new Point3(3,8,19),true);
        world = new World(new Color(0,0,0),new Color(0.1,0.1,0.1),1);
        TodessternGUI.welt = world;
        TodessternGUI.cam = camUniv;

        final Node no = new Node(new Transform().rotY(-0.8).rotZ(0).scale(4, 4, 4),new ArrayList<>());
        Sphere sphereTex = new Sphere(new SingleColorMaterial(new InterpolatedImageTexture(new raytracer.Color(0,0,0),"jupitermap320.jpg")));

        no.g.add(sphereTex);
        world.list.add(no);
        world.lightList.add(point);


    }
    public void saturn () {


        PointLight point = new PointLight(new Color(1,1,1),new Point3(0,14,14),true);
        //PointLight point1 = new PointLight(new Color(1,1,1),new Point3(-9,-4,7),true);
        //PointLight point2 = new PointLight(new Color(1,1,1),new Point3(5,10,-8),true);
        world = new World(new Color(0,0,0),new Color(0.25,0.25,0.25),1);
        TodessternGUI.welt = world;
        TodessternGUI.cam = camUniv;

        Torus torus1 = new Torus(new ReflectiveMaterial(new raytracer.Color(0.8,0.8,0.8),
                new raytracer.Color(1,1,1),64,new raytracer.Color(0.5,0.5,0.5)));

        Torus torus2 = new Torus(new TransparentMaterial(1.33));

        Torus torus3 = new Torus(new TransparentMaterial(1.33));

        Torus torus4 = new Torus(new TransparentMaterial(2.33));

        Node torusnode1 = new Node(new Transform().rotY(-0.4).rotX(0).rotZ(-0.2).scale(1, 0.1, 1.0), new ArrayList<>());

        Node torusnode2 = new Node(new Transform().rotY(150).rotX(135).rotZ(60).scale(1.0,0.1,1), new ArrayList<>());

       // Node torusnode4 = new Node(new Transform().rotY(190).rotX(100).rotZ(40).scale(1.5,0.1,1), new ArrayList<>());

        Node torusnode3 = new Node(new Transform().rotY(69).rotX(150).rotZ(200).scale(1.0, 0.1, 1), new ArrayList<>());





        Node sNode2 = new Node(new Transform().scale(3.2,3.2,3.2).translate(new Point3(-5,5,0)), new ArrayList<>());
        Sphere sph2 =  new Sphere(new SingleColorMaterial(new InterpolatedImageTexture(new Color(0,0,0),"venus4.jpg")));

        Node sNode = new Node(new Transform().scale(2.2,2.2,2.2), new ArrayList<>());
        Sphere sph =  new Sphere(new SingleColorMaterial(new InterpolatedImageTexture(new Color(0,0,0),"moonmap4k.jpg")));

        torusnode1.g.add(torus1);


        torusnode3.g.add(torus4);
        torusnode2.g.add(torus3);


       // torusnode4.g.add(torus2);
        sNode.g.add(sph);
        sNode2.g.add(sph2);

        world.list.add(torusnode1);
        world.list.add(sNode);
       // world.list.add(torusnode4);
        world.list.add(torusnode2);
        world.list.add(sNode2);
        world.list.add(torusnode3);
      //  world.lightList.add(point1);
        world.lightList.add(point);

    }


}
