package client.view.RecordScreen;

import client.model.Transcribe;
import client.View;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class RecordMealScreen extends BorderPane {

  private Header header;
  private Footer footer;
  private RecordMealPrompt recordPrompt;

  private Button createButton;

  public RecordMealScreen(View view, String str) {
    header = new Header();
    recordPrompt = new RecordMealPrompt(str);
    footer = new Footer();

    // Add header to the top of the BorderPane
    this.setTop(header);
    // Add scroller to the centre of the BorderPane
    this.setCenter(recordPrompt);
    // Add footer to the bottom of the BorderPane
    this.setBottom(footer);

    createButton = footer.getCreateButton();
    createButton.setOnAction(e -> {
      String mealType = "error";
      try {
        mealType = Transcribe.transcribe();
        String type = Transcribe.checkMealType(mealType);
        if (type == null) {
          view.setRoot("recordMealError");
        } else if (
          type.equals("breakfast") ||
          type.equals("lunch") ||
          type.equals("dinner")
        ) {
          view.setMealType(type);
        } else {
          view.setRoot("recordMealError");
        }
      } catch (Exception exception) {}
      //view.setRoot("recordMealError");
    });
  }
}