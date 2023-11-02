import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

class ViewFooter extends HBox {

  private Button closeButton;

  ViewFooter() {
    this.setPrefSize(500, 60);
    this.setStyle("-fx-background-color: #F0F8FF;");
    this.setSpacing(15);
    this.setAlignment(Pos.CENTER);

    String defaultButtonStyle =
      "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";

    closeButton = new Button("Close");
    closeButton.setStyle(defaultButtonStyle);

    this.getChildren().addAll(closeButton);
    this.setAlignment(Pos.CENTER);
  }

  public Button getCloseButton() {
    return closeButton;
  }
}
