package Contact;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

class Footer extends HBox {

  private Button newButton;
  private Button saveButton;
  private Button sortButton;

  Footer() {
    this.setPrefSize(500, 60);
    this.setStyle("-fx-background-color: #F0F8FF;");
    this.setSpacing(15);

    String defaultButtonStyle =
      "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";

    newButton = new Button("New Contact");
    newButton.setStyle(defaultButtonStyle);
    saveButton = new Button("Export Contacts to Disk");
    saveButton.setStyle(defaultButtonStyle);
    sortButton = new Button("Sort Contacts (By Name)");
    sortButton.setStyle(defaultButtonStyle);

    this.getChildren().addAll(newButton, saveButton, sortButton);
    this.setAlignment(Pos.CENTER);
  }

  public Button getNewButton() {
    return newButton;
  }

  public Button getSaveButton() {
    return saveButton;
  }

  public Button getSortButton() {
    return sortButton;
  }
}
