package client.pantryPal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

class ViewContactBox extends VBox {

  private Label firstName;
  private Label lastName;
  private Label number;
  private Label email;
  private ImageView imageView;
  private Image profileImage;

  ViewContactBox(Contact contact, Stage primaryStage) {
    this.setSpacing(5);
    this.setPrefSize(500, 560);
    this.setStyle("-fx-background-color: #F0F8FF;");
    this.setAlignment(Pos.TOP_CENTER);

    imageView = new ImageView();
    this.getChildren().add(imageView);
    profileImage = contact.getProfileImage();
    imageView.setImage(profileImage);
    imageView.setFitHeight(100);
    imageView.setPreserveRatio(true);

    firstName = new Label();
    firstName.setPrefSize(500, 20);
    firstName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
    if (contact.getFirstName() != null) {
      firstName.setText("First Name: " + contact.getFirstName());
      this.getChildren().add(firstName);
    }
    firstName.setPadding(new Insets(10, 0, 10, 10));

    lastName = new Label();
    lastName.setPrefSize(500, 20);
    lastName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");

    if (contact.getLastName() != null) {
      lastName.setText("Last Name: " + contact.getLastName());
      this.getChildren().add(lastName);
    }
    lastName.setPadding(new Insets(10, 0, 10, 10));

    number = new Label();
    number.setPrefSize(500, 20);
    number.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
    if (contact.getPhoneNumber() != null) {
      number.setText("Number: " + contact.getPhoneNumber());
      number.setPadding(new Insets(10, 0, 10, 10));
      this.getChildren().add(number);
    }

    email = new Label();
    email.setPrefSize(500, 20);
    email.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
    if (contact.getEmail() != null) {
      email.setText("Email: " + contact.getEmail());
      email.setPadding(new Insets(10, 0, 10, 10));
      this.getChildren().add(email);
    }
  }

  public String getFirstName() {
    return this.firstName.getText();
  }

  public String getLastName() {
    return this.lastName.getText();
  }

  public String getPhoneNumber() {
    return this.number.getText();
  }

  public String getEmail() {
    return this.email.getText();
  }

  public Image getProfileImage() {
    return this.profileImage;
  }
}
