package client.view.RecipeScreen;

import client.model.IRecipeDetails;
import client.model.RecipeDetails;
import client.model.RecipeImage;
import client.view.MainMenu.Recipe;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane;
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
  private IRecipeDetails recipeDetails;
  public Button deleteButton;
  private DetailedRecipeView detailedRecipeView;
  private RecipeImage recipeImage;
  public Button backButton;
  public Recipe recipe;

  public Button editButton;
  public Button regenButton;
  private Button saveButton;
  public Button shareButton;

  private ScrollPane scrollPane;

  public RecipeScreen(boolean newRecipe) {
    header = new Header();
    recipeDetails = new RecipeDetails();
    recipeImage = new RecipeImage();
    detailedRecipeView = new DetailedRecipeView();
    footer = new Footer();

    scrollPane = new ScrollPane();
    scrollPane.setContent(detailedRecipeView);
    
    scrollPane.setFitToWidth(true);

    // Add header to the top of the BorderPane
    this.setTop(header);
    // Add scroller to the centre of the BorderPane
    this.setCenter(scrollPane);
    // Add footer to the bottom of the BorderPane
    this.setBottom(footer);

    saveButton = footer.getSaveButton();
    deleteButton = footer.getDeleteButton();
    backButton = header.getbackButton();
    editButton = footer.getEditButton();
    regenButton = footer.getRegenButton();

  }

 
  public Button getSaveButton() {
    return this.saveButton;
  }

  public IRecipeDetails getRecipeDetails() {
    return this.recipeDetails;
  }

  public DetailedRecipeView getDetailedRecipeView() {
    return this.detailedRecipeView;
  }

  public RecipeImage getRecipeImage() {
    return this.recipeImage;
  }
  
  
  public void setSaveButtonAction(EventHandler<ActionEvent> eventHandler) {
    this.saveButton.setOnAction(eventHandler);
  }

  public void setDeleteButtonAction(EventHandler<ActionEvent> eventHandler) {
    this.deleteButton.setOnAction(eventHandler);
  }

  public void setShareButtonAction(EventHandler<ActionEvent> eventHandler) {
    this.shareButton.setOnAction(eventHandler);
  }

  public void setbackButtonAction(EventHandler<ActionEvent> eventHandler) {
    this.backButton.setOnAction(eventHandler);
  }

  public void setEditButtonAction(EventHandler<ActionEvent> eventHandler) {
    this.editButton.setOnAction(eventHandler);
  }

   public void setRegenButtonAction(EventHandler<ActionEvent> eventHandler) {
    this.regenButton.setOnAction(eventHandler);
  }

  public void generateRecipe(String mealType, String ingredients)
    throws IOException, InterruptedException, URISyntaxException {
    try {
      recipeDetails.newRecipe(mealType, ingredients);
    } catch (Exception e) {}
  }

  public void setRecipe(Recipe recipe) {
    this.recipe = recipe;
  }

  public String getRecipe() {
    return this.recipe.getRecipe();
  }

  public Footer getFooter() {
    return this.footer;
  }
}
