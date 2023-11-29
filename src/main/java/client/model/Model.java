package client.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.charset.StandardCharsets;

public class Model {

  // takes in method, name, recipe, and query
  // returns a response
  public String performRequest(
    String method,
    String account,
    String recipe,
    String query
  ) {
    // Implement your HTTP request logic here and return the response
    try {
      String urlString = "http://localhost:8100/";
      if (query != null) {
        urlString += "?=" + query;
      }
      URL url = new URI(urlString).toURL();
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod(method);
      conn.setDoOutput(true);

      if (method.equals("POST") || method.equals("PUT")) {
        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
        out.write(account + "," + recipe);
        out.flush();
        out.close();
      }

      BufferedReader in = new BufferedReader(
        new InputStreamReader(conn.getInputStream())
      );
      String response = in.readLine();
      while (in.ready()) {
        response += '\n' + in.readLine();
      }
      in.close();
      if (response != null) {
        response =
          URLDecoder.decode(response, StandardCharsets.UTF_8.toString());
      }
      return response;
    } catch (Exception ex) {
      ex.printStackTrace();
      return "Error: " + ex.getMessage();
    }
  }
}
