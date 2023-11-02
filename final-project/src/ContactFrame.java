import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

class ContactFrame extends BorderPane {

  private ContactHeader header;
  private ContactFooter footer;
  private Button saveButton;
  private Button cancelButton;

  private NewContactBox newContactBox;

  private ScrollPane scrollPane;

  ContactFrame(Contact contact, Stage stage) {
    header = new ContactHeader(contact);

    newContactBox = new NewContactBox(contact, stage);

    footer = new ContactFooter();

    scrollPane = new ScrollPane();
    scrollPane.setContent(newContactBox);
    scrollPane.setFitToHeight(true);
    scrollPane.setFitToWidth(true);

    this.setTop(header);

    this.setCenter(scrollPane);

    this.setBottom(footer);

    saveButton = footer.getSaveButton();
    cancelButton = footer.getCancelButton();
  }

  public Button getSaveButton() {
    return saveButton;
  }

  public Button getCancelButton() {
    return cancelButton;
  }

  public void saveActions(Contact contact) {
    contact.setFirstName(newContactBox.getFirstName());
    contact.setLastName(newContactBox.getLastName());
    contact.setPhoneNumber(newContactBox.getPhoneNumber());
    contact.setEmail(newContactBox.getEmail());
    contact.setProfileImage(newContactBox.getProfileImage());
  }
}
