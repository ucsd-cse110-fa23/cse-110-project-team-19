package client.GUI.RecipeScreen;

import client.GUI.MainMenu.MainMenu;
import client.View;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class RecipeScreen extends BorderPane {

  private Header header;
  private Footer footer;

  private HashMap<String, RecipeDetails> recipesInMemory;
  private String currRecipe;

  private Button saveButton;
  private Button closeButton;

  public RecipeScreen(View view) {
    header = new Header();
    recipesInMemory = new HashMap<>();
    footer = new Footer();

    // Add header to the top of the BorderPane
    this.setTop(header);
    // Add scroller to the centre of the BorderPane
    // currently empty center
    // Add footer to the bottom of the BorderPane
    this.setBottom(footer);

    saveButton = footer.getSaveButton();
    saveButton.setOnAction(e -> {
      ((MainMenu) view.getRoot("main")).createRecipe(currRecipe);
      view.setRoot("main");
    });

    closeButton = footer.getCloseButton();
    closeButton.setOnAction(e -> {
      footer.switchToCreating();
      view.setRoot("main");
    });
  }

  public void putRecipeDetail(String title, String details){
    currRecipe = title;
    RecipeDetails recipeDetails = new RecipeDetails(title, details);
    this.setCenter(recipeDetails);
    recipesInMemory.put(title, recipeDetails);
  }

  public void setRecipeDetail(String title){
    this.setCenter(recipesInMemory.get(title));
  }

  public void switchToViewing(){
    footer.switchToViewing();
  }
}
