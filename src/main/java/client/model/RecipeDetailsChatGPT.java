package client.model;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//import javafx.geometry.Insets;
import javafx.scene.control.Label;
//import javafx.scene.layout.*;
//import javafx.scene.text.TextAlignment;
import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeDetailsChatGPT {

  private String str;
  private Label text;

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
    str = choices.getJSONObject(0).getString("text");

    text.setText(str);
  }

  public String toString() {
    return str.substring(0, str.indexOf("\n"));
  }
}
