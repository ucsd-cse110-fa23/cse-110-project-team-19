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

  String mealType;
  String imageURL;

  public RecipeDetailsMock(){
    mealType = "";
    imageURL = "";
  }

  public String getMealType(){
    return mealType;
  }

  public void newRecipe(String mealType, String ingredients)
    throws IOException, InterruptedException, URISyntaxException {
    
    this.mealType = mealType;

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

  public void setRecipe(String recipe) {}

  public void generateImage(){
    imageURL = "https://oaidalleapiprodscus.blob.core.windows.net/private/org-a0XEdwytLN2S7LV0Ejs0h3L0/user-Q5SZrx0CjLVuTnfup7gtqtxA/img-QJs4TgnuQTnlbZr9bfDAdROz.png?st=2023-12-05T21%3A23%3A19Z&se=2023-12-05T23%3A23%3A19Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-12-05T19%3A56%3A58Z&ske=2023-12-06T19%3A56%3A58Z&sks=b&skv=2021-08-06&sig=Lo/lRA89yTjgnJVztEkV1S%2Bqxa3YJmUm6ahUw0z34VM%3D";
  }

  public String getImageURL(){
    return imageURL;
  }
}
