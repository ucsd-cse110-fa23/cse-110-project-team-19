package client.view.RecipeScreen;

import client.model.RecipeDetails;
import client.view.RecipeScreen.DetailedRecipeView;
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
  private DetailedRecipeView detailedRecipeView;

  private Button saveButton;

  public RecipeScreen() {
    header = new Header();
    recipeDetails = new RecipeDetails();
    detailedRecipeView = new DetailedRecipeView();
    footer = new Footer();

    // Add header to the top of the BorderPane
    this.setTop(header);
    // Add scroller to the centre of the BorderPane
    this.setCenter(detailedRecipeView);
    // Add footer to the bottom of the BorderPane
    this.setBottom(footer);

    saveButton = footer.getSaveButton();
  }

  public Button getSaveButton() {
    return this.saveButton;
  }

  public RecipeDetails getRecipeDetails() {
    return this.recipeDetails;
  }

  public DetailedRecipeView getDetailedRecipeView() {
    return this.detailedRecipeView;
  }

  public void setSaveButtonAction(EventHandler<ActionEvent> eventHandler) {
    this.saveButton.setOnAction(eventHandler);
  }
}
