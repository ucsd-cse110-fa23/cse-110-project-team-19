package client.view.AccountScreen;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class Login extends VBox {

  private TextField username;
  private TextField password;
  private Label errorPrompt;
  private CheckBox automaticLogin;

  public Login(String prompt) {
    this.setSpacing(5); // sets spacing between tasks
    this.setPrefSize(500, 560);
    this.setStyle("-fx-background-color: #F0F8FF;");

    String textStyle = "-fx-background-color: #DAE5EA; -fx-border-width: 0;";

    errorPrompt = new Label(prompt);
    errorPrompt.setStyle(
      "-fx-border-width: 0; -fx-font-weight: bold; -fx-font-size: 18px"
    );

    username = new TextField();
    username.setPrefSize(380, 20);
    username.setStyle(textStyle);

    password = new TextField();
    password.setPrefSize(380, 20);
    password.setStyle(textStyle);

    automaticLogin = new CheckBox("Stay logged in?");

    this.getChildren()
      .addAll(
        errorPrompt,
        new Label("Username:"),
        username,
        new Label("Password:"),
        password,
        automaticLogin
      );
  }

  public String getUsername() {
    return username.getText();
  }

  public String getPassword() {
    return password.getText();
  }

  public void clearLogin() {
    password.clear();
    username.clear();
  }

  public boolean automaticLogin() {
    return automaticLogin.isSelected();
  }
}
