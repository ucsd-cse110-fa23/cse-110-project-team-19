import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

class ContactFooter extends HBox {

  private Button saveButton;
  private Button cancelButton;

  ContactFooter() {
    this.setPrefSize(500, 60);
    this.setStyle("-fx-background-color: #F0F8FF;");
    this.setSpacing(15);
    this.setAlignment(Pos.CENTER);

    String defaultButtonStyle =
      "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";

    saveButton = new Button("Save");
    saveButton.setStyle(defaultButtonStyle);
    cancelButton = new Button("Cancel");
    cancelButton.setStyle(defaultButtonStyle);

    this.getChildren().addAll(saveButton, cancelButton);
    this.setAlignment(Pos.CENTER);
  }

  public Button getSaveButton() {
    return saveButton;
  }

  public Button getCancelButton() {
    return cancelButton;
  }
}
