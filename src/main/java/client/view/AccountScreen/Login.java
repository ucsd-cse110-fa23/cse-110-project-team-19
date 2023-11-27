package client.view.AccountScreen;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class Login extends VBox {

  private TextField username;
  private TextField password;

  public Login() {
    this.setSpacing(5); // sets spacing between tasks
    this.setPrefSize(500, 560);
    this.setStyle("-fx-background-color: #F0F8FF;");

    String textStyle = "-fx-background-color: #DAE5EA; -fx-border-width: 0;";

    username = new TextField();
    username.setPrefSize(380, 20);
    username.setStyle(textStyle);

    password = new TextField();
    password.setPrefSize(380, 20);
    password.setStyle(textStyle);

    this.getChildren()
      .addAll(
        new Label("Username:"),
        username,
        new Label("Password:"),
        password
      );
  }

  public String getUsername() {
    return username.getText();
  }

  public String getPassword() {
    return password.getText();
  }
}
