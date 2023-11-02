package client.pantryPal;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.*;

class ContactHeader extends HBox {

  ContactHeader(Contact contact) {
    this.setPrefSize(500, 60);
    this.setStyle("-fx-background-color: #F0F8FF;");
    Text titleText;
    if (contact.getFirstName() == null) {
      if (contact.getLastName() == null) {
        titleText = new Text("New Contact");
      } else {
        titleText = new Text(contact.getLastName());
      }
    } else {
      if (contact.getLastName() == null) {
        titleText = new Text(contact.getFirstName());
      } else {
        titleText =
          new Text(contact.getFirstName() + " " + contact.getLastName());
      }
    }
    titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
    this.getChildren().add(titleText);
    this.setAlignment(Pos.CENTER);
  }
}
