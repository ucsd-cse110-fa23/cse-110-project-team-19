package client.pantryPal;

import java.io.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

class AppFrame extends BorderPane {

  private Header header;
  private Footer footer;
  private ContactList contactlist;
  private ScrollPane scrollPane;

  private Button newButton;
  private Button saveButton;
  private Button sortButton;

  private Button closeButton;

  private Button saveContactButton;
  private Button cancelButton;

  AppFrame(Stage primaryStage) throws FileNotFoundException {
    header = new Header();

    contactlist = new ContactList();

    footer = new Footer();

    scrollPane = new ScrollPane();
    scrollPane.setContent(contactlist);
    scrollPane.setFitToHeight(true);
    scrollPane.setFitToWidth(true);

    this.setTop(header);
    this.setCenter(scrollPane);
    this.setBottom(footer);

    newButton = footer.getNewButton();
    saveButton = footer.getSaveButton();
    sortButton = footer.getSortButton();

    addListeners(primaryStage);
  }

  public void addListeners(Stage primaryStage) throws FileNotFoundException {
    newButton.setOnAction(e -> {
      Contact contact = new Contact();
      ContactFrame root = new ContactFrame(contact, primaryStage);
      Stage stage = new Stage();
      stage.setTitle("Edit Contact");
      stage.setScene(new Scene(root, 450, 450));
      stage.setResizable(false);
      stage.show();
      saveContactButton = root.getSaveButton();
      cancelButton = root.getCancelButton();
      saveContactButton.setOnAction(event -> {
        root.saveActions(contact);
        contactlist.getChildren().add(contact);
        stage.close();
      });
      cancelButton.setOnAction(event -> {
        stage.close();
      });
      Button editButton = contact.getEditButton();
      Button viewButton = contact.getViewButton();
      Button deleteButton = contact.getDeleteButton();

      editButton.setOnAction(event -> {
        ContactFrame editRoot = new ContactFrame(contact, primaryStage);
        Stage editStage = new Stage();
        editStage.setTitle("Edit Contact");
        editStage.setScene(new Scene(editRoot, 450, 450));
        editStage.setResizable(false);
        editStage.show();
        Button editSaveButton = editRoot.getSaveButton();
        Button editCancelButton = editRoot.getCancelButton();
        editSaveButton.setOnAction(saveEvent -> {
          editRoot.saveActions(contact);
          editStage.close();
        });
        editCancelButton.setOnAction(cancelEvent -> {
          editStage.close();
        });
      });

      viewButton.setOnAction(event -> {
        ViewFrame viewRoot = new ViewFrame(contact, primaryStage);
        Stage viewStage = new Stage();
        viewStage.setTitle(contact.getContactName().getText());
        viewStage.setScene(new Scene(viewRoot, 450, 450));
        viewStage.setResizable(false);
        viewStage.show();
        closeButton = viewRoot.getCloseButton();
        closeButton.setOnAction(closeEvent -> {
          viewStage.close();
        });
      });

      deleteButton.setOnAction(event -> {
        contactlist.getChildren().remove(contact);
      });
    });

    saveButton.setOnAction(e -> {
      contactlist.saveTasks();
    });

    sortButton.setOnAction(e -> {
      contactlist.sortContacts();
    });
  }
}
