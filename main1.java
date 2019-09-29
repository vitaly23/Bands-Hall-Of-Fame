package Ex2;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class main1 extends Application {

	private BandsDataControllerImpl bdci;
	
	private BorderPane mainLayout = new BorderPane();
	private upperPane topPane;
	private MiddlePane middlePane;
	private LeftPane leftPane;
	private RightPane rightPane;
	private BottomPane bottomPane;

	public static void main(String[] args) {

		launch(args);
	}//############# MY OS IS MAC IF YOUR'E USING WINDOWS YOU'LL PROBOBLY WILL HAVE TO CHANGE THE "FILE_NAME"
	// TO ".\\bands.bin"

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		bdci = BandsDataControllerImpl.getInstance();
		middlePane = new MiddlePane(bdci);
		topPane = new upperPane(bdci,middlePane);
		leftPane = new LeftPane(bdci,middlePane);
		rightPane = new RightPane(bdci,middlePane);
		bottomPane = new BottomPane(bdci, middlePane);
		mainLayout.setTop(topPane);
		mainLayout.setCenter(middlePane);
		mainLayout.setLeft(leftPane);
		mainLayout.setRight(rightPane);
		mainLayout.setBottom(bottomPane);
		mainLayout.setOnKeyPressed(e -> leftKeyPressed(e));

		mainLayout.setOnKeyPressed(e -> rightKeyPressed(e));
		mainLayout.setOnKeyPressed(e -> {
			try {
				saveKeyPressed(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		mainLayout.setOnKeyPressed(e -> {
			try {
				saveKeyPressed(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		mainLayout.setOnKeyPressed(e -> {
			try {
				undoKeyPressed(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Metal Hall Of Fame");
		primaryStage.show();
	}
	
	private void rightKeyPressed(KeyEvent e) {
		if(e.getCode() == KeyCode.RIGHT) {
			rightPane.pressed();
			
		}
		setAndRequestFocus();
	}
	
	private void setAndRequestFocus(){
		mainLayout.setFocusTraversable(true);
		mainLayout.requestFocus();
	}
	
	private void leftKeyPressed(KeyEvent e) {
		if(e.getCode() == KeyCode.LEFT) {
			leftPane.pressed();
			setAndRequestFocus();
		}
	}
	
	private void saveKeyPressed(KeyEvent e) throws IOException {
		if(e.getCode() == KeyCode.S && e.isControlDown()) {
			bottomPane.savePressed();;
			setAndRequestFocus();
		 }
	}
	
	private void undoKeyPressed(KeyEvent e) throws IOException {
		if(e.getCode() == KeyCode.Z && e.isControlDown()) {
			bottomPane.undoPressed();
			setAndRequestFocus();
		 }
	}
}
