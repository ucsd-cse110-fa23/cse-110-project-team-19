package client.controller;

import client.View;
import client.model.RecipeDetails;
import client.view.MainMenu.MainMenu;
import client.view.MainMenu.Recipe;
import client.view.RecipeScreen.RecipeScreen;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

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
  }

  private void handleSaveButton(ActionEvent event) {
    Recipe recipe = new Recipe();
    recipe.getRecipeName().setText(recipeDetails.toString());
    mainMenu.getRecipeList().getChildren().add(recipe);
    view.setRoot("main");
  }

  private void handledeleButton(ActionEvent event) {
    recipeScreen.deleButton.setOnAction(e -> {
      Stage addStage = new Stage();
      addStage.setTitle("Delete comfirmation");
      GridPane grid = new GridPane();
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setPadding(new Insets(50, 10, 50, 10));
      addStage.setScene(new Scene(grid, 400, 150));
      addStage.setResizable(false);
      addStage.show();

      Label prompt = new Label("Are you sure you want to delete this recipe?");

      prompt.setStyle(
        "-fx-border-width: 0; -fx-font-weight: bold; -fx-font-size: 15px"
      );
      Button confirmButton = new Button("Delete");
      confirmButton.setFocusTraversable(false);
      Button cancelButton = new Button("cancel");
      cancelButton.setFocusTraversable(false);
      grid.add(prompt,5,0);
      HBox buttonBox = new HBox(10); 
      buttonBox.getChildren().addAll(confirmButton, cancelButton);
      buttonBox.setAlignment(Pos.CENTER);
      grid.add(buttonBox, 5,3);
    
      buttonBox.setAlignment(Pos.CENTER);
      cancelButton.setOnAction(e1 -> {
        addStage.close();

      });
      confirmButton.setOnAction(e2 -> {
        // delete recipe in main menu

      });

    });

  }
}
