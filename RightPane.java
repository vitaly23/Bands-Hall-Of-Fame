package Ex2;

import Ex2.BandsArrayList.BandsListIterator;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public class RightPane extends StackPane {

	private Button right;
	private BandsDataControllerImpl bdci;
	private BandsListIterator li;
	private MiddlePane middle;

	public RightPane(BandsDataControllerImpl bdci,MiddlePane middle) {
		this.bdci = bdci;
		this.middle = middle;
		right = new Button(">");
		getChildren().add(right);
		li = bdci.getBli();
		right.setOnAction(e -> pressed());
		setStyle("-fx-background-color: BLACK");
		
	}

	

	public void pressed() {
		Band band = bdci.next();
		middle.setCurrentBand(band);
		}
	
}
