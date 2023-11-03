package client.GUI.RecordScreen;

import client.GUI.MainMenu.MainMenu;
import client.GUI.RecipeScreen.RecipeScreen;
import client.View;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class RecordScreen extends BorderPane {

  private Header header;
  private Footer footer;
  private RecordPrompt recordPrompt;

  private Button createButton;

  public RecordScreen(View view) {
    header = new Header();
    recordPrompt = new RecordPrompt();
    footer = new Footer();

    // Add header to the top of the BorderPane
    this.setTop(header);
    // Add scroller to the centre of the BorderPane
    this.setCenter(recordPrompt);
    // Add footer to the bottom of the BorderPane
    this.setBottom(footer);

    createButton = footer.getCreateButton();
    createButton.setOnAction(e -> {
      ((RecipeScreen) view.getRoot("recipe")).generateRecipe("", "");
      view.setRoot("recipe");
    });
  }
}
