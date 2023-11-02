package Contact;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

class ViewFrame extends BorderPane {

  private ContactHeader header;
  private ViewFooter footer;

  private ViewContactBox newContactBox;

  private ScrollPane scrollPane;
  private Button closeButton;

  ViewFrame(Contact contact, Stage stage) {
    header = new ContactHeader(contact);
    footer = new ViewFooter();

    newContactBox = new ViewContactBox(contact, stage);

    scrollPane = new ScrollPane();
    scrollPane.setContent(newContactBox);
    scrollPane.setFitToHeight(true);
    scrollPane.setFitToWidth(true);

    this.setTop(header);
    this.setBottom(footer);

    this.setCenter(scrollPane);
    closeButton = footer.getCloseButton();
  }

  public Button getCloseButton() {
    return this.closeButton;
  }
}
