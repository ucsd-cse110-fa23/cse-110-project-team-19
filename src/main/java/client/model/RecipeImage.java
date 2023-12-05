package client.model;

import client.model.RecipeDetails;
import client.view.RecipeScreen.RecipeScreen;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeImage {

  // TODO: Set the URL of the API Endpoint
  private static final String API_ENDPOINT =
    "https://api.openai.com/v1/images/generations";
  private static final String API_KEY =
    "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";
  //private static final String API_KEY = "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";
  private static final String MODEL = "dall-e-2";

  private String generatedImageURL;

  public void NewImage(String prompt)
    throws URISyntaxException, IOException, InterruptedException {
    // Set request parameters
    int n = 1;

    // Create a request body which you will pass into request object
    JSONObject requestBody = new JSONObject();
    requestBody.put("model", MODEL);
    requestBody.put("prompt", prompt);
    requestBody.put("n", n);
    requestBody.put("size", "256x256");

    // Create the HTTP client
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

    // TODO: Process the response
    JSONArray data = responseJson.getJSONArray("data");
    generatedImageURL = data.getJSONObject(0).getString("url");
    //    // Download the Generated Image to Current Directory
    //    try(InputStream in = new URI(generatedImageURL).toURL().openStream())
    //    {
    //        Files.copy(in, Paths.get("image.jpg"));
    //    }

    try {
      FileWriter fw = new FileWriter("images/" + prompt.replaceAll(" ", "_"));
      fw.write(generatedImageURL);
      fw.close();
    } catch (Exception e) {}
  }

  public String getURL() {
    return generatedImageURL;
  }
}
