package client.view.MainMenu;

import client.View;
import client.view.RecipeScreen.RecipeScreen;
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
  private Button sortButton;
  private Button filterButton;
  private Button logOutButton;
  private Button sortButton;
  private Button filterButton;

  public MainMenu() {
    header = new Header();
    footer = new Footer();

    recipeList = new RecipeList();

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
    sortButton = header.getsortButton();
    filterButton = header.getfilterButton();
    logOutButton = footer.getLogOutButton();
    sortButton = header.getsortButton();
    filterButton = header.getfilterButton();
  }

  public Button getCreateButton() {
    return createButton;
  }

  public Button getLogOutButton() {
    return logOutButton;
  }

  public Button getsortButton() {
    return sortButton;
  }

  public Button getfilterButton() {
    return filterButton;
  }

  public void setCreateButtonAction(EventHandler<ActionEvent> e) {
    createButton.setOnAction(e);
  }

  public void setsortButtonAction(EventHandler<ActionEvent> e) {
    sortButton.setOnAction(e);
  }

  public void setfilterButtonAction(EventHandler<ActionEvent> e) {
    filterButton.setOnAction(e);
  }

  public void setLogOutButtonAction(EventHandler<ActionEvent> e) {
    logOutButton.setOnAction(e);
  }

  public void setsortButtonAction(EventHandler<ActionEvent> e) {
    sortButton.setOnAction(e);
  }

  public void setfilterButtonAction(EventHandler<ActionEvent> e) {
    filterButton.setOnAction(e);
  }

  public RecipeList getRecipeList() {
    return this.recipeList;
  }

  public void clearRecipeList() {
    this.recipeList.getChildren().clear();
  }
}
