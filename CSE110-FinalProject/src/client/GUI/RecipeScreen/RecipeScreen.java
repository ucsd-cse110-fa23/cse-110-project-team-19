package client.GUI.RecipeScreen;

import client.View;
import client.GUI.MainMenu.MainMenu;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class RecipeScreen extends BorderPane{
    
    private Header header;
    private Footer footer;
    private RecipeDetails recipeDetails;

    private Button createButton;

    public RecipeScreen(View view)
    {
        header = new Header();
        recipeDetails = new RecipeDetails();
        footer = new Footer();

        // Add header to the top of the BorderPane
        this.setTop(header);
        // Add scroller to the centre of the BorderPane
        this.setCenter(recipeDetails);
        // Add footer to the bottom of the BorderPane
        this.setBottom(footer);

        createButton = footer.getSaveButton();
        createButton.setOnAction(e -> {
            ((MainMenu) view.getRoot("main")).createRecipe(recipeDetails.toString());
            view.setRoot("main");
        });
    }

    public void generateRecipe(String mealType, String ingredients){
        recipeDetails.newRecipe();
    }
}
