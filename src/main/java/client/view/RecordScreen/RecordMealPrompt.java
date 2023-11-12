package client.view.RecordScreen;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

public class RecordMealPrompt extends VBox {

  private Label prompt;
  private Button startButton;
  private Button stopButton;
  // private AudioFormat audioFormat;
  private Label recordingLabel;

  RecordMealPrompt(String str) {
    this.setSpacing(5); // sets spacing between tasks
    this.setPrefSize(500, 560);
    this.setStyle("-fx-background-color: #F0F8FF;");

    prompt = new Label(str);

    prompt.setStyle(
      "-fx-border-width: 0; -fx-font-weight: bold; -fx-font-size: 24px"
    );
    startButton = new Button("Start");
    startButton.setFocusTraversable(false);
    stopButton = new Button("Stop");
    stopButton.setFocusTraversable(false);
    recordingLabel = new Label("Recording...");
    this.getChildren().add(prompt);
    HBox buttonBox = new HBox(8); // spacing = 8
    buttonBox.getChildren().addAll(startButton, stopButton);
    buttonBox.setAlignment(Pos.CENTER);
    this.getChildren().addAll(buttonBox, recordingLabel);
    recordingLabel.setVisible(false);
    this.setAlignment(Pos.TOP_CENTER);

  }

 public Label getRecordingLabel() {
    return this.recordingLabel;
  }

  public void setStartButtonAction(EventHandler<ActionEvent> eventHandler) {
    startButton.setOnAction(eventHandler);
  }

  public void setStopButtonAction(EventHandler<ActionEvent> eventHandler) {
    stopButton.setOnAction(eventHandler);
  }


}
