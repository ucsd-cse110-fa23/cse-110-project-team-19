package client.GUI.RecordScreen;

import client.GUI.RecipeScreen.RecipeScreen;

import java.io.IOException;
import java.net.URISyntaxException;

import client.View;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class RecordIngredientScreen extends BorderPane {

  private Header header;
  private Footer footer;
  private RecordIngredientPrompt recordPrompt;

  private Button createButton;

  public RecordIngredientScreen(View view, String meal) {
    header = new Header();
    recordPrompt = new RecordIngredientPrompt();
    footer = new Footer();

    // Add header to the top of the BorderPane
    this.setTop(header);
    // Add scroller to the centre of the BorderPane
    this.setCenter(recordPrompt);
    // Add footer to the bottom of the BorderPane
    this.setBottom(footer);

    createButton = footer.getCreateButton();
    createButton.setOnAction(e -> {
      String ingredients = "error";
      try {
        // String details = ChatGPTRecipeGenerator.getFakeRecipe();
        ingredients = Transcribe.transcribe();
        String details = ChatGPTRecipeGenerator.generateNewRecipe(meal, ingredients);
        RecipeScreen recipeScreen = (RecipeScreen) view.getRoot("recipe");
        recipeScreen.putRecipeDetail(ChatGPTRecipeGenerator.getTitleOfString(details), details);
      } catch (Exception exception) {}
      view.setRoot("recipe");
    });
  }
}
