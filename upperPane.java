package Ex2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class upperPane extends VBox {
	
	private static final double SPACE = 15d;
	private Timeline timeline;
	private ComboBox<String> combo;
	private ObservableList<String> sortBy;
	private Text text;
	private final int DURATION = 300;
	private final String DATE_FORMAT = "dd/MM/YYYY";
	private final String CLOCK_FORMAT = "hh:mm:ss";
	private Comparator<Band> sorts[];
	private BandsDataControllerImpl bdci;
	private MiddlePane middlePane;
	
	public upperPane(BandsDataControllerImpl bdci, MiddlePane middlePane) {
		this.bdci = bdci;
		this.middlePane = middlePane;
		sorts = new Comparator[] 
				{new BandComparatorByName(), new BandComparatorByFansDecreasing(), new BandComparatorByOrigin()};
		setAlignment(Pos.CENTER);
		text = new Text(setTime());
		text.setFill(Color.RED);
		timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.cycleDurationProperty();
		timeline.getKeyFrames().add(new KeyFrame(new Duration(DURATION), e -> text.setText(setTime())));
		timeline.play();
		sortBy = FXCollections.observableArrayList(new String("Sort By Name"),
				new String("Sort By Fans"), new String("Sort By Origin"));
		combo = new ComboBox<>();
		combo.setPromptText("Sort By...");
		combo.setItems(sortBy);
		getChildren().addAll(text,combo);
		setPaneStyle();
		setPath();
		combo.setOnAction(e->sortPressed());
	}
	
	public void  sortPressed() {
		int index = combo.getSelectionModel().getSelectedIndex();
		bdci.sort(sorts[index]);
		middlePane.updateTexts();
	}

	private void setPaneStyle() {
		// TODO Auto-generated method stub
		setStyle("-fx-background-color: BLACK");
		setSpacing(SPACE);
		setPadding(new Insets(SPACE));
	}

	private void setPath() {
		// TODO Auto-generated method stub
		
		Line line = new Line(-100,0,350,0);
		getChildren().add(line);
		
		PathTransition path = new PathTransition(new Duration(4000), line);
		path.setCycleCount(Animation.INDEFINITE);
		path.setNode(text);
		path.setAutoReverse(true);
		//path.setDelay(Duration.millis(4000));
		path.play();
		
		setOnMouseEntered(e -> path.pause());
		setOnMouseExited(e -> path.play());
		
	}

	private String setTime() {
		String str =LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT))+" ";
		str += LocalTime.now().format(DateTimeFormatter.ofPattern(CLOCK_FORMAT))+"  Metal Hall Of Fame";
		return str;
	}
			
	
	
}
