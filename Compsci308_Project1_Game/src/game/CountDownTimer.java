package game;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class CountDownTimer {

	private Timeline timeline;
	private Label countdownLbl = new Label();
	private Integer START_TIME;
	private IntegerProperty countdownSeconds;
	public static boolean countDownOver = false;

	public CountDownTimer(int startTime, Ship myShip, boolean bossBattle) {
		START_TIME = startTime;
		countdownSeconds = new SimpleIntegerProperty(START_TIME);

		countdownLbl.textProperty().bind(countdownSeconds.asString());
		countdownLbl.setTextFill(Color.RED);

		countdownSeconds.set(START_TIME);
		timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(START_TIME), e -> {
			timeline.setOnFinished(event -> {
				if(bossBattle){ 
					goToBossBattle(myShip);
				} else {
					//handle key input? 
				}
			});
		}, new KeyValue(countdownSeconds, 0)));

	}

	public void startCountDown() {
		timeline.playFromStart();
	}

	public void goToBossBattle(Ship myShip) {
		System.out.println("Going to boss battle");
		countDownOver = true;
	}

	public Label getLabel() {
		return countdownLbl;
	}

}
