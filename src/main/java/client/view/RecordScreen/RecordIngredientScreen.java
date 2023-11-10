package client.view.RecordScreen;

import client.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class RecordIngredientScreen extends BorderPane {

  private Header header;
  private Footer footer;
  private RecordIngredientPrompt recordIngredientPrompt;

  private Button generateButton;

  public RecordIngredientScreen() {
    header = new Header();
    recordIngredientPrompt = new RecordIngredientPrompt();
    footer = new Footer();

    // Add header to the top of the BorderPane
    this.setTop(header);
    // Add scroller to the centre of the BorderPane
    this.setCenter(recordIngredientPrompt);
    // Add footer to the bottom of the BorderPane
    this.setBottom(footer);

    generateButton = footer.getCreateButton();
  }

  public RecordIngredientPrompt getRecordIngredientPrompt() {
    return recordIngredientPrompt;
  }

  public void setGenerateButtonAction(EventHandler<ActionEvent> eventHandler) {
    generateButton.setOnAction(eventHandler);
  }
}
