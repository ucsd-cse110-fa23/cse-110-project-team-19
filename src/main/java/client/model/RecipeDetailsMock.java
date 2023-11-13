package client.model;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.scene.control.Label;
import javafx.scene.control.Label;
import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeDetailsMock implements IRecipeDetails {

  public JSONObject requestBody;
  public String recipe;
  private static final String MODEL = "text-davinci-003";

  public void newRecipe(String mealType, String ingredients)
    throws IOException, InterruptedException, URISyntaxException {
    String prompt =
      "Can you create me a recipe for " +
      mealType +
      " with these ingredients " +
      ingredients;
    int maxTokens = 100;
    // Create a request body which you will pass into request object
    requestBody = new JSONObject();
    requestBody.put("model", MODEL);
    requestBody.put("prompt", prompt);
    requestBody.put("max_tokens", maxTokens);
    requestBody.put("temperature", 1.0);

    recipe = "ChatGPT\nmocked recipe";
    //System.out.println(requestBody.toString());
  }

  // could be changed in the future, this is duplicate code from RecipeDetails
  public String getRecipe() {
    if (recipe == null) {
      return null;
    }
    return recipe.replaceAll("(?m)^[ \t]*\r?\n", "");
  }

  public String getRecipeName() {
    String recipeName;
    recipeName = recipe.replaceAll("(?m)^[ \t]*\r?\n", "");
    return recipeName.substring(0, recipeName.indexOf("\n"));
  }
}
