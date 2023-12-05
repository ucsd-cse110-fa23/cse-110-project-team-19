package client.view.ServerScreen;



import client.View;
import client.view.ServerScreen.ServerError;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ServerStatus extends BorderPane {
    
    private ServerError serverError;

    public ServerStatus() {
        serverError = new ServerError();
        serverError.setPrompt("ERROR 404");
        // Add scroller to the centre of the BorderPane
        this.setCenter(serverError);
  }
}

