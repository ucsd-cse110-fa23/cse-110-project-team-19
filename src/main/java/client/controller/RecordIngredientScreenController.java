package client.controller;

import client.View;
import client.model.ATranscribe;
import client.model.RecipeImage;
import client.model.Transcribe;
import client.view.MainMenu.Recipe;
import client.view.RecipeScreen.DetailedRecipeView;
import client.view.RecipeScreen.RecipeScreen;
import client.view.RecordScreen.RecordIngredientScreen;
import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.scene.image.*;

public class RecordIngredientScreenController {

  private RecordIngredientScreen recordIngredientScreen;
  private View view;
  private RecipeImage recipeImage;
  private ATranscribe transcriber = new Transcribe();
  private DetailedRecipeView detailedRecipeView;

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

      detailedRecipeView =
        ((RecipeScreen) view.getRoot("recipe")).getDetailedRecipeView();

      ((RecipeScreen) view.getRoot("recipe")).getRecipeDetails()
        .newRecipe(view.getMealType(), ingredients);

      detailedRecipeView.setText(
        ((RecipeScreen) view.getRoot("recipe")).getRecipeDetails().getRecipe()
      );

      recipeImage = ((RecipeScreen) view.getRoot("recipe")).getRecipeImage();
      recipeImage.NewImage(
        ((RecipeScreen) view.getRoot("recipe")).getRecipeDetails()
          .getRecipeName()
      );

      detailedRecipeView.SI(recipeImage.getURL());
    } catch (Exception exception) {}
    Recipe recipe = new Recipe(view);
    recipe.setRecipe(
      ((RecipeScreen) view.getRoot("recipe")).getRecipeDetails().getRecipe()
    );
    recipe.setImageURL(
      ((RecipeScreen) view.getRoot("recipe")).getRecipeImage().getURL()
    );

    recipe.setTime();
    view.recipeScreen.setRecipe(recipe);
    view.recipeScreen.getFooter().switchToCreating();
    view.setRoot("recipe");
  }
}
