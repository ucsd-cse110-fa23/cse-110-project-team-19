package client.view.RecipeScreen;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.geometry.Dimension2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class DetailedRecipeView extends VBox {

  public Label text;
  public ImageView image;

  public DetailedRecipeView() {
    this.setSpacing(5); // sets spacing between tasks
    this.setPrefSize(500, 560);
    this.setStyle("-fx-background-color: #F0F8FF;");
    this.setAlignment(Pos.CENTER);

    image = new ImageView();
    text = new Label("Recipe");
    text.setPrefSize(500, 400);
    //text.setPadding(new Insets(5, 0, 0, 10));
    text.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of label
    //text.setTextAlignment(TextAlignment.CENTER); // set alignment of label
    text.setWrapText(true);
    //image.setImage(new Image("https://oaidalleapiprodscus.blob.core.windows.net/private/org-B0v84x70FYYKq0WRLO8JrLH1/user-TYQQFtmHcLSy3wJMhAD7KDoQ/img-tP6gbqJIMFbePplJZxP3LJ9r.png?st=2023-12-03T06%3A27%3A34Z&se=2023-12-03T08%3A27%3A34Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-12-02T22%3A48%3A34Z&ske=2023-12-03T22%3A48%3A34Z&sks=b&skv=2021-08-06&sig=oZ5iljFs7MdJFgHJSYsZjVuHaUtCtMfyb0m%2BXtAUDd4%3D"));
    
    this.getChildren().addAll(image, text); // add text to recipe details
  }

  public Label getText() {
    return this.text;
  }

  public void setText(String str) {
    this.text.setText(str);
  }

  public ImageView getImage() {
    return this.image;
  }

  public void SI(String str) {
    this.image.setImage(new Image(str));
  }
}
