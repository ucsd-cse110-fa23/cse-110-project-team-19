package client.model;

import java.util.HashMap;
import java.util.Map;

public class MockLoginModel {

  Map<String, String> userPass;

  public MockLoginModel(){
    userPass = new HashMap<>();
  }
  
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
      String urlString = "http://localhost:8100/login";
      if (query != null) {
        urlString += "?=" + query;
      }
      
      String response = "";
      if (method.equals("POST") || method.equals("PUT")) {
        userPass.put(username, password);
        response = "put username: " + username + " and password:" + password + " into server";
      }

      return response;
    } catch (Exception ex) {
      ex.printStackTrace();
      return "Error: " + ex.getMessage();
    }
  }

  public Map<String, String> getServerData(){
    return userPass;
  }
}
