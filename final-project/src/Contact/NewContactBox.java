package Contact;

import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

class NewContactBox extends VBox {

  private TextField firstName;
  private TextField lastName;
  private TextField number;
  private TextField email;
  private ImageView imageView;
  private Image profileImage;

  NewContactBox(Contact contact, Stage primaryStage) {
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

    Button uploadButton = new Button("Upload Image");
    uploadButton.setOnAction(e -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser
        .getExtensionFilters()
        .add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
      Stage imageStage = new Stage();
      File selectedFile = fileChooser.showOpenDialog(imageStage);

      if (selectedFile != null) {
        Image image = new Image(selectedFile.toURI().toString());

        imageView.setImage(image);
        profileImage = image;
      }
    });
    this.getChildren().add(uploadButton);

    firstName = new TextField();
    firstName.setPrefSize(380, 20);
    firstName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
    firstName.setPromptText("Enter your first name.");
    if (contact.getFirstName() != null) {
      firstName.setText(contact.getFirstName());
    }
    firstName.setPadding(new Insets(10, 0, 10, 0));
    this.getChildren().add(firstName);

    lastName = new TextField();
    lastName.setPrefSize(380, 20);
    lastName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");

    lastName.setPromptText("Enter your last name.");
    if (contact.getLastName() != null) {
      lastName.setText(contact.getLastName());
    }
    lastName.setPadding(new Insets(10, 0, 10, 0));
    this.getChildren().add(lastName);

    number = new TextField();
    number.setPrefSize(380, 20);
    number.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
    number.setPromptText("Enter your number.");
    if (contact.getPhoneNumber() != null) {
      number.setText(contact.getPhoneNumber());
    }
    number.setPadding(new Insets(10, 0, 10, 0));
    this.getChildren().add(number);

    email = new TextField();
    email.setPrefSize(380, 20);
    email.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
    email.setPromptText("Enter your email.");
    if (contact.getEmail() != null) {
      email.setText(contact.getEmail());
    }
    email.setPadding(new Insets(10, 0, 10, 0));
    this.getChildren().add(email);
  }

  public String getFirstName() {
    if (this.firstName.getText().equals("")) {
      return null;
    }
    return this.firstName.getText();
  }

  public String getLastName() {
    if (this.lastName.getText().equals("")) {
      return null;
    }
    return this.lastName.getText();
  }

  public String getPhoneNumber() {
    if (this.number.getText().equals("")) {
      return null;
    }
    return this.number.getText();
  }

  public String getEmail() {
    if (this.email.getText().equals("")) {
      return null;
    }
    return this.email.getText();
  }

  public Image getProfileImage() {
    return this.profileImage;
  }
  //Make a save contact method where you call set methods
  //Use get text to get info from text field
}
