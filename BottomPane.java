package Ex2;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class BottomPane extends HBox {

	private Button save,removeBand,undo,revert;
	private final int SPACE = 15;
	private BandsDataControllerImpl bdci;
	private MiddlePane middlePane;

	public BottomPane(BandsDataControllerImpl bdci,MiddlePane middlePane) {
		this.middlePane = middlePane;
		this.bdci = bdci;
		save = new Button("Save");
		removeBand = new Button("Remove Band");
		undo = new Button("Undo");
		revert = new Button("Revert");
		getChildren().addAll(save,removeBand,undo,revert);
		setPaddingAndSpacing();
		setStyle("-fx-background-color: BLACK");
		save.setOnAction(e -> {
			try {
				savePressed();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		removeBand.setOnAction(e->removePressed());
		undo.setOnAction(e->undoPressed());
		revert.setOnAction(e->revertPressed());
	}
	
	public void revertPressed() {
		bdci.revert();
		middlePane.updateTexts();
	}

	public void undoPressed() {
		bdci.undo();
		middlePane.setCurrentBand(bdci.getBand());
		//middlePane.updateTexts();
		
	}

	public void removePressed() {
		bdci.remove();
		middlePane.setCurrentBand(bdci.getBand());
	}

	public void savePressed() throws IOException {
		bdci.save();
		middlePane.updateTexts();
	}

	public void setPaddingAndSpacing() {
		setPadding(new Insets(SPACE));
		setSpacing(SPACE);
		setAlignment(Pos.CENTER);
	}
	
}
