package ourWindow;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import licht.DirectionalLight;
import licht.PointLight;
import licht.SpotLight;
import material.*;
import javafx.stage.Stage;
import raytracer.Ray;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.camera.OrthographicCamera;
import raytracer.camera.PerspectiveCamera;
import raytracer.geometrie.*;
import raytracer.matVecLib.Mat4x4;
import raytracer.matVecLib.Normal3;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;
import sampling.SamplingPattern;

/**
 * OurGui Class opens a window thanks to the implemented JavaFX
 * application and gives the user the oportunity to 
 * create his own world containing objects such as world, camera and geometry.
 * The window size is editable by the user.
 * 
 * @author Charline Waldrich
 */
public class OurGui extends Application {

	/**
	 * For testing we initialize the needed object in our world. 
	 */
	public final static World welt = new World(new raytracer.Color(0,0,0),new raytracer.Color(0,0,0));


	//Schatten 1

	public final static Node abb4Plane = new Node(new Transform(),new ArrayList<Geometry>());
	public final static Node abb4Box = new Node(new Transform().scale(1,2,1),new ArrayList<Geometry>());

	//Schatten 2

	public final static Node abb3Plane = new Node(new Transform(),new ArrayList<Geometry>());
	public final static Node abb3sphere1 = new Node(new Transform().translate(new Point3(-3,1,0)),new ArrayList<Geometry>());
	public final static Node abb3sphere2 = new Node(new Transform().translate(new Point3(0,1,0)),new ArrayList<Geometry>());
	public final static Node abb3sphere3 = new Node(new Transform().translate(new Point3(3,1,0)),new ArrayList<Geometry>());


	//Transformation abb1-22
	public final static Node abb1 = new Node(new Transform().rotX(0.5).rotZ(-0.6).scale(2, 0.3, 2),new ArrayList<Geometry>());

	public final static Node abb2 = new Node(new Transform().rotX(0.6).rotZ(-0.3).rotY(0.6).scale(6, 0.4, 2).translate(new Point3(0.4,0,0)),new ArrayList<Geometry>());


	//TEST
	public final static Node test = new Node(new Transform().rotX(3).rotZ(4).scale(4, 3, 0.5),new ArrayList<Geometry>());
	public final static Node test2 = new Node(new Transform().rotX(5).rotZ(3).scale(2,1,3),new ArrayList<Geometry>());

//	public final Material material = new SingleColorMaterial(new raytracer.Color(0,0,1));
//
//	// Aufgabe Abb.5
//	public final Plane plane = new Plane(new Point3(0,-1,0), new Normal3(0,1,0), material);
//	public final Camera abb5Cam = new PerspectiveCamera(new Point3(0,0,0), new Vector3(0,0,-1), new Vector3(0,1,0), Math.PI/4);
//
//	// Aufgabe Abb.6
//	public final Sphere sphere0 = new Sphere(new Point3(0,0,-3),0.5, material);
//	public final Camera abb6Cam = new PerspectiveCamera(new Point3(0,0,0), new Vector3(0,0,-1), new Vector3(0,1,0), Math.PI/4);
//
//	// Aufgabe Abb.7
//	public final AxisAlignedBox box0 = new AxisAlignedBox(new Point3(-0.5,0,-0.5), new Point3(0.5,1,0.5), material);
//	public final Camera abb7Cam = new PerspectiveCamera(new Point3(3,3,3), new Vector3(-3,-3,-3), new Vector3(0,1,0), Math.PI/4);
//
//	// Aufgabe Abb.8
//	public final Triangle triangl0 = new Triangle(new Point3(-0.5,0.5,-3),new Point3(0.5,0.5,-3),new Point3(0.5,-0.5,-3), material);
//	public final Camera abb8Cam = new PerspectiveCamera(new Point3(0,0,0),new Vector3(0,0,-1), new Vector3(0,1,0),Math.PI/4);
//
//	// Aufgabe Abb.9
//	public final Sphere sphere1 = new Sphere(new Point3(-1,0,-3),0.5, material);
//	public final Sphere sphere2 = new Sphere(new Point3(1,0,-6),0.5, material);
//	public final Camera abb9Cam = new PerspectiveCamera(new Point3(0,0,0),new Vector3(0,0,-1), new Vector3(0,1,0),Math.PI/4);
//
//	// Aufgabe Abb.10
//	public final Sphere sphere3 = new Sphere(new Point3(-1,0,-3),0.5, material);
//	public final Sphere sphere4 = new Sphere(new Point3(1,0,-6),0.5, material);
//	public final Camera abb10Cam = new OrthographicCamera(new Point3(0,0,0), new Vector3(0,0,-1),new Vector3(0,1,0),4);

	// Uebung 3 Beleuchtung
	// Abbildung 3 Beispielszene

//	public final Camera abb3Cam = new PerspectiveCamera(new Point3(4,4,4), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4);
//	public final Plane plane1 = new Plane(new Point3(0,0,0), new Normal3(0,1,0), new SingleColorMaterial(new raytracer.Color(1,0,0)));
//	public final Triangle triangl1 = new Triangle(new Point3(0,0,-1),new Point3(1,0,-1),new Point3(1,1,-1), new SingleColorMaterial(new raytracer.Color(1,1,0)));
//	public final Sphere sphere5 = new Sphere(new Point3(1,1,1),0.5, new SingleColorMaterial(new raytracer.Color(0,1,0)));
//	public final AxisAlignedBox box1 = new AxisAlignedBox(new Point3(-1.5,0.5,0.5), new Point3(-0.5,1.5,1.5), new SingleColorMaterial(new raytracer.Color(0,0,1)));
//
//	// Uebung 3 Beleuchtung
//	// Abbildung 4 Beispielszene
//
//	public final PointLight pointLight = new PointLight(new raytracer.Color(1,1,1),new Point3(4,4,4),false);
//	public final Plane plane2 = new Plane(new Point3(0,0,0), new Normal3(0,1,0), new LambertMaterial(new raytracer.Color(1,0,0)));
//	public final Triangle triangl2 = new Triangle(new Point3(0,0,-1),new Point3(1,0,-1),new Point3(1,1,-1), new LambertMaterial(new raytracer.Color(1,1,0)));
//	public final Sphere sphere6 = new Sphere(new Point3(1,1,1),0.5, new LambertMaterial(new raytracer.Color(0,1,0)));
//	public final AxisAlignedBox box2 = new AxisAlignedBox(new Point3(-1.5,0.5,0.5), new Point3(-0.5,1.5,1.5), new LambertMaterial(new raytracer.Color(0,0,1)));
//
//	//Abbildung 5
//
//	public final Plane plane3 = new Plane(new Point3(0,0,0), new Normal3(0,1,0), new PhongMaterial(new raytracer.Color(1,0,0),new raytracer.Color(1,1,1),64));
//	public final Triangle triangl3 = new Triangle(new Point3(0,0,-1),new Point3(1,0,-1),new Point3(1,1,-1), new PhongMaterial(new raytracer.Color(1,1,0),new raytracer.Color(1,1,1),64));
//	public final Sphere sphere7 = new Sphere(new Point3(1,1,1),0.5, new PhongMaterial(new raytracer.Color(0,1,0),new raytracer.Color(1,1,1),64));
//	public final AxisAlignedBox box3 = new AxisAlignedBox(new Point3(-1.5,0.5,0.5), new Point3(-0.5,1.5,1.5), new PhongMaterial(new raytracer.Color(0,0,1), new raytracer.Color(1,1,1),64) );


//	//Abbildung 6
//
//	public final DirectionalLight dirLight = new DirectionalLight(new raytracer.Color(1,1,1),new Vector3(-1,-1,-1).normalized(),false );
//
//    //Abbildung 7
//
//	//public final SpotLight spotLight = new SpotLight(new raytracer.Color(1,1,1),new Point3(4,4,4),new Vector3(-1,-1,-1),Math.PI/4);
//	public final SpotLight spotLight = new SpotLight(new raytracer.Color(1,1,1),new Point3(4,4,4),false,new Vector3(-1,-1,-1),Math.PI/14);
//
//
//	//Akzeptanzkriterien II - eigene Szene
//	// Lights
//	public final PointLight pointLight91 = new PointLight(new raytracer.Color(1,1,1), new Point3(4,4,4),false);
//	public final PointLight pointLight92 = new PointLight(new raytracer.Color(1,1,1), new Point3(4,4,0),false);
//	//public final DirectionalLight dirLight9 = new DirectionalLight(new raytracer.Color(1,1,1),new Vector3(-1,-1,-1).normalized() );
//	public final SpotLight spotLight9 = new SpotLight(new raytracer.Color(1,1,1),new Point3(-3,1,-3),false,new Vector3(2,1,2),Math.PI/2);
//
//	// Objekte
//	public final Plane plane9 = new Plane(new Point3(0,0,0), new Normal3(1,1,1), new LambertMaterial(new raytracer.Color(1,0,0)));
//	public final Triangle triangle9 = new Triangle(new Point3(2,0,-1),new Point3(1,1,-1),new Point3(3,2,-1), new LambertMaterial(new raytracer.Color(1,1,0)));
//	public final Sphere sphere91 = new Sphere(new Point3(3,1,2), 0.5, new LambertMaterial(new raytracer.Color(0,1,0)));
//	public final AxisAlignedBox box9 = new AxisAlignedBox(new Point3(-1.5,0.5,0.5), new Point3(-0.5,1.5,1.5), new LambertMaterial(new raytracer.Color(0,0,1)));
//	public final Sphere sphere92 = new Sphere(new Point3(0,0,0),2, new LambertMaterial(new raytracer.Color(1,1,1)));
//
	
	// CAM
	PerspectiveCamera cam9 = new PerspectiveCamera(new Point3(4,8,4),new Vector3(-1,-2,-1),new Vector3(0,1,0),new SamplingPattern(4),Math.PI/4);

	//SCENE SHADOW
	public final PointLight pointLight100 = new PointLight(new raytracer.Color(1,1,1), new Point3(8,8,0),true);
	//public final Plane plane10 = new Plane(new Point3(0,0,0), new Normal3(0,1,0), new LambertMaterial(new raytracer.Color(0.8,0.8,0.8)));
	public final PerspectiveCamera cam10 = new PerspectiveCamera(new Point3(2,2,2),new Vector3(-1,-1,-1),new Vector3(0,1,0),new SamplingPattern(10),Math.PI/4);

	//public final AxisAlignedBox box10 = new AxisAlignedBox(new Point3(-0.5,0,-0.5), new Point3(0.5,1,0.5), new LambertMaterial(new raytracer.Color(1,0,0)));

	//scene Shadow 2

//	public final Sphere sphere11 = new Sphere(new Point3(-3,1,0),1, new ReflectiveMaterial(new raytracer.Color(1,0,0),
//			new raytracer.Color(1,1,1),64,new raytracer.Color(0.5,0.5,0.5)));
//	public final Sphere sphere12 = new Sphere(new Point3(0,1,0),1, new ReflectiveMaterial(new raytracer.Color(0,1,0),
//			new raytracer.Color(1,1,1),64,new raytracer.Color(0.5,0.5,0.5)));
//	public final Sphere sphere13 = new Sphere(new Point3(3,1,0),1, new ReflectiveMaterial(new raytracer.Color(0,0,1),
//			new raytracer.Color(1,1,1),64,new raytracer.Color(0.5,0.5,0.5)));
	public final PerspectiveCamera camTransformation = new PerspectiveCamera(new Point3(0,0,8),new Vector3(0,0,-1),new Vector3(0,1,0),new SamplingPattern(20),Math.PI/4);


	// Transformationen

	public final Sphere s = new Sphere(new ReflectiveMaterial(new raytracer.Color(1,0,0),
		new raytracer.Color(1,1,1),64,new raytracer.Color(0.5,0.5,0.5)));


	//Abbildung3 3 Kugeln mit reflektierendem Material

	public final PointLight pointLight101 = new PointLight(new raytracer.Color(1,1,1), new Point3(8,8,8),true);

	public final Sphere sRed = new Sphere(new ReflectiveMaterial(new raytracer.Color(1,0,0),
			new raytracer.Color(1,1,1),64,new raytracer.Color(0.5,0.5,0.5)));

	public final Sphere sGreen = new Sphere(new ReflectiveMaterial(new raytracer.Color(0,1,0),
			new raytracer.Color(1,1,1),64,new raytracer.Color(0.5,0.5,0.5)));

	public final Sphere sBlue = new Sphere(new ReflectiveMaterial(new raytracer.Color(0,0,1),
			new raytracer.Color(1,1,1),64,new raytracer.Color(0.5,0.5,0.5)));

	public final Plane blackPlane = new Plane(new ReflectiveMaterial(new raytracer.Color(0.1,0.1,0.1),
			new raytracer.Color(0,0,0),64,new raytracer.Color(0.5,0.5,0.5)));


	//Abb4 rote Box auf Ebene
	public final Plane plane1 = new Plane(new LambertMaterial(new raytracer.Color(0.8,0.8,0.8)));

	public final AxisAlignedBox redBox = new AxisAlignedBox(new LambertMaterial(new raytracer.Color(1,0,0)));



	//Transformtionen box/sphere
	public final Plane p = new Plane(new ReflectiveMaterial(new raytracer.Color(1,0,1),
			new raytracer.Color(1,1,1),64,new raytracer.Color(0.5,0.5,0.5)));

	public final AxisAlignedBox b = new AxisAlignedBox(new ReflectiveMaterial(new raytracer.Color(1,0,0),
			new raytracer.Color(1,1,1),64,new raytracer.Color(0.5,0.5,0.5)));


	/**
	 * Drawing Surface:
	 */

	private final VBox root = new VBox();
	private ImageView imageview;
	private WritableImage writableimage;

	/**
	 * The start method initializes the window property at initial point. The
	 * title is added and its initial size is set.
	 */
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Welcome");
		primaryStage.setMinHeight(51);
		primaryStage.setWidth(640);
		primaryStage.setHeight(480);
		initializeMenu(primaryStage);

		// Akzeptanz II
//		welt.lightList.add(pointLight91);
//		welt.lightList.add(pointLight92);
//		welt.lightList.add(spotLight9);
//		
//		welt.list.add(plane9);
//		welt.list.add(triangle9);
//		welt.list.add(sphere91);
//		welt.list.add(box9);
//		welt.list.add(sphere92);
		
//		// Abb 3	
//		welt.list.add(plane1);
//		welt.list.add(triangl1);
//		welt.list.add(sphere5);
//		welt.list.add(box1);
		
		// Abb 4	
//		welt.lightList.add(pointLight);
//		welt.list.add(plane2);
//		welt.list.add(triangl2);
//		welt.list.add(sphere6);
//		welt.list.add(box2);

		
		// Abb 5	
//		welt.lightList.add(pointLight);
		// Abb 6	
//		welt.lightList.add(dirLight);
		// Abb 7 / 8
//		welt.lightList.add(spotLight);
//
//		welt.list.add(plane3);
//		welt.list.add(triangl3);
//		welt.list.add(sphere7);
//		welt.list.add(box3);



		//schatten scene 1
//       //use cam10
		welt.lightList.add(pointLight100);
		abb4Plane.g.add(plane1);
		abb4Box.g.add(redBox);
		welt.list.add(abb4Plane);
		welt.list.add(abb4Box);



		//Schtten scene2
		 //use cam10
//
//		welt.lightList.add(pointLight101);
//		abb3Plane.g.add(blackPlane);
//		abb3sphere1.g.add(sRed);
//		abb3sphere2.g.add(sGreen);
//		abb3sphere3.g.add(sBlue);
////
//		welt.list.add(abb3Plane);
//		welt.list.add(abb3sphere1);
//		welt.list.add(abb3sphere2);
//		welt.list.add(abb3sphere3);
////
//
		// Transformation abb.1
//		abb1.g.add(s);
//		welt.list.add(abb1);

		// Transformation abb.2
//		abb2.g.add(b);
//		welt.list.add(abb2);


//		//TEST
//		abb3Plane.g.add(plane1);
//		test2.g.add(b);
//		test.g.add(sRed);
//		welt.list.add(test);
//		welt.list.add(abb3Plane);
//
//		welt.list.add(test2);

		drawPicture(primaryStage);

		primaryStage.show();
	}

	/**
	 * The drawPicture method draws a picture pixel by pixel using a
	 * writableImage which is stored in the imageview.
	 * 
	 * @param primaryStage
	 *            is needed to get the width and height property for the image
	 *            to be drawn
	 *
	 * In order to be able to draw a completely new picture each time
	 * the method is called, we first need to remove the earlier
	 * added imageview from the VBox. The width and height property
	 * cannot be bound directly because neither the writableimage nor
	 * the imageview provides a property binding method.
	 */
	private void drawPicture(Stage primaryStage) {

		root.getChildren().remove(imageview);

		final int height = (int) primaryStage.getHeight() - 50;
		final int width = (int) primaryStage.getWidth();

		this.imageview = new ImageView();
		this.writableimage = new WritableImage(width, height);

		final PixelWriter writer = writableimage.getPixelWriter();

		try {
			for (int y = 0; y < height; ++y) {
				for (int x = 0; x < width; ++x) {

					writer.setColor(x, y, getColor(width, height, x, y));
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Das Fenster ist zu klein um ein Bild zu Zeichnen." + e.getMessage());
		}

		imageview.setImage(writableimage);
		root.getChildren().add(imageview);
	}

	/**
	 * The getColor method is called in order to decide which color to give the
	 * pixel at the given point (x,y).
	 * 
	 * @param x > 0 && y > 0 coordinate of the pixel which is drawn in this moment
	 * @param width > 0 && height > null, both represent the size of the window -
	 * @return Color object either gets the color from the geometry, which was hit 
	 * or the background color of the world.
	 */



	private Color getColor(int width , int height, int x, int y) throws IllegalArgumentException {

		raytracer.Color hitFarbe=new raytracer.Color(0,0,0);
		raytracer.Color addFarbe = new raytracer.Color(0,0,0);


		Set<Ray> rays = cam10.rayFor(width, height, x, height - 1 - y);

		for (Ray r : rays) {

			hitFarbe = welt.hit(r);


			if(hitFarbe==null)throw new IllegalArgumentException("color has to be not null");

			if (hitFarbe.r < 0) {
				hitFarbe.r = 0;
			}
			if (hitFarbe.r > 1) {
				hitFarbe.r = 1;
			}
			if (hitFarbe.g < 0) {
				hitFarbe.g = 0;
			}
			if (hitFarbe.g > 1) {
				hitFarbe.g = 1;
			}
			if (hitFarbe.b < 0) {
				hitFarbe.b = 0;
			}
			if (hitFarbe.b > 1) {
				hitFarbe.b = 1;
			}
			addFarbe=addFarbe.add(hitFarbe);

		}

		//divide color with pixels of grid
		addFarbe=addFarbe.mul(1f/rays.size());

		return new Color(addFarbe.r, addFarbe.g, addFarbe.b, 1);
	}

	/**
	 * Initializes the menubar.
	 * @param primaryStage needs to be given in oder to call the saveFile method in the lamda expression
	 */
	private void initializeMenu(Stage primaryStage) {

		final MenuBar menubar = new MenuBar();
		final Menu fileMenu = new Menu("File");
		final Menu addTo = new Menu("Add to window");
		final MenuItem world = new MenuItem("New world");
		final MenuItem camera = new MenuItem("New camera");
		final MenuItem plane = new MenuItem("New plane");
		final MenuItem sphere = new MenuItem("New sphere");
		final MenuItem triangle = new MenuItem("New triangle");
		final MenuItem box = new MenuItem("New axis aligned box");		
		final MenuItem save = new MenuItem("Save");

		addTo.getItems().addAll(world, camera, plane, sphere, triangle, box);
		fileMenu.getItems().add(save);
		menubar.getMenus().addAll(fileMenu, addTo);
		
		save.setOnAction(e -> saveFile(primaryStage));

		root.getChildren().add(menubar);
	}

	/**
	 * Initializes the save dialog window and gives the user the possibility to
	 * save the drawn picture as jpg or png. Before being able to save it, we
	 * need to transform the writableimage into a bufferedimage.
	 */
	private void saveFile(Stage primaryStage) {

		final FileChooser fileChooser = new FileChooser();
		final ExtensionFilter png = new ExtensionFilter("PNG File", "*.png");
		final ExtensionFilter jpg = new ExtensionFilter("JPG File", "*.jpg");

		fileChooser.getExtensionFilters().addAll(png, jpg);
		fileChooser.setTitle("Save Image");
		File file = fileChooser.showSaveDialog(primaryStage);

		if (file != null) {

			String fileName = file.getName();
			BufferedImage buff = SwingFXUtils.fromFXImage(writableimage, null);

			if (fileName.contains("png")) {
				try {
					ImageIO.write(buff, "png", file);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Bild kann nicht gespeichert werden. Fehler: " + e.getMessage());
				}
			}

			else {
				BufferedImage image = new BufferedImage(buff.getWidth(), buff.getHeight(),
						BufferedImage.TYPE_BYTE_INDEXED);
				Graphics2D graphics = image.createGraphics();
				graphics.drawImage(buff, 0, 0, null);
				try {
					ImageIO.write(image, "jpg", file);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Bild kann nicht gespeichert werden. Fehler: " + e.getMessage());
				}
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}