package client.controller;

import client.View;
import client.model.RecipeDetails;
import client.view.MainMenu.MainMenu;
import client.view.MainMenu.Recipe;
import client.view.RecipeScreen.RecipeScreen;
import javafx.event.ActionEvent;

public class RecipeScreenController {

  private RecipeScreen recipeScreen;
  private RecipeDetails recipeDetails;
  private View view;
  private MainMenu mainMenu;

  public RecipeScreenController(View view, RecipeScreen recipeScreen) {
    this.recipeScreen = recipeScreen;
    this.recipeDetails = recipeScreen.getRecipeDetails();
    this.view = view;
    this.recipeScreen.setSaveButtonAction(this::handleSaveButton);
    this.mainMenu = ((MainMenu) view.getRoot("main"));
  }

  private void handleSaveButton(ActionEvent event) {
    Recipe recipe = new Recipe();
    // doesn't correctly store recipe name
    recipe.getRecipeName().setText(recipeDetails.toString());
    mainMenu.getRecipeList().getChildren().add(recipe);
    view.setRoot("main");
  }
}
