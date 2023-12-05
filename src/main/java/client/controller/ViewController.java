package client.controller;

import client.View;
import client.model.Model;
import client.view.MainMenu.Recipe;
import java.io.BufferedReader;
import java.io.FileReader;
import javafx.scene.Scene;

public class ViewController {

  private View view;

  public ViewController(View view) {
    this.view = view;
  }

  public String viewStart() {
    try {
      FileReader fr = new FileReader("automaticLogin.txt");
      BufferedReader br = new BufferedReader(fr);
      String line = br.readLine();
      if (line.equals("false")) {
        br.close();
        return "accountScreen";
      } else if (line.equals("true")) {
        view.setUsername(br.readLine());
        String query = view.getUsername();
        Model model = new Model();
        String response = model.performRequest("GET", null, null, query);
        if (response != null) {
          String[] recipes = response.split("~");
          for (String recipeContent : recipes) {
            Recipe recipe = new Recipe(view);
            recipe.setRecipe(recipeContent);

            String recipeName = recipeContent.substring(
              0,
              recipeContent.indexOf('\n')
            );

            recipe.getRecipeName().setText(recipeName);
            view.mainMenu.getRecipeList().getChildren().add(recipe);
            new RecipeScreenController(
              view,
              view.recipeScreen,
              view.mainMenu,
              model,
              recipe
            );
          }
        }
      }
      br.close();
      return "main";
    } catch (Exception e) {}
    return "";
  }
}
