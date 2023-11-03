package client.GUI.RecordScreen;

import java.io.File;
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

class RecordPrompt extends VBox {

  private Label prompt;
  private Button startButton;
  private Button stopButton;
  private AudioFormat audioFormat;
  private TargetDataLine targetDataLine;
  private Label recordingLabel;

  RecordPrompt() {
    this.setSpacing(5); // sets spacing between tasks
    this.setPrefSize(500, 560);
    this.setStyle("-fx-background-color: #F0F8FF;");

    prompt = new Label("Record the ingredients for the recipe:");

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

    // Get the audio format
    audioFormat = getAudioFormat();

    // Add the listeners to the buttons
    addListeners();
  }

  public void addListeners() {
    // Start Button
    startButton.setOnAction(e -> {
      startRecording();
    });

    // Stop Button
    stopButton.setOnAction(e -> {
      stopRecording();
    });
  }

  private AudioFormat getAudioFormat() {
    // the number of samples of audio per second.
    // 44100 represents the typical sample rate for CD-quality audio.
    float sampleRate = 44100;

    // the number of bits in each sample of a sound that has been digitized.
    int sampleSizeInBits = 16;

    // the number of audio channels in this format (1 for mono, 2 for stereo).
    int channels = 2;

    // whether the data is signed or unsigned.
    boolean signed = true;

    // whether the audio data is stored in big-endian or little-endian order.
    boolean bigEndian = false;

    return new AudioFormat(
      sampleRate,
      sampleSizeInBits,
      channels,
      signed,
      bigEndian
    );
  }

  private void startRecording() {
    Thread t = new Thread(
      new Runnable() {
        @Override
        public void run() {
          try {
            // the format of the TargetDataLine
            DataLine.Info dataLineInfo = new DataLine.Info(
              TargetDataLine.class,
              audioFormat
            );
            // the TargetDataLine used to capture audio data from the microphone
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
            targetDataLine.open(audioFormat);
            targetDataLine.start();
            recordingLabel.setVisible(true);

            // the AudioInputStream that will be used to write the audio data to a file
            AudioInputStream audioInputStream = new AudioInputStream(
              targetDataLine
            );

            // the file that will contain the audio data
            File audioFile = new File("recording.wav");
            AudioSystem.write(
              audioInputStream,
              AudioFileFormat.Type.WAVE,
              audioFile
            );
            recordingLabel.setVisible(false);
          } catch (Exception e1) {}
        }
      }
    );
    t.start();
  }

  private void stopRecording() {
    targetDataLine.stop();
    targetDataLine.close();
  }
}
