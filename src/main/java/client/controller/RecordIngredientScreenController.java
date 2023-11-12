package client.controller;

import client.View;
import client.model.Transcribe;
import client.view.RecipeScreen.DetailedRecipeView;
import client.view.RecipeScreen.RecipeScreen;
import client.view.RecordScreen.RecordIngredientScreen;
import javafx.event.ActionEvent;

public class RecordIngredientScreenController {

  private RecordIngredientScreen recordIngredientScreen;
  private View view;

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
      ingredients = Transcribe.transcribe();

      DetailedRecipeView detailedRecipeView =
        ((RecipeScreen) view.getRoot("recipe")).getDetailedRecipeView();

      ((RecipeScreen) view.getRoot("recipe")).getRecipeDetails()
        .newRecipe(view.getMealType(), ingredients);

      detailedRecipeView.setText(
        ((RecipeScreen) view.getRoot("recipe")).getRecipeDetails().getRecipe()
      );
    } catch (Exception exception) {}
    view.setRoot("recipe");
  }
}
