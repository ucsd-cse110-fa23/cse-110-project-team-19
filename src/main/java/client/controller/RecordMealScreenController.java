package client.controller;

import client.View;
import client.view.RecipeScreen.RecipeScreen;
import client.view.RecordScreen.RecordIngredientScreen;
import client.view.RecordScreen.RecordMealScreen;
import client.model.Transcribe;
import javafx.event.ActionEvent;

public class RecordMealScreenController {
    private RecordMealScreen recordMealScreen;
    private View view;

    public RecordMealScreenController(
    View view,
    RecordMealScreen recordMealScreen
  ) {
    this.recordMealScreen = recordMealScreen;
    this.view = view;
    this.recordMealScreen.setGenerateButtonAction(
        this::handleGenerateButton
      );
  }

  private void handleGenerateButton(ActionEvent event) {
    String mealType = "error";
      try {
        mealType = Transcribe.transcribe();
        String type = Transcribe.checkMealType(mealType);
        if (type == null) {
          view.setRoot("recordMealError");
        } else if (
          type.equals("breakfast") ||
          type.equals("lunch") ||
          type.equals("dinner")
        ) {
          view.setMealType(type);
        } else {
          view.setRoot("recordMealError");
        }
      } catch (Exception exception) {}
  }
  
}
