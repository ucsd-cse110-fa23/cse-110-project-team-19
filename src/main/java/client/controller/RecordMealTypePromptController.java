package client.controller;

import client.View;
import client.view.MainMenu.MainMenu;
import client.model.RecipeDetails;
import client.view.RecipeScreen.RecipeScreen;
import client.view.RecordScreen.RecordMealPrompt;
import java.io.File;
import javafx.event.ActionEvent;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

public class RecordMealTypePromptController {

    private RecordMealPrompt recordMealTypePrompt;
    private TargetDataLine targetDataLine;

    public RecordMealTypePromptController(
    RecordMealPrompt recordMealTypePrompt
  ) {
    this.recordMealTypePrompt = recordMealTypePrompt;
    this.recordMealTypePrompt.setStartButtonAction(this::handleStartButton);
    this.recordMealTypePrompt.setStopButtonAction(this::handleStopButton);
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
            targetDataLine = (TargetDataLine) AudioSystem.getLine(
              dataLineInfo
            );
            targetDataLine.open(audioFormat);
            targetDataLine.start();
            recordMealTypePrompt.getRecordingLabel().setVisible(true);

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
            

            recordMealTypePrompt.getRecordingLabel().setVisible(false);
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
