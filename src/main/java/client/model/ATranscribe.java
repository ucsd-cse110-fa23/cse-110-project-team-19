package client.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ATranscribe {
    
    // FIELDS
    private static final String API_ENDPOINT =
    "https://api.openai.com/v1/audio/transcriptions";
    private static final String TOKEN =
    "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";
    private static final String MODEL = "whisper-1";
    private static final String FILE_PATH = "ingredients.wav";

    // CONCRETE METHODS
    public static String getEndpoint() {
    return API_ENDPOINT;
    }

    public static String getToken() {
    return TOKEN;
    }

    public static String getModel() {
    return MODEL;
    }

    public static String getFilePath() {
    return FILE_PATH;
    }

    // Helper method to write a parameter to the output stream in multipart form data format
    public static void writeParameterToOutputStream(
        OutputStream outputStream,
        String parameterName,
        String parameterValue,
        String boundary
    ) throws IOException {
    outputStream.write(("--" + boundary + "\r\n").getBytes());
    outputStream.write(
        (
        "Content-Disposition: form-data; name=\"" + parameterName + "\"\r\n\r\n"
        ).getBytes()
    );
    outputStream.write((parameterValue + "\r\n").getBytes());
    }

  // Helper method to write a file to the output stream in multipart form data format
  public static void writeFileToOutputStream(
    OutputStream outputStream,
    File file,
    String boundary
  ) throws IOException {
    outputStream.write(("--" + boundary + "\r\n").getBytes());
    outputStream.write(
      (
        "Content-Disposition: form-data; name=\"file\"; filename=\"" +
        file.getName() +
        "\"\r\n"
      ).getBytes()
    );
    outputStream.write(("Content-Type: audio/mpeg\r\n\r\n").getBytes());

    FileInputStream fileInputStream = new FileInputStream(file);
    byte[] buffer = new byte[1024];
    int bytesRead;
    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, bytesRead);
    }
    fileInputStream.close();
  }

  // Helper method to handle a successful response
  public static String handleSuccessResponse(HttpURLConnection connection)
    throws IOException, JSONException {
    BufferedReader in = new BufferedReader(
      new InputStreamReader(connection.getInputStream())
    );
    String inputLine;
    StringBuilder response = new StringBuilder();
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();

    JSONObject responseJson = new JSONObject(response.toString());

    String generatedText = responseJson.getString("text");

    // Print the transcription result
    return generatedText;
  }

  // Helper method to handle an error response
  public static void handleErrorResponse(HttpURLConnection connection)
    throws IOException, JSONException {
    BufferedReader errorReader = new BufferedReader(
      new InputStreamReader(connection.getErrorStream())
    );
    String errorLine;
    StringBuilder errorResponse = new StringBuilder();
    while ((errorLine = errorReader.readLine()) != null) {
      errorResponse.append(errorLine);
    }
    errorReader.close();
    String errorResult = errorResponse.toString();
    System.out.println("Error Result: " + errorResult);
  }

  public static String checkMealType(String input) {
    input = input.replaceAll("\\p{Punct}", "");
    if (input.toLowerCase().equals("breakfast")) {
      return "breakfast";
    } else if (input.toLowerCase().equals("lunch")) {
      return "lunch";
    } else if (input.toLowerCase().equals("dinner")) {
      return "dinner";
    }
    return null;
  }

// ABSTRACT METHODS
  public abstract String transcribe() throws IOException, URISyntaxException;
}
