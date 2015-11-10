package ourWindow;

import javax.swing.SpringLayout.Constraints;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import raytracer.camera.Camera;
import raytracer.camera.OrthographicCamera;
import raytracer.camera.PerspectiveCamera;
import raytracer.matVecLib.Point3;
import raytracer.matVecLib.Vector3;

public class GetCameraWindow extends Stage {

	private final RadioButton togOrto = new RadioButton("Ortografic Camera");
	private final RadioButton togPer = new RadioButton("Perspective Camera");
	private final TextField punktx = new TextField("0");
	private final TextField punkty = new TextField("0");
	private final TextField punktz = new TextField("0");
	private final TextField directionx = new TextField("0");
	private final TextField directiony = new TextField("0");
	private final TextField directionz = new TextField("0");
	private final TextField rotationx = new TextField("0");
	private final TextField rotationy = new TextField("0");
	private final TextField rotationz = new TextField("0");
	private final TextField scaling = new TextField("1");

	public GetCameraWindow(){

		BorderPane root = new BorderPane();
		GridPane middle = new GridPane();
		VBox side = new VBox();
		
		Insets padding = new Insets(20);
		

		ToggleGroup togGru = new ToggleGroup();
		togOrto.setToggleGroup(togGru);
		togPer.setToggleGroup(togGru);
		HBox top = new HBox(togOrto, togPer);
		top.setPadding(padding);
		
		Button button = new Button("Choose");
		Button cancel = new Button("Cancel");
		HBox bottom = new HBox(button, cancel);
		bottom.setPadding(padding);

		middle.setPadding(padding);
		middle.setHgap(20);
		middle.setVgap(20);

		middle.getChildren().addAll(new Label("X"), punktx, new Label("Y"), punkty, new Label("Z"), punktz,
				new Label("X"), directionx, new Label("Y"), directiony, new Label("Z"), directionz, new Label("X"), rotationx,
				new Label("Y"), rotationy, new Label("Z"), rotationz, new Label("Scaling"), scaling);
		side.setPadding(padding);
		side.getChildren().addAll(new Label ("Choose your camera properties:"), new Label ("Coordinates of your camera position:"), 
				new Label("Coordinates of the gaze direction of your camera:"), new Label("Coordinates of the rotation of your camera:"));
		
		button.setOnAction(e -> createCamera(togGru.getSelectedToggle()));

		
		root.setTop(top);
		root.setCenter(middle);
		root.setLeft(side);
		root.setBottom(bottom);
		root.setPadding(padding);
		Scene sceneCamera = new Scene(root);

		this.setWidth(640);
		this.setHeight(480);
		this.setTitle("Create a new camera");
		this.setScene(sceneCamera);
		this.initModality(Modality.APPLICATION_MODAL);
		this.showAndWait();
	}

	private Camera createCamera(Toggle selectedToggle) {

		Point3 punkt = new Point3(Double.valueOf(punktx.getText()), Double.valueOf(punkty.getText()),
				Double.valueOf(punktz.getText()));
		Vector3 direction = new Vector3(Double.valueOf(directionx.getText()), Double.valueOf(directiony.getText()),
				Double.valueOf(directionz.getText()));
		Vector3 rotation = new Vector3(Double.valueOf(rotationx.getText()), Double.valueOf(rotationy.getText()),
				Double.valueOf(rotationz.getText()));

		if (selectedToggle == togOrto) {
			return new OrthographicCamera(punkt, direction, rotation, Double.valueOf(scaling.getText()));
		}

		return new PerspectiveCamera(punkt, direction, rotation, Double.valueOf(scaling.getText()));

	}

}
