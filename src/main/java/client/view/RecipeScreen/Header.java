package client.view.RecipeScreen;

import client.model.RecipeDetails;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class Header extends HBox {
  private Button backButton;
  private RecipeDetails recipeDetails;



  Header() {
    this.setPrefSize(500, 60); // Size of the header
    this.setStyle("-fx-background-color: #F0F8FF;");

    String defaultButtonStyle =
      "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";

    backButton = new Button("Back"); // text displayed on edit button
    backButton.setStyle(defaultButtonStyle); // styling the button

    Text titleText;
    // if(recipeDetails.getRecipeName() == (null)){
      titleText = new Text("Recipe"); // Text of the Header
    // }
    // else{
    // titleText.setText(recipeDetails.getRecipeName()); // Text of the Header
    // }
    titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
    backButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
    Pane spacer1 = new Pane();
    Pane spacer2 = new Pane();
    Header.setHgrow(spacer1, Priority.ALWAYS);
    Header.setHgrow(spacer2, Priority.ALWAYS);
    spacer1.setMaxWidth(10);
    spacer2.setMaxWidth(160);
    this.getChildren().addAll(spacer1,backButton, spacer2, titleText);
    this.setAlignment(Pos.CENTER_LEFT); // Align the text to the Center
  }

  public Button getbackButton() {
    return backButton;
  }


}
