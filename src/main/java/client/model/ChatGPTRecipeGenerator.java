package client.model;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.json.JSONArray;
import org.json.JSONObject;

class ChatGPTRecipeGenerator extends VBox {

  //this entire class should be part of Model
  private static final String API_ENDPOINT =
    "https://api.openai.com/v1/completions";
  private static final String API_KEY =
    "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";
  private static final String MODEL = "text-davinci-003";

  ChatGPTRecipeGenerator() {}

  public static String generateNewRecipe(String mealType, String ingredients)
    throws IOException, InterruptedException, URISyntaxException {
    String prompt =
      "Can you create me a recipe for " +
      mealType +
      " with these ingredients " +
      ingredients;
    int maxTokens = 100;
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
    return choices.getJSONObject(0).getString("text").trim();
  }

  public static String getTitleOfString(String str) {
    return str.substring(0, str.indexOf("\n"));
  }

  public static String getFakeRecipe() {
    return (
      "\n\n" +
      "Tomato Toasts with Eggs\n" +
      "\n" +
      "Ingredients:\n" +
      "2 thick slices white or whole wheat bread\n" +
      "2 slices tomato\n" +
      "2 teaspoons butter\n" +
      "2 eggs\n" +
      "Salt and ground black pepper, to taste\n" +
      "\n" +
      "Instructions\n" +
      "\n" +
      "1. Preheat a skillet over medium heat.\n" +
      "\n" +
      "2. Toast bread slices until they are golden and crisp.\n" +
      "\n" +
      "3. Spread one teaspoon of butter onto each slice of toast.\n" +
      "\n" +
      "4. Place tomato slices onto the toasts.\n"
    );
  }
}
