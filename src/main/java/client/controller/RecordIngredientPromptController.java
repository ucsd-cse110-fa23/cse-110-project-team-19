package client.controller;

import client.view.RecordScreen.RecordIngredientPrompt;
import java.io.File;
import javafx.event.ActionEvent;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

public class RecordIngredientPromptController {

  private RecordIngredientPrompt recordIngredientPrompt;
  private TargetDataLine targetDataLine;

  public RecordIngredientPromptController(
    RecordIngredientPrompt recordIngredientPrompt
  ) {
    this.recordIngredientPrompt = recordIngredientPrompt;
    this.recordIngredientPrompt.setStartButtonAction(this::handleStartButton);
    this.recordIngredientPrompt.setStopButtonAction(this::handleStopButton);
  }

  private void handleStartButton(ActionEvent event) {
    Thread t = new Thread(
      new Runnable() {
        @Override
        public void run() {
          try {
            // Get the audio format
            AudioFormat audioFormat = new AudioFormat(
              44100,
              16,
              1,
              true,
              false
            );

            // the format of the TargetDataLine
            DataLine.Info dataLineInfo = new DataLine.Info(
              TargetDataLine.class,
              audioFormat
            );
            // the TargetDataLine used to capture audio data from the microphone
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
            targetDataLine.open(audioFormat);
            targetDataLine.start();
            recordIngredientPrompt.getRecordingLabel().setVisible(true);

            // the AudioInputStream that will be used to write the audio data to a file
            AudioInputStream audioInputStream = new AudioInputStream(
              targetDataLine
            );

            // the file that will contain the audio data
            File audioFile = new File("ingredients.wav");
            audioFile.delete();

            AudioSystem.write(
              audioInputStream,
              AudioFileFormat.Type.WAVE,
              audioFile
            );

            recordIngredientPrompt.getRecordingLabel().setVisible(false);
          } catch (Exception e1) {}
        }
      }
    );
    t.start();
  }

  private void handleStopButton(ActionEvent event) {
    targetDataLine.stop();

    targetDataLine.close();
  }
}
