package ourWindow.zusatzaufgabeGui.windows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This Class builds a basic MenuScreen (window). Is's created with a border plane and a grid pane to structure the
 * layouts. Also we have a box with two buttons in the bottom area of the BorderPane.
 * Created by Juliand on 09.01.16.
 */
public class Window extends Stage {


    /**
     * The first button component.
     */
    public Button bt1 = new Button("XX");
    /**
     * The second button component
     */
    public Button bt2 = new Button("XX");

    /**
     * The grid pane to structure the layout of the window.
     */
    public GridPane root = new GridPane();

    /**
     * The border pane for layouting the main components on the window.
     */
    public BorderPane border = new BorderPane();



    /**
     * This constructor builds the basic window.
     */
    public Window(){


        Scene window = new Scene(border);
        initRoot();
        this.setWidth(300);
        this.setHeight(120);
        this.setTitle("overwrite PLS");
        this.setScene(window);
        this.initModality(Modality.APPLICATION_MODAL);
        this.showAndWait();
    }

    /**
     * This method sets-up the stuff.
     */
    public void initRoot() {

        border.setCenter(root);

        root.setPadding(new Insets(3,10,10,30));
        root.setVgap(5);
        root.setHgap(5);

        bt1.setMaxSize(100, 10);
        bt2.setMaxSize(100, 10);

        HBox btnBox = new HBox();
        btnBox.setPadding(new Insets(3,3,15,115));

        btnBox.setSpacing(5);
        btnBox.getChildren().addAll(bt1,bt2);

        border.setBottom(btnBox);

    }
}
