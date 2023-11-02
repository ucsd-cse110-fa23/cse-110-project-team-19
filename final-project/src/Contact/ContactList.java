package Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.Node;
import javafx.scene.layout.*;

class ContactList extends VBox {

  ContactList() {
    this.setSpacing(5);
    this.setPrefSize(500, 560);
    this.setStyle("-fx-background-color: #F0F8FF;");
  }

  public void saveTasks() {
    try {
      FileWriter fw = new FileWriter("contacts.csv");
      for (int i = 0; i < this.getChildren().size(); i++) {
        if (this.getChildren().get(i) instanceof Contact) {
          fw.write(
            "Name: " +
            ((Contact) this.getChildren().get(i)).getContactName().getText()
          );
          if (((Contact) this.getChildren().get(i)).getPhoneNumber() != null) {
            fw.write(
              ", Phone Number: " +
              ((Contact) this.getChildren().get(i)).getPhoneNumber()
            );
          }
          if (((Contact) this.getChildren().get(i)).getEmail() != null) {
            fw.write(
              ", Email: " + ((Contact) this.getChildren().get(i)).getEmail()
            );
          }
          fw.write('\n');
        }
      }
      fw.close();
    } catch (Exception e) {}
  }

  public void sortContacts() {
    ArrayList<Contact> contacts = new ArrayList<>();
    for (int i = 0; i < this.getChildren().size(); i++) {
      if (this.getChildren().get(i) instanceof Contact) {
        contacts.add(((Contact) this.getChildren().get(i)));
      }
    }
    Collections.sort(contacts);
    this.getChildren().clear();
    for (int i = 0; i < contacts.size(); i++) {
      this.getChildren().add((Node) contacts.get(i));
    }
  }
}
