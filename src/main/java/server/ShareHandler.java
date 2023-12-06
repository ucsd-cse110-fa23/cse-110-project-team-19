package server;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import javafx.scene.control.Button;
import org.bson.Document;
import org.bson.conversions.Bson;

public class ShareHandler implements HttpHandler {

  String mongoURI =
    "mongodb+srv://user:RbrRuGgkHtbSgqai@pantrypal.mz6l5wo.mongodb.net/?retryWrites=true&w=majority";

  public void handle(HttpExchange httpExchange) throws IOException {
    String response = "Request Received";
    String method = httpExchange.getRequestMethod();
    try {
      if (method.equals("GET")) {
        response = handleGet(httpExchange);
      } else {
        throw new Exception("Not Valid Request Method");
      }
    } catch (Exception e) {
      System.out.println("An erroneous request");
      response = e.toString();
      e.printStackTrace();
    }

    OutputStream outputStream = httpExchange.getResponseBody();

    // this line is a must
    httpExchange.sendResponseHeaders(200, response.length());

    outputStream.write(response.getBytes());
    outputStream.flush();
    outputStream.close();
  }

  private String handleGet(HttpExchange httpExchange) {
    String response = "Invalid GET request";
    URI uri = httpExchange.getRequestURI();
    String query = uri.getRawQuery();
    if (query != null) {
      String username = query.substring(
        query.indexOf("=") + 1,
        query.indexOf('~')
      );
      String name = query.substring(query.indexOf("~") + 1);
      String recipe = "";
      String imageURl = "";
      String recipeContent = "";
      try (MongoClient mongoClient = MongoClients.create(mongoURI)) {
        FileReader fr = new FileReader("images/" + name);
        name = name.replaceAll("_", " ");
        MongoDatabase accountDB = mongoClient.getDatabase("account_db");
        MongoCollection<Document> recipesCollection = accountDB.getCollection(
          "recipes"
        );
        Bson filter = and(eq("title", name), eq("account", username));
        Document recipeDoc = recipesCollection.find(filter).first();
        recipe = recipeDoc.get("recipe") + "";
        BufferedReader br = new BufferedReader(fr);
        imageURl = br.readLine();
        br.close();
        recipeContent = recipe.substring(recipe.indexOf("\n") + 1);
        recipeContent = recipeContent.replaceAll("[^a-zA-Z0-9]", " ");
      } catch (Exception e) {}

      StringBuilder htmlBuilder = new StringBuilder();
      htmlBuilder
        .append("<html>")
        .append("<body>")
        .append("<h1>")
        .append(name)
        .append("</h1>")
        .append("<img src=\"" + imageURl + "\">")
        .append("</img>")
        .append("<p>")
        .append(recipeContent)
        .append("</p>")
        .append("</body>")
        .append("</html>");
      // encode HTML content
      response = htmlBuilder.toString();
    }
    return response;
  }
}
