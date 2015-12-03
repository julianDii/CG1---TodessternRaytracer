package ourWindow.zusatzaufgabeGui;

import javafx.scene.control.TextField;



public class NumberField extends TextField{

	private static final String regex = "[+-]?[\\d]+[\\.]?[\\d]*";
	private String initialText = "0";

	public NumberField(){
		setMaxSize(50, 10);
		setText(initialText);
		setOnMouseClicked(event1 -> this.clear());
		setOnMouseExited(event2 -> setNumber());
	}

	public NumberField(String text){
		setMaxSize(50, 10);
		setText(text);
		setNumber();
		setOnMouseClicked(event1 -> this.clear());
		setOnMouseExited(event2 -> setNumber());
	}

	private void setNumber(){
		String text = getText();
		if (!text.matches(regex) || text == null) {
			this.setFocused(true);
			this.setText(initialText);
		} else {
			this.initialText = text;
		}
	}

	public double getNumber(){
		return Double.parseDouble(initialText);
	}
}
