package client.view.MainMenu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class Header extends HBox {
  private Button filterButton;
  private Button sortButton;

  Header() {
    this.setPrefSize(500, 60); // Size of the header
    this.setStyle("-fx-background-color: #F0F8FF;");

    String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";

    filterButton = new Button("Filter by: ");
    filterButton.setStyle(defaultButtonStyle);

    sortButton = new Button("Sort by: ");
    sortButton.setStyle(defaultButtonStyle);

    Text titleText = new Text("Main Menu"); // Text of the Header
    titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
    sortButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
    filterButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
    Pane spacer1 = new Pane();
    Pane spacer2 = new Pane();
    Pane spacer3 = new Pane();
    Header.setHgrow(spacer1, Priority.ALWAYS);
    Header.setHgrow(spacer2, Priority.ALWAYS);
    Header.setHgrow(spacer3, Priority.ALWAYS);
    spacer1.setMaxWidth(200);
    spacer2.setMaxWidth(50);
    spacer3.setMaxWidth(10);
    this.getChildren().addAll(spacer1, titleText, spacer2, sortButton, spacer3, filterButton);
    this.setAlignment(Pos.CENTER_LEFT); // Align the text to the Center
  }

  public Button getfilterButton() {
    return filterButton;
  }

  public Button getsortButton() {
    return sortButton;
  }
}