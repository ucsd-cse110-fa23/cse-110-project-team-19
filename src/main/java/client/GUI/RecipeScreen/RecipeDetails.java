package client.GUI.RecipeScreen;

import javafx.scene.control.Label;
import javafx.scene.layout.*;

class RecipeDetails extends VBox {

  private String title;
  private Label body;

  RecipeDetails(String title, String details) {
    this.title = title;

    this.setSpacing(5); // sets spacing between tasks
    this.setPrefSize(500, 560);
    this.setStyle("-fx-background-color: #F0F8FF;");

    body = new Label("");
    body.setPrefSize(500, 400);
    //text.setPadding(new Insets(5, 0, 0, 10));
    body.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of label
    //text.setTextAlignment(TextAlignment.CENTER); // set alignment of label
    body.setWrapText(true);
    this.getChildren().add(body); // add text to recipe details
    body.setText(details);
  }

  public String getTitle(){
    return title;
  }
}
