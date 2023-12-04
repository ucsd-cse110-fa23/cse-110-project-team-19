package client.view.RecipeScreen;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class Footer extends HBox {

  private Button editButton;
  private Button saveButton;
  private Button deleteButton;
  private Button regenButton;
  private Button shareButton;

  Footer() {
    this.setPrefSize(500, 60);
    this.setStyle("-fx-background-color: #F0F8FF;");
    this.setSpacing(15);

    // set a default style for buttons - background color, font size, italics
    String defaultButtonStyle =
      "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";

    editButton = new Button("Edit"); // text displayed on edit button
    editButton.setStyle(defaultButtonStyle); // styling the button

    saveButton = new Button("Save"); // text displayed on save button
    saveButton.setStyle(defaultButtonStyle); // styling the button

    deleteButton = new Button("Delete"); // text displayed on delete button
    deleteButton.setStyle(defaultButtonStyle); // styling the button

    regenButton = new Button("Regenerate"); // text displayed on regen button
    regenButton.setStyle(defaultButtonStyle); // styling the button

    shareButton = new Button("Share"); // text displayed on share button
    shareButton.setStyle(defaultButtonStyle); // styling the button

    this.getChildren()
      .addAll(editButton, deleteButton, saveButton, regenButton, shareButton); // adding buttons to footer
    this.setAlignment(Pos.CENTER); // aligning the buttons to center
  }

  public Button getEditButton() {
    return editButton;
  }

  public Button getRegenButton() {
    return regenButton;
  }

  public Button getSaveButton() {
    return saveButton;
  }
  

  public Button getShareButton() {
    return shareButton;
  }

  public Button getDeleteButton() {
    return deleteButton;
  }

  public void switchToViewing() {
    this.getChildren().remove(saveButton);
    this.getChildren().remove(regenButton);
    this.getChildren().remove(shareButton);
    this.getChildren().add(shareButton);
  }

  public void switchToCreating() {
    this.getChildren().remove(saveButton);
    this.getChildren().add(saveButton);
    this.getChildren().remove(regenButton);
    this.getChildren().add(regenButton);
    this.getChildren().remove(shareButton);
  }
}
