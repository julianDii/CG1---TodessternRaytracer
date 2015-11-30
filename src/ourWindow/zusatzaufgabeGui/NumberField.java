package ourWindow.zusatzaufgabeGui;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import javafx.scene.control.TextField;



public class NumberField extends TextField{

	private static final String regex = "[+-]?[\\d]+[\\.]?[\\d]*";


	public NumberField(){
		setMaxSize(50, 10);
	}

	public NumberField(String text){
		setMaxSize(50, 10);
		setText(text);
	}

	public double getNumber() {
		String text = getText();
		try {
			if (text.matches(regex)) {
				return Double.parseDouble(text);
			}
			throw new IllegalArgumentException();

		} catch (IllegalArgumentException e) {
			System.out.println("Du darfst keine Buchstaben eingeben!");
		}
		return 0;
	}
}
