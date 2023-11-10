package client.view.RecipeScreen;

import client.model.RecipeDetails;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class RecipeScreen extends BorderPane {

  private Header header;
  private Footer footer;
  private RecipeDetails recipeDetails;

  private Button saveButton;

  public RecipeScreen() {
    header = new Header();
    recipeDetails = new RecipeDetails();
    footer = new Footer();

    // Add header to the top of the BorderPane
    this.setTop(header);
    // Add scroller to the centre of the BorderPane
    this.setCenter(recipeDetails);
    // Add footer to the bottom of the BorderPane
    this.setBottom(footer);

    saveButton = footer.getSaveButton();
  }

  public Button getSaveButton() {
    return saveButton;
  }

  public RecipeDetails getRecipeDetails() {
    return recipeDetails;
  }

  public void setSaveButtonAction(EventHandler<ActionEvent> eventHandler) {
    saveButton.setOnAction(eventHandler);
  }

  public void generateRecipe(String mealType, String ingredients)
    throws IOException, InterruptedException, URISyntaxException {
    try {
      recipeDetails.newRecipe(mealType, ingredients);
    } catch (Exception e) {}
  }
}
