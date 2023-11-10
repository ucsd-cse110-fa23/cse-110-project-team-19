package client.view.RecipeScreen;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.scene.control.Label;
import javafx.scene.control.Label;
import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeDetailsMock implements IRecipeDetails {

  public Label text;
  public JSONObject requestBody;
  private static final String MODEL = "text-davinci-003";

  public void newRecipe(String mealType, String ingredients)
    throws IOException, InterruptedException, URISyntaxException {
    // TODO
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

    text.setText("");
    //System.out.println(requestBody.toString());
  }
}
