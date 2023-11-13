package client.controller;

import client.Model;
import client.View;
import client.view.MainMenu.MainMenu;
import client.view.MainMenu.Recipe;
import client.view.RecipeScreen.DetailedRecipeView;
import client.view.RecipeScreen.RecipeScreen;
import javafx.event.ActionEvent;

public class MainMenuController {

  private MainMenu mainMenu;
  private View view;
  private Recipe recipe;

  public MainMenuController(View view, MainMenu mainMenu, Model model) {
    this.mainMenu = mainMenu;
    this.view = view;

    String query = "all";
    String response = model.performRequest("GET", null, null, query);
    if (response != null) {
      String[] recipes = response.split(",");
      System.out.println(response);
      for (String name : recipes) {
        String recipeName = name.replaceAll("_", " ");
        recipe = new Recipe();
        String recipeContent = model.performRequest("GET", null, null, name);
        recipe.setRecipe(recipeContent);
        recipe.getRecipeName().setText(recipeName);
        recipe.setRecipeButtonAction(this::handleRecipeButtonAction);
        mainMenu.getRecipeList().getChildren().add(recipe);
        new RecipeScreenController(
          view,
          view.viewRecipeScreen,
          mainMenu,
          model,
          recipe
        );
      }
    }

    this.mainMenu.setCreateButtonAction(this::handleCreateButton);
  }

  private void handleRecipeButtonAction(ActionEvent event) {
    DetailedRecipeView detailedRecipeView =
      ((RecipeScreen) view.getRoot("viewRecipe")).getDetailedRecipeView();

    detailedRecipeView.setText(recipe.getRecipe());
    view.setRoot("viewRecipe");
    view.viewRecipeScreen.setRecipe(recipe);
  }

  private void handleCreateButton(ActionEvent event) {
    view.setRoot("recordMeal");
  }
}
