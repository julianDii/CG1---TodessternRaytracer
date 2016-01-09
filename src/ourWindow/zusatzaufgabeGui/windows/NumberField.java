package ourWindow.zusatzaufgabeGui.windows;

import javafx.scene.control.TextField;



public class NumberField extends TextField{

	private static final String regex = "[+-]?[\\d]+[\\.]?[\\d]*";

	public NumberField(){
		this("0");
	}

	public NumberField(String text){
		setMaxSize(50, 10);
		setText(text);
		setNumber();
		setOnMouseClicked(e -> this.clear());
		focusedProperty().addListener(event2 -> setNumber());
	}

	private void setNumber(){
		String text = getText();
		if (!text.matches(regex)||text==null) {
			this.setText("0");
		} else {
			this.setText(text);
		}
	}

	public double getNumber(){
		System.out.println(Double.parseDouble(getText()));
		return Double.parseDouble(getText());
	}
}
