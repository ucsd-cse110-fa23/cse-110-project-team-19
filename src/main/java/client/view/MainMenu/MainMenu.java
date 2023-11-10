package client.view.MainMenu;

import client.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

public class MainMenu extends BorderPane {

  private Header header;
  private Footer footer;
  private RecipeList recipeList;

  private Button createButton;

  public MainMenu() {
    header = new Header();
    recipeList = new RecipeList();
    footer = new Footer();

    ScrollPane scrollPane = new ScrollPane(recipeList);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);

    // Add header to the top of the BorderPane
    this.setTop(header);
    // Add scroller to the centre of the BorderPane
    this.setCenter(scrollPane);
    // Add footer to the bottom of the BorderPane
    this.setBottom(footer);

    createButton = footer.getCreateButton();
  }

  public Button getCreateButton() {
    return createButton;
  }

  public void setCreateButtonAction(EventHandler<ActionEvent> e) {
    createButton.setOnAction(e);
  }

  public void createRecipe(String str) {
    Recipe recipe = new Recipe();
    recipe.getRecipeName().setText(str);
    recipeList.getChildren().add(recipe);
  }
}
