package client.GUI.RecipeScreen;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

class RecipeDetails extends VBox {

    private final String[] testTitles;

    private String str;
    private Label text;

    RecipeDetails() {
        testTitles = new String[5];
        testTitles[0] = "Fried Rice";
        testTitles[1] = "Braised Pork";
        testTitles[2] = "Stir-fried Cabbage";
        testTitles[3] = "Wonton Soup";
        testTitles[4] = "Chicken";

        this.setSpacing(5); // sets spacing between tasks
        this.setPrefSize(500, 560);
        this.setStyle("-fx-background-color: #F0F8FF;");

        text = new Label("");
        text.setPrefSize(500, 400); 
        //text.setPadding(new Insets(5, 0, 0, 10));
        text.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of label
        //text.setTextAlignment(TextAlignment.CENTER); // set alignment of label
        text.setWrapText(true);
        this.getChildren().add(text); // add text to recipe details
    }

    //TODO:
    public void newRecipe(){
        //TODO: generate text with Chat-GPT
        str = "Random: " + testTitles[(int)(System.currentTimeMillis() % testTitles.length)] + "\n\n";
        str +=
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
            "sed do eiusmod tempor incididunt ut labore et dolore magna " + 
            "aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco " + 
            "laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure " + 
            "dolor in reprehenderit in voluptate velit esse cillum dolore eu " + 
            "fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, " + 
            "sunt in culpa qui officia deserunt mollit anim id est laborum.";
        
        text.setText(str);
    }

    public String toString(){
        return str.substring(0, str.indexOf("\n"));
    }
}
