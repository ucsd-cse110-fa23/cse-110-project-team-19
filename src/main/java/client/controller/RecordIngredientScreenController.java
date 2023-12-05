package client.controller;

import client.View;
import client.model.ATranscribe;
import client.model.Transcribe;
import client.view.MainMenu.Recipe;
import client.view.RecipeScreen.DetailedRecipeView;
import client.view.RecipeScreen.RecipeScreen;
import client.view.RecordScreen.RecordIngredientScreen;
import java.time.LocalDateTime;
import javafx.event.ActionEvent;

public class RecordIngredientScreenController {

  private RecordIngredientScreen recordIngredientScreen;
  private View view;
  private ATranscribe transcriber = new Transcribe();

  public RecordIngredientScreenController(
    View view,
    RecordIngredientScreen recordIngredientScreen
  ) {
    this.recordIngredientScreen = recordIngredientScreen;
    this.view = view;
    this.recordIngredientScreen.setGenerateButtonAction(
        this::handleGenerateButton
      );
  }

  private void handleGenerateButton(ActionEvent event) {
    String ingredients = "error";
    try {
      ingredients = transcriber.transcribe();

      DetailedRecipeView detailedRecipeView =
        ((RecipeScreen) view.getRoot("recipe")).getDetailedRecipeView();

      ((RecipeScreen) view.getRoot("recipe")).getRecipeDetails()
        .newRecipe(view.getMealType(), ingredients);

      detailedRecipeView.setText(
        ((RecipeScreen) view.getRoot("recipe")).getRecipeDetails().getRecipe()
      );
    } catch (Exception exception) {}
    Recipe recipe = new Recipe(view);
    recipe.setRecipe(
      ((RecipeScreen) view.getRoot("recipe")).getRecipeDetails().getRecipe()
    );
    view.recipeScreen.setRecipe(recipe);
    view.recipeScreen.getFooter().switchToCreating();
    view.setRoot("recipe");
  }
}
