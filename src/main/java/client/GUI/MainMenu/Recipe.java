package client.GUI.MainMenu;

import client.View;
import client.GUI.RecipeScreen.RecipeScreen;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

public class Recipe extends HBox {

  private Label index;
  private Button recipeName;

  Recipe(View view, String title) {
    this.setPrefSize(500, 20); // sets size of task
    this.setStyle(
        "-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"
      ); // sets background color of task

    index = new Label();
    index.setText(""); // create index label
    index.setPrefSize(40, 20); // set size of Index label
    index.setTextAlignment(TextAlignment.CENTER); // Set alignment of index label
    index.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the task
    this.getChildren().add(index); // add index label to task

    recipeName = new Button(title); // create task name text field
    recipeName.setPrefSize(380, 20); // set size of text field
    recipeName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of texfield
    index.setTextAlignment(TextAlignment.LEFT); // set alignment of text field
    recipeName.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the text field
    this.getChildren().add(recipeName); // add textlabel to task
    recipeName.setOnAction(e -> {
      RecipeScreen recipeScreen = (RecipeScreen) view.getRoot("recipe");
      recipeScreen.switchToViewing();
      recipeScreen.setRecipeDetail(title);
      view.setRoot("recipe");
    });
  }

  public void setTaskIndex(int num) {
    this.index.setText(num + ""); // num to String
    this.recipeName.setText("Task " + num);
  }

  public Button getRecipeName() {
    return this.recipeName;
  }

  public void setRecipeName(String name) {
    recipeName.setText(name);
  }
}
