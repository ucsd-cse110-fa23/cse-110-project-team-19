package client.controller;

import client.View;
import client.model.RecipeDetails;
import client.model.ATranscribe;
import client.model.Transcribe;
import client.view.RecipeScreen.DetailedRecipeView;
import client.view.RecipeScreen.RecipeScreen;
import client.view.RecordScreen.RecordIngredientScreen;
import java.io.IOException;
import java.net.URISyntaxException;
import javafx.event.ActionEvent;

public class RecordIngredientScreenController {

  private RecordIngredientScreen recordIngredientScreen;
  private View view;
  //private RecipeDetails recipeDetails;
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
    view.setRoot("recipe");
  }
}
