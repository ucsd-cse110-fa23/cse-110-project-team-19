package client.view.RecordScreen;

import javafx.scene.control.Button;
import javafx.geometry.Insets;
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
    //this.getChildren().addAll(titleText,backButton);
    


    backButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
    Pane spacer1 = new Pane();
    Pane spacer2 = new Pane();
    Header.setHgrow(spacer1, Priority.ALWAYS);
    Header.setHgrow(spacer2, Priority.ALWAYS);
    spacer1.setMaxWidth(10);
    spacer2.setMaxWidth(140);
    this.getChildren().addAll(spacer1,backButton, spacer2, titleText);
    this.setAlignment(Pos.CENTER_LEFT); // Align the text to the Center


  }

  public Button getBackButton() {
    return backButton;
  }
}
