package ourWindow.zusatzaufgabeGui.windows;

import javafx.scene.control.Label;

/**
 * This class represents the window of the OBJLoader menu.
 * You can type a file name in the text field and choose a texture.
 * Created by Juliand on 09.01.16.
 */
public class ObjWindow extends Window {

    private final Label name = new Label("INSERT FILE NAME");

    private final NumberField fileName = new NumberField();




    public ObjWindow(){


        this.setTitle("OBJloader");

        initRoot();
    }

    @Override

    public void initRoot() {
        super.initRoot();

        bt1.setText("LOAD");
        bt2.setText("CAN");
        

    }


}
