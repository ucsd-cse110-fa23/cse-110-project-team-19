package client.view.ServerScreen;

import static com.mongodb.client.model.Filters.text;

import client.View;
import client.view.RecipeScreen.DetailedRecipeView;
import client.view.RecipeScreen.RecipeScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

public class ServerError extends HBox {

  private Label error;
    private String prompt;

  public ServerError() {
    this.setPrefSize(500, 20); // sets size of task
    this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");

    error = new Label("SERVER ERROR: Server is Down");
    error.setPrefSize(500, 400);
    error.setAlignment(Pos.CENTER);
    //text.setPadding(new Insets(5, 0, 0, 10));
    error.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of label
    //text.setTextAlignment(TextAlignment.CENTER); // set alignment of label
    error.setWrapText(true);
    this.getChildren().add(error); // add text to recipe details
  }

  public Label getError() {
    return this.error;
  }

  public void setError(String str) {
    this.error.setText(str);
  }

  public String getPrompt() {
    return this.prompt;
  }

  public void setPrompt(String str) {
    this.prompt = str;
  }
}
