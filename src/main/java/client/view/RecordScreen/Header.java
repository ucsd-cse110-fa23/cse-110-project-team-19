package client.view.RecordScreen;

import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class Header extends HBox {
  private Button backButton;

  Header() {
    this.setPrefSize(500, 60); // Size of the header
    this.setStyle("-fx-background-color: #F0F8FF;");

    String defaultButtonStyle =
      "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";

    backButton = new Button("Back");
    backButton.setStyle(defaultButtonStyle);

    Text titleText = new Text("Record Screen"); // Text of the Header
    titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
    this.getChildren().addAll(titleText,backButton);
    this.setAlignment(Pos.CENTER); // Align the text to the Center

  }

  public Button getBackButton() {
    return backButton;
  }
}
