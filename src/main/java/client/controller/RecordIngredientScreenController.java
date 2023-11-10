package client.controller;

import client.View;
import client.view.RecipeScreen.RecipeScreen;
import client.view.RecordScreen.RecordIngredientScreen;
import client.view.RecordScreen.Transcribe;
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
      ((RecipeScreen) view.getRoot("recipe")).generateRecipe(
          view.getMealType(),
          ingredients
        );
    } catch (Exception exception) {}
    view.setRoot("recipe");
  }
}
