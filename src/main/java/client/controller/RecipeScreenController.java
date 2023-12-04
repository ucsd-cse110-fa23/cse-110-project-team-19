package client.controller;

import client.View;
import client.model.ATranscribe;
import client.model.IRecipeDetails;
import client.model.Model;
import client.model.RecipeDetails;
import client.model.RecipeImage;
import client.model.Transcribe;
import client.view.MainMenu.MainMenu;
import client.view.MainMenu.Recipe;
import client.view.RecipeScreen.DetailedRecipeView;
import client.view.RecipeScreen.RecipeScreen;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class RecipeScreenController {

  private RecipeScreen recipeScreen;
  private IRecipeDetails recipeDetails;
  private View view;
  private MainMenu mainMenu;
  private Recipe recipe;
  private Model model;
  private String recString;
  private String recipeurl;
  private RecipeImage recipeImage;

  TextArea prompt = new TextArea();
  private ATranscribe transcriber = new Transcribe();

  public RecipeScreenController(
    View view, 
    RecipeScreen recipeScreen, 
    MainMenu mainMenu, 
    Model model,
    Recipe recipe
    
    ) {

    this.recipeScreen = recipeScreen;
    this.recipeDetails = recipeScreen.getRecipeDetails();
    this.view = view;
    this.model = model;
    this.mainMenu = mainMenu;
    this.recipe = recipe;
    this.recipeImage = recipeScreen.getRecipeImage();
    
    this.recipeScreen.setSaveButtonAction(this::handleSaveButton);

    this.recipeScreen.setDeleteButtonAction(this::handleDeleteButton);

    this.recipeScreen.setEditButtonAction(this::handleEditButton);

    this.recipeScreen.setRegenButtonAction(this::handleRegenerateButton);

    this.recipeScreen.setbackButtonAction(this::handlebackButton);

    this.recipeScreen.setShareButtonAction(this::handleShareButton);
  }

  public void handleRegenerateButton(ActionEvent event) {
    String ingredients = "error";
    try {
      ingredients = transcriber.transcribe();

      DetailedRecipeView detailedRecipeView =
        ((RecipeScreen) view.getRoot("recipe")).getDetailedRecipeView();

      ((RecipeScreen) view.getRoot("recipe")).getRecipeDetails()
        .newRecipe(view.getMealType(), ingredients);

      detailedRecipeView.setText(
        ((RecipeScreen) view.getRoot("recipe")).getRecipeDetails().getRecipe()
      );
    } catch (Exception exception) {}
    Recipe recipe = new Recipe(view);
    recipe.setRecipe(
      ((RecipeScreen) view.getRoot("recipe")).getRecipeDetails().getRecipe()
    );
    view.recipeScreen.setRecipe(recipe);
    view.recipeScreen.getFooter().switchToCreating();
    view.setRoot("recipe");
  }

  private void handleSaveButton(ActionEvent event) {
    recipe = new Recipe(view);
    recipe.setRecipe(recipeDetails.getRecipe());
    recipeurl = recipeImage.getURL();
    recipe.setImageURL(recipeurl);
    // doesn't correctly store recipe name
    recipe.getRecipeName().setText(recipeDetails.getRecipeName());
    mainMenu.getRecipeList().getChildren().add(recipe);

    if (recString == null) {
      recString = recipeDetails.getRecipe();
    }
    model.performRequest("POST", view.getUsername(), recString, null);
    view.setRoot("main");
  }

  private void handleDeleteButton(ActionEvent event) {
    Stage addStage = new Stage();
    addStage.setTitle("Delete confirmation");
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(50, 10, 50, 10));
    addStage.setScene(new Scene(grid, 400, 150));
    addStage.setResizable(false);
    addStage.show();

    Label prompt = new Label("Are you sure you want to delete this recipe?");

    prompt.setStyle(
      "-fx-border-width: 0; -fx-font-weight: bold; -fx-font-size: 15px"
    );
    Button confirmButton = new Button("Delete");
    confirmButton.setFocusTraversable(false);
    Button cancelButton = new Button("Cancel");
    cancelButton.setFocusTraversable(false);
    grid.add(prompt, 5, 0);
    HBox buttonBox = new HBox(10);
    buttonBox.getChildren().addAll(confirmButton, cancelButton);
    grid.add(buttonBox, 5, 3);

    buttonBox.setAlignment(Pos.CENTER);
    cancelButton.setOnAction(e1 -> {
      addStage.close();
    });
    confirmButton.setOnAction(e2 -> {
      // delete recipe in main menu
      mainMenu.getRecipeList().getChildren().remove(view.recipeScreen.recipe);
      addStage.close();
      view.setRoot("main");
      addStage.close();
      String name = view.recipeScreen.recipe.getRecipeName().getText();
      name = name.replaceAll(" ", "_");
      String username = view.getUsername();
      model.performRequest("DELETE",  null,null, username + "~" + name);
    });
  }

  private void handleEditButton(ActionEvent event) {
    Stage addStage = new Stage();
    addStage.setTitle("Edit Recipe");
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(5, 5, 5, 5));
    addStage.setScene(new Scene(grid, 400, 500));
    addStage.setResizable(false);
    addStage.show();

    String recipeContent = view.recipeScreen.recipe.getRecipe();
    String title = recipeContent.substring(0, recipeContent.indexOf('\n'));
    Label recipeTitle = new Label(title);
    TextArea prompt = new TextArea();
    prompt.setText(recipeContent.substring(recipeContent.indexOf('\n') + 1));
    prompt.setMinSize(350, 425);

    Button saveButton = new Button("Save");
    saveButton.setFocusTraversable(false);
    grid.add(recipeTitle, 1, 0);
    grid.add(prompt, 1, 2);
    HBox buttonBox = new HBox(4);
    buttonBox.getChildren().addAll(saveButton);
    grid.add(buttonBox, 1, 3);

    buttonBox.setAlignment(Pos.CENTER);
    saveButton.setOnAction(e1 -> {
      String recString = title + "\n" + prompt.getText();
      prompt.setText(recString);
      DetailedRecipeView detailedRecipeView =
        ((RecipeScreen) view.getRoot("recipe")).getDetailedRecipeView();
      detailedRecipeView.setText(recString);
      if (recipe == null) {
        recipe = new Recipe(view);
      }
      recipeDetails.setRecipe(recString);
      recipe.setRecipe(recString);
      view.recipeScreen.setRecipe(recipe);

      model.performRequest("PUT", view.getUsername(), recString, null);

      view.setRoot("recipe");
      addStage.close();
    });
  }

  private void handlebackButton(ActionEvent event) {
    view.setRoot("main");
  }


  private void handleShareButton(ActionEvent event) {
    String link = "localhost:8100/recipeName?=RECIPEID" ;
    Stage addStage = new Stage();
    addStage.setTitle("Share Recipe");
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    addStage.setScene(new Scene(grid, 400, 150));
    addStage.setResizable(false);
    addStage.show();

    Label prompt = new Label("Share your Recipe! \n" + link );

    prompt.setStyle("-fx-border-width: 0; -fx-font-weight: bold; -fx-font-size: 15px");
    prompt.setTextAlignment(TextAlignment.CENTER);
    Button copyButton = new Button("Copy Link");
    copyButton.setFocusTraversable(false);
    grid.add(prompt, 0, 2);
    HBox buttonBox = new HBox(10);
    buttonBox.getChildren().addAll(copyButton);
    grid.add(buttonBox, 3, 6);

    buttonBox.setAlignment(Pos.CENTER);
    copyButton.setOnAction(e1 -> {
      // Copy to clipboard
      Clipboard clipboard = Clipboard.getSystemClipboard();
      ClipboardContent content = new ClipboardContent();
      content.putString(link);
      clipboard.setContent(content);
      addStage.close();
      view.setRoot("recipe");

      
    });
  }
}
