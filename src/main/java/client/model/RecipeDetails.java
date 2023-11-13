package client.model;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeDetails implements IRecipeDetails {

  private String recipe;

  private static final String API_ENDPOINT =
    "https://api.openai.com/v1/completions";
  private static final String API_KEY =
    "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";
  private static final String MODEL = "text-davinci-003";

  public void newRecipe(String mealType, String ingredients)
    throws IOException, InterruptedException, URISyntaxException {
    String prompt =
      "Can you create me a recipe for " +
      mealType +
      " with these ingredients " +
      ingredients +
      " Give me the recipe name on the first line and the recipe with each step on a new line.";

    int maxTokens = 300;
    // Create a request body which you will pass into request object
    JSONObject requestBody = new JSONObject();
    requestBody.put("model", MODEL);
    requestBody.put("prompt", prompt);
    requestBody.put("max_tokens", maxTokens);
    requestBody.put("temperature", 1.0);
    // Create the HTTP Client

    HttpClient client = HttpClient.newHttpClient();

    // Create the request object
    HttpRequest request = HttpRequest
      .newBuilder()
      .uri(URI.create(API_ENDPOINT))
      .header("Content-Type", "application/json")
      .header("Authorization", String.format("Bearer %s", API_KEY))
      .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
      .build();

    // Send the request and receive the response
    HttpResponse<String> response = client.send(
      request,
      HttpResponse.BodyHandlers.ofString()
    );

    // Process the response
    String responseBody = response.body();

    JSONObject responseJson = new JSONObject(responseBody);

    JSONArray choices = responseJson.getJSONArray("choices");
    recipe = choices.getJSONObject(0).getString("text");
  }

  public void setRecipe(String recipe) {
    this.recipe = recipe;
  }

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
