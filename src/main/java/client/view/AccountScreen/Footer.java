package client.view.AccountScreen;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class Footer extends VBox {

  private Button createAccountButton;
  private Button loginButton;

  private String mainButtonStyle;
  private String smallButtonStyle;

  Footer() {
    this.setPrefSize(500, 60);
    this.setStyle("-fx-background-color: #F0F8FF;");
    this.setSpacing(15);

    // set a default style for buttons - background color, font size, italics
    mainButtonStyle =
      "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 24 arial;";

    smallButtonStyle =
      "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 14 arial;";

    createAccountButton = new Button("Create Account"); // text displayed on add button
    createAccountButton.setStyle(mainButtonStyle); // styling the button

    loginButton = new Button("Login");
    loginButton.setStyle(smallButtonStyle);

    this.getChildren().addAll(createAccountButton, loginButton); // adding buttons to footer
    this.setAlignment(Pos.CENTER); // aligning the buttons to center
  }

  public Button getCreateAccountButton() {
    return createAccountButton;
  }

  public Button getLoginButton() {
    return loginButton;
  }

  public void switchToLogin() {
    this.getChildren().removeAll(createAccountButton, loginButton);
    this.getChildren().addAll(loginButton, createAccountButton);
    loginButton.setStyle(mainButtonStyle);
    createAccountButton.setStyle(smallButtonStyle);
  }

  public void switchToCreate() {
    this.getChildren().removeAll(createAccountButton, loginButton);
    this.getChildren().addAll(createAccountButton, loginButton);
    createAccountButton.setStyle(mainButtonStyle);
    loginButton.setStyle(smallButtonStyle);
  }
}
