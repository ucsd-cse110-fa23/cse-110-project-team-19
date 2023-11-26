package client.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class CreateAccountModel {

  // takes in method, name, recipe, and query
  // returns a response
  public String performRequest(
    String method,
    String username,
    String password,
    String query
  ) {
    // Implement your HTTP request logic here and return the response
    try {
      String urlString = "http://localhost:8100/create-account";
      if (query != null) {
        urlString += "?=" + query;
      }
      URL url = new URI(urlString).toURL();
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod(method);
      conn.setDoOutput(true);

      if (method.equals("POST") || method.equals("PUT")) {
        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
        out.write(username + "," + password);
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
      return response;
    } catch (Exception ex) {
      ex.printStackTrace();
      return "Error: " + ex.getMessage();
    }
  }
}
