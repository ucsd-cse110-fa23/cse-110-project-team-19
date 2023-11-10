package client.controller;

import client.View;
import client.view.MainMenu.MainMenu;
import client.view.RecipeScreen.RecipeDetails;
import client.view.RecipeScreen.RecipeScreen;
import javafx.event.ActionEvent;

public class RecipeScreenController {

  private RecipeScreen recipeScreen;
  private RecipeDetails recipeDetails;
  private View view;

  public RecipeScreenController(View view, RecipeScreen recipeScreen) {
    this.recipeScreen = recipeScreen;
    this.recipeDetails = recipeScreen.getRecipeDetails();
    this.view = view;
    this.recipeScreen.setSaveButtonAction(this::handleSaveButton);
  }

  private void handleSaveButton(ActionEvent event) {
    ((MainMenu) view.getRoot("main")).createRecipe(recipeDetails.toString());
    view.setRoot("main");
  }
}
