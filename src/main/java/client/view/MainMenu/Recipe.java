package client.view.MainMenu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

public class Recipe extends HBox {

  private Button recipeName;
  private String recipe;

  public Recipe() {
    this.setPrefSize(500, 20); // sets size of task
    this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");

    recipeName = new Button(); // create task name text field
    recipeName.setPrefSize(380, 20); // set size of text field
    recipeName.setStyle(
      "-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold; -fx-underline: true;"
    ); // set background color of textfield
    recipeName.setPadding(new Insets(10, 0, 0, 0)); // adds some padding to the text field
    this.getChildren().add(recipeName); // add textlabel to task
    //recipeName.setTextAlignment(TextAlignment.LEFT);
    this.setAlignment(Pos.BOTTOM_LEFT);
  }

  // TODO
  // public void setTaskIndex(int num) {
  //   this.index.setText(num + ""); // num to String
  //   this.recipeName.setText("Task " + num);
  // }

  public void setRecipeButtonAction(EventHandler<ActionEvent> eventHandler) {
    this.recipeName.setOnAction(eventHandler);
  }

  public Button getRecipeName() {
    return this.recipeName;
  }

  public void setRecipe(String recipe) {
    this.recipe = recipe;
  }

  public String getRecipe() {
    return this.recipe;
  }
}
