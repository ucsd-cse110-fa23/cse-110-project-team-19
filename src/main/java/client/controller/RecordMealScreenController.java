package client.controller;

import client.View;
import client.view.MainMenu.MainMenu;
import client.view.RecipeScreen.RecipeScreen;
import client.view.RecordScreen.RecordIngredientScreen;
import client.view.RecordScreen.RecordMealScreen;
import client.model.ATranscribe;
import client.model.Transcribe;
import client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class RecordMealScreenController {
    private RecordMealScreen recordMealScreen;
    private View view;
    private ATranscribe transcriber = new Transcribe();
    
    public RecordMealScreenController(View view, RecordMealScreen recordMealScreen) {
    this.recordMealScreen = recordMealScreen;
    this.view = view;
    this.recordMealScreen.setGenerateButtonAction(this::handleGenerateButton);
    this.recordMealScreen.setBackButtonAction(this::handleBackButton);
  }

  private void handleGenerateButton(ActionEvent event) {
    String mealType = "error";
      try {
        mealType = transcriber.transcribe();
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


  
  private void handleBackButton(ActionEvent event) {
    view.setRoot("main");
  
  }
}
