package client.view.RecipeScreen;

import client.model.RecipeDetails;

import java.io.File;
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
    deleButton = footer.getDeleteButton();

    
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


  public void setDeleButtonAction(EventHandler<ActionEvent> eventHandler) {
    deleButton.setOnAction(eventHandler);

  }
  
  public void generateRecipe(String mealType, String ingredients)
      throws IOException, InterruptedException, URISyntaxException {
    try {
      recipeDetails.newRecipe(mealType, ingredients);
    } catch (Exception e) {
    }
  }
}
