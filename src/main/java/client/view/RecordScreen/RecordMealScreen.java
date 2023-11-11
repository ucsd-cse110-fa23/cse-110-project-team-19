package client.view.RecordScreen;

import client.model.Transcribe;
import client.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class RecordMealScreen extends BorderPane {

  private Header header;
  private Footer footer;
  private RecordMealPrompt recordMealPrompt;

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
    
  }

  public RecordMealPrompt getRecordMealPrompt() {
    return recordMealPrompt;
  }

  public void setGenerateButtonAction(EventHandler<ActionEvent> eventHandler) {
    continueButton.setOnAction(eventHandler);
  }

  
}
