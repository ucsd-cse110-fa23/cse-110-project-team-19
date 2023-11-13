package client.model;

import static client.model.ATranscribe.handleErrorResponse;
import static client.model.ATranscribe.handleSuccessResponse;
import static client.model.ATranscribe.writeFileToOutputStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class Transcribe extends ATranscribe {

    // FIELDS
    private static final String API_ENDPOINT = getEndpoint();
    private static final String TOKEN = getToken();
    private static final String MODEL = getModel();
    private static final String FILE_PATH = getFilePath();

  public String transcribe() throws IOException, URISyntaxException {
    // Create file object from file path
    File file = new File(FILE_PATH);

    // Set up HTTP connection
    URL url = new URI(API_ENDPOINT).toURL();
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setDoOutput(true);

    // Set up request headers
    String boundary = "Boundary-" + System.currentTimeMillis();
    connection.setRequestProperty(
      "Content-Type",
      "multipart/form-data; boundary=" + boundary
    );
    connection.setRequestProperty("Authorization", "Bearer " + TOKEN);

    // Set up output stream to write request body
    OutputStream outputStream = connection.getOutputStream();

    // Write model parameter to request body
    writeParameterToOutputStream(outputStream, "model", MODEL, boundary);

    // Write file parameter to request body
    writeFileToOutputStream(outputStream, file, boundary);

    // Write closing boundary to request body
    outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());

    // Flush and close output stream
    outputStream.flush();
    outputStream.close();

    // Get response code
    int responseCode = connection.getResponseCode();
    String rString = "Error";

    // Check response code and handle response accordingly
    if (responseCode == HttpURLConnection.HTTP_OK) {
      rString = handleSuccessResponse(connection);
    } else {
      handleErrorResponse(connection);
    }

    // Disconnect connection
    connection.disconnect();
    return rString;
  }
    
}
