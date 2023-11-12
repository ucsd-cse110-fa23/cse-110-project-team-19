package client.view.RecipeScreen;

import client.model.RecipeDetails;
import client.View;
import javafx.scene.control.Label;
import javafx.scene.layout.*;


public class DetailedRecipeView extends VBox {

    public Label text;
    private RecipeDetails recipeDetails;

    public DetailedRecipeView() {
        this.setSpacing(5); // sets spacing between tasks
        this.setPrefSize(500, 560);
        this.setStyle("-fx-background-color: #F0F8FF;");

        text = new Label("Recipe");
        text.setPrefSize(500, 400);
        //text.setPadding(new Insets(5, 0, 0, 10));
        text.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of label
        //text.setTextAlignment(TextAlignment.CENTER); // set alignment of label
        text.setWrapText(true);
        this.getChildren().add(text); // add text to recipe details
    }

    public Label getText(){
        return this.text;
    }

    public void setText(String str){  
        this.text.setText(str);
    }

}
