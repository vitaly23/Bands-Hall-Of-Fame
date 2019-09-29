package Ex2;

import Ex2.BandsArrayList.BandsListIterator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class MiddlePane extends GridPane {
	
	private final static int SPACE = 15;
	private BandsDataControllerImpl bcdi;
	private BandsListIterator li;
	private Label bandLbl;
	private Label formedLbl;
	private Label fansLbl;
	private Label originLbl;
	private Label splitLbl;
	private Label styleLbl;
	private TextField bandTxt;
	private TextField formedTxt;
	private TextField fansTxt;
	private TextField originTxt;
	private TextField styleTxt;
	private CheckBox splitCheck;
	private Band currentBand ;
	
	
	public MiddlePane(BandsDataControllerImpl bcdi) {
		setAlignment(Pos.CENTER);
		this.bcdi = bcdi;
		li =bcdi.getBli();
		setStyle("-fx-background-color: BLACK");
		setUpLabels();
		setUpElse();
		addToRows();
		setPaddingAndSpacing();
	}
	
	public void setUpLabels() {
		bandLbl = new Label("Band: ");
		formedLbl = new Label("Formed: ");
		fansLbl = new Label("Fans: ");
		originLbl = new Label("Origin: ");
		splitLbl = new Label("Did they split: ");
		styleLbl = new Label("Style: ");
		bandLbl.setTextFill(Color.RED);
		formedLbl.setTextFill(Color.RED);
		fansLbl.setTextFill(Color.RED);
		originLbl.setTextFill(Color.RED);
		splitLbl.setTextFill(Color.RED);
		styleLbl.setTextFill(Color.RED);
	}
	
	public void setUpElse() {
		li = bcdi.getBli();
		currentBand = li.next();
		bcdi.setBand(currentBand);
		bandTxt = new TextField(currentBand.getName());
		bandTxt.setEditable(false);
		formedTxt = new TextField(currentBand.getFormedYear().toString());
		formedTxt.setEditable(false);
		fansTxt = new TextField(Integer.toString(currentBand.getNumOfFans()));
		fansTxt.setEditable(false);
		originTxt = new TextField(currentBand.getOrigin());
		originTxt.setEditable(false);
		styleTxt = new TextField(currentBand.getStyle());
		styleTxt.setEditable(false);
		splitCheck = new CheckBox();
		splitCheck.setSelected(currentBand.hasSplit());
		splitCheck.setDisable(true);
	}
	
	public void addToRows() {
		addRow(0, bandLbl,bandTxt);
		addRow(1, fansLbl,fansTxt);
		addRow(2, formedLbl,formedTxt);
		addRow(3, originLbl,originTxt);
		addRow(4, splitLbl,splitCheck);
		addRow(5, styleLbl,styleTxt);
	}

	public Band getCurrentBand() {
		return currentBand;
	}

	public void setCurrentBand(Band currentBand) {
		this.currentBand = currentBand;
		//bcdi.setBand(currentBand);
		updateTexts();
	}

	public void updateTexts() {
		bandTxt.setText(currentBand.getName());
		formedTxt.setText(currentBand.getFormedYear().toString());
		fansTxt.setText(Integer.toString(currentBand.getNumOfFans()));
		originTxt.setText(currentBand.getOrigin());
		styleTxt.setText(currentBand.getStyle());
		splitCheck.setSelected(currentBand.hasSplit());;
	}
	public void setPaddingAndSpacing() {
		setPadding(new Insets(SPACE));
		setHgap(SPACE);
		setVgap(SPACE);
	}
	
}
