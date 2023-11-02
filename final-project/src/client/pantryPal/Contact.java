package client.pantryPal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

public class Contact extends HBox implements Comparable<Contact> {

  private ImageView imageView;
  private Image profileImage;
  private Label contactName;
  private Button editButton;
  private Button viewButton;
  private Button deleteButton;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String email;

  Contact() {
    this.setPrefSize(500, 50);
    this.setStyle(
        "-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"
      );

    imageView = new ImageView();
    this.getChildren().add(imageView);

    contactName = new Label();
    contactName.setPrefSize(300, 50);
    contactName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
    contactName.setTextAlignment(TextAlignment.CENTER);
    contactName.setPadding(new Insets(10, 0, 10, 10));
    this.getChildren().add(contactName);

    viewButton = new Button("View");
    viewButton.setPrefSize(100, 20);
    viewButton.setPrefHeight(Double.MAX_VALUE);
    viewButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");

    editButton = new Button("Edit");
    editButton.setPrefSize(100, 20);
    editButton.setPrefHeight(Double.MAX_VALUE);
    editButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");

    deleteButton = new Button("Delete");
    deleteButton.setPrefSize(100, 20);
    deleteButton.setPrefHeight(Double.MAX_VALUE);
    deleteButton.setStyle(
      "-fx-background-color: #DAE5EA; -fx-border-width: 0;"
    );

    try {
      this.setContactDefault();
    } catch (FileNotFoundException e) {}

    this.getChildren().addAll(viewButton, editButton, deleteButton);
  }

  public void setContactDefault() throws FileNotFoundException {
    this.contactName.setText("New Contact");
    firstName = null;
    lastName = null;
    profileImage = new Image(new FileInputStream("Unknown.jpeg"));
    imageView.setImage(profileImage);
    imageView.setFitHeight(50);
    imageView.setPreserveRatio(true);
  }

  public Label getContactName() {
    return this.contactName;
  }

  public Button getEditButton() {
    return this.editButton;
  }

  public Button getViewButton() {
    return this.viewButton;
  }

  public Button getDeleteButton() {
    return this.deleteButton;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public String getEmail() {
    return this.email;
  }

  public Image getProfileImage() {
    return this.profileImage;
  }

  public void setFirstName(String name) {
    this.firstName = name;

    if (this.getFirstName() == null) {
      if (this.getLastName() == null) {
        contactName.setText("New Contact");
      } else {
        contactName.setText(lastName);
      }
    } else {
      if (this.getLastName() == null) {
        contactName.setText(firstName);
      } else {
        contactName.setText(firstName + " " + lastName);
      }
    }
  }

  public void setLastName(String name) {
    this.lastName = name;

    if (this.getFirstName() == null) {
      if (this.getLastName() == null) {
        contactName.setText("New Contact");
      } else {
        contactName.setText(lastName);
      }
    } else {
      if (this.getLastName() == null) {
        contactName.setText(firstName);
      } else {
        contactName.setText(firstName + " " + lastName);
      }
    }
  }

  public void setPhoneNumber(String num) {
    this.phoneNumber = num;
  }

  public void setEmail(String em) {
    this.email = em;
  }

  public void setProfileImage(Image i) {
    this.profileImage = i;

    imageView.setImage(profileImage);
    imageView.setFitHeight(50);
    imageView.setPreserveRatio(true);
  }

  @Override
  public int compareTo(Contact contact) {
    return this.contactName.getText().compareTo(contact.contactName.getText());
  }
}
