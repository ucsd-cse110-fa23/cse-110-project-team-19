package client.view.RecordScreen;

import client.View;
import client.model.Transcribe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class RecordMealScreen extends BorderPane {

  private Header header;
  private Footer footer;
  private RecordMealPrompt recordMealPrompt;
  public Button backButton;
  private Button continueButton;

  public RecordMealScreen(View view, String str) {
    header = new Header();
    recordMealPrompt = new RecordMealPrompt(str);
    footer = new Footer();

    // Add header to the top of the BorderPane
    this.setTop(header);
    // Add scroller to the centre of the BorderPane
    this.setCenter(recordMealPrompt);
    // Add footer to the bottom of the BorderPane
    this.setBottom(footer);

    continueButton = footer.getCreateButton();
    backButton = header.getBackButton();
  }

  public RecordMealPrompt getRecordMealPrompt() {
    return recordMealPrompt;
  }

  public void setBackButtonAction(EventHandler<ActionEvent> eventHandler) {
    backButton.setOnAction(eventHandler);
  }

  public void setGenerateButtonAction(EventHandler<ActionEvent> eventHandler) {
    continueButton.setOnAction(eventHandler);
  }
}
