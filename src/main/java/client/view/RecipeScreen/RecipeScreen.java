package client.view.RecipeScreen;

import client.model.RecipeDetails;
import client.view.RecipeScreen.DetailedRecipeView;
import java.io.IOException;
import java.net.URISyntaxException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class RecipeScreen extends BorderPane {

  private Header header;
  private Footer footer;
  private RecipeDetails recipeDetails;
  public Button deleButton;
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
    deleButton = footer.getDeleteButton();

    
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


  public void generateRecipe(String mealType, String ingredients)
    throws IOException, InterruptedException, URISyntaxException {
    try {
      recipeDetails.newRecipe(mealType, ingredients);
    } catch (Exception e) {}
  }
}
