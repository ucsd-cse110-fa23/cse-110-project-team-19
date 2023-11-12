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
  private RecipeDetails recipeDetails;
  public Button deleteButton;
  private DetailedRecipeView detailedRecipeView;
  public Button backButton;

  public Button editButton;
  private Button saveButton;

  private ScrollPane scrollPane;

  public RecipeScreen() {
    header = new Header();
    recipeDetails = new RecipeDetails();
    detailedRecipeView = new DetailedRecipeView();
    footer = new Footer();

    scrollPane = new ScrollPane();
    scrollPane.setContent(detailedRecipeView);
    scrollPane.setFitToHeight(true);
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

  public void setDeleteButtonAction(EventHandler<ActionEvent> eventHandler) {
    this.deleteButton.setOnAction(eventHandler);
  }

  public void setbackButtonAction(EventHandler<ActionEvent> eventHandler) {
    this.backButton.setOnAction(eventHandler);
  }

  public void setEditButtonAction(EventHandler<ActionEvent> eventHandler) {
    this.editButton.setOnAction(eventHandler);
  }

  public void generateRecipe(String mealType, String ingredients)
    throws IOException, InterruptedException, URISyntaxException {
    try {
      recipeDetails.newRecipe(mealType, ingredients);
    } catch (Exception e) {}
  }
}
