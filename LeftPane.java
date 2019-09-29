package Ex2;

import Ex2.BandsArrayList.BandsListIterator;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class LeftPane extends StackPane {

	private Button left;
	private BandsDataControllerImpl bdci;
	private BandsListIterator li;
	private MiddlePane middle;

	public LeftPane(BandsDataControllerImpl bdci, MiddlePane middle) {
		this.middle = middle;
		this.bdci = bdci;
		left = new Button("<");
		getChildren().add(left);
		li = bdci.getBli();
		left.setOnAction(e -> pressed());
		setStyle("-fx-background-color: BLACK");

		
	}

	public void pressed() {
		Band band = bdci.previous();
		middle.setCurrentBand(band);
	}
	
	
}
