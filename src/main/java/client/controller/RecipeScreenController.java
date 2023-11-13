package client.controller;

import client.Model;
import client.View;
import client.model.RecipeDetails;
import client.view.MainMenu.MainMenu;
import client.view.MainMenu.Recipe;
import client.view.RecipeScreen.DetailedRecipeView;
import client.view.RecipeScreen.RecipeScreen;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RecipeScreenController {

  private RecipeScreen recipeScreen;
  private RecipeDetails recipeDetails;
  private View view;
  private MainMenu mainMenu;
  private Recipe recipe;
  private Model model;

  public RecipeScreenController(
    View view,
    RecipeScreen recipeScreen,
    MainMenu mainMenu,
    Model model,
    Recipe recipe
  ) {
    this.recipeScreen = recipeScreen;
    this.recipeDetails = recipeScreen.getRecipeDetails();
    this.view = view;
    this.model = model;
    this.mainMenu = mainMenu;
    this.recipe = recipe;
    this.recipeScreen.setSaveButtonAction(this::handleSaveButton);

    this.recipeScreen.setDeleteButtonAction(this::handleDeleteButton);

    this.recipeScreen.setEditButtonAction(this::handleEditButton);

    this.recipeScreen.setbackButtonAction(this::handlebackButton);
  }

  private void handleSaveButton(ActionEvent event) {
    recipe = new Recipe();
    recipe.setRecipe(recipeDetails.getRecipe());
    // doesn't correctly store recipe name
    recipe.getRecipeName().setText(recipeDetails.getRecipeName());
    recipe.setRecipeButtonAction(this::handleRecipeButtonAction);
    mainMenu.getRecipeList().getChildren().add(recipe);
    String name = recipeDetails.getRecipeName().replaceAll(" ", "_");
    model.performRequest("POST", name, recipeDetails.getRecipe(), null);
    view.setRoot("main");
  }

  private void handleRecipeButtonAction(ActionEvent event) {
    DetailedRecipeView detailedRecipeView =
      ((RecipeScreen) view.getRoot("viewRecipe")).getDetailedRecipeView();

    detailedRecipeView.setText(recipe.getRecipe());
    view.setRoot("viewRecipe");
    view.viewRecipeScreen.setRecipe(recipe);
  }

  private void handleDeleteButton(ActionEvent event) {
    Stage addStage = new Stage();
    addStage.setTitle("Delete confirmation");
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(50, 10, 50, 10));
    addStage.setScene(new Scene(grid, 400, 150));
    addStage.setResizable(false);
    addStage.show();

    Label prompt = new Label("Are you sure you want to delete this recipe?");

    prompt.setStyle(
      "-fx-border-width: 0; -fx-font-weight: bold; -fx-font-size: 15px"
    );
    Button confirmButton = new Button("Delete");
    confirmButton.setFocusTraversable(false);
    Button cancelButton = new Button("Cancel");
    cancelButton.setFocusTraversable(false);
    grid.add(prompt, 5, 0);
    HBox buttonBox = new HBox(10);
    buttonBox.getChildren().addAll(confirmButton, cancelButton);
    grid.add(buttonBox, 5, 3);

    buttonBox.setAlignment(Pos.CENTER);
    cancelButton.setOnAction(e1 -> {
      addStage.close();
    });
    confirmButton.setOnAction(e2 -> {
      // delete recipe in main menu
      mainMenu
        .getRecipeList()
        .getChildren()
        .remove(view.viewRecipeScreen.recipe);
      addStage.close();
      view.setRoot("main");
    });
  }

  private void handleEditButton(ActionEvent event) {
    Stage addStage = new Stage();
    addStage.setTitle("Edit Recipe");
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(5, 5, 5, 5));
    addStage.setScene(new Scene(grid, 400, 500));
    addStage.setResizable(false);
    addStage.show();

    TextArea prompt = new TextArea();
    prompt.setText(view.viewRecipeScreen.getRecipe());
    prompt.setMinSize(350, 425);

    Button saveButton = new Button("Save");
    saveButton.setFocusTraversable(false);
    grid.add(prompt, 2, 2);
    HBox buttonBox = new HBox(4);
    buttonBox.getChildren().addAll(saveButton);
    grid.add(buttonBox, 2, 1);

    buttonBox.setAlignment(Pos.CENTER);
    saveButton.setOnAction(e1 -> {
      addStage.close();
    });
  }

  private void handlebackButton(ActionEvent event) {
    view.setRoot("main");
  }
}
