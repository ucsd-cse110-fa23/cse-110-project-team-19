package server;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.bson.Document;
import org.bson.conversions.Bson;

public class RequestHandler implements HttpHandler {

  String mongoURI =
    "mongodb+srv://user:RbrRuGgkHtbSgqai@pantrypal.mz6l5wo.mongodb.net/?retryWrites=true&w=majority";

  public void handle(HttpExchange httpExchange) throws IOException {
    String response = "Request Received";
    String method = httpExchange.getRequestMethod();

    try {
      if (method.equals("GET")) {
        response = handleGet(httpExchange);
      } else if (method.equals("POST")) {
        response = handlePost(httpExchange);
      } else if (method.equals("PUT")) {
        response = handlePut(httpExchange);
      } else if (method.equals("DELETE")) {
        response = handleDelete(httpExchange);
      } else {
        throw new Exception("Not Valid Request Method");
      }
    } catch (Exception e) {
      System.out.println("An erroneous request");
      response = e.toString();
      e.printStackTrace();
    }
    response = URLEncoder.encode(response, StandardCharsets.UTF_8.toString());
    httpExchange.sendResponseHeaders(200, response.length());
    OutputStream outStream = httpExchange.getResponseBody();
    outStream.write(response.getBytes());
    outStream.close();
  }

  private String handleGet(HttpExchange httpExchange) throws IOException {
    String response = "Invalid GET request";
    URI uri = httpExchange.getRequestURI();
    String query = uri.getRawQuery();
    String username = query.substring(query.indexOf("=") + 1);
    response = "";
    try (MongoClient mongoClient = MongoClients.create(mongoURI)) {
      MongoDatabase sampleTrainingDB = mongoClient.getDatabase("account_db");
      MongoCollection<Document> recipesCollection = sampleTrainingDB.getCollection(
        "recipes"
      );
      Bson filter = eq("account", username);

      FindIterable<Document> recipes = recipesCollection.find(filter);

      for (Document recipe : recipes) {
        System.out.println(recipe);
        response += recipe.get("recipe") + "|" + recipe.get("meal_type") + "~";
        // response += recipe.get("recipe") + "~";
        // response += recipe.get("recipe").toString().substring(0, 
        // (recipe.get("recipe")).toString().indexOf("|")) + "~";
      }
    }
    System.out.println(response);
    return response;
  }

  private String handlePost(HttpExchange httpExchange) throws IOException {
    InputStream inStream = httpExchange.getRequestBody();
    Scanner scanner = new Scanner(inStream);
    String postData = scanner.nextLine();
    String username = postData.substring(0, postData.indexOf(","));
    String recipe = postData.substring(postData.indexOf(",") + 1) + '\n';
    while (scanner.hasNext()) {
      recipe += scanner.nextLine() + '\n';
    }
    System.out.println(recipe);

    try (MongoClient mongoClient = MongoClients.create(mongoURI)) {
      MongoDatabase accountDB = mongoClient.getDatabase("account_db");
      MongoCollection<Document> recipesCollection = accountDB.getCollection(
        "recipes"
      );
      Document account = new Document("account", username);
      account.append("recipe", recipe.substring(0, recipe.indexOf("|")));
      account.append("title", recipe.substring(0, recipe.indexOf("\n")));
      account.append(
        "meal_type",
        recipe.substring(recipe.indexOf("|") + 1, recipe.length() - 1)
      );
      recipesCollection.insertOne(account);
    }

    String response = "Posted entry {" + username + ", " + recipe + "}";
    System.out.println(response);
    scanner.close();

    return response;
  }

  private String handlePut(HttpExchange httpExchange) throws IOException {
    InputStream inStream = httpExchange.getRequestBody();
    Scanner scanner = new Scanner(inStream);
    String postData = scanner.nextLine();
    String username = postData.substring(0, postData.indexOf(","));
    //String mealType = postData.substring();
    String recipe = postData.substring(postData.indexOf(",") + 1) + '\n';
    while (scanner.hasNext()) {
      recipe += scanner.nextLine() + '\n';
    }

    try (MongoClient mongoClient = MongoClients.create(mongoURI)) {
      MongoDatabase accountDB = mongoClient.getDatabase("account_db");
      MongoCollection<Document> recipesCollection = accountDB.getCollection(
        "recipes"
      );
      Bson filter = and(
        eq("title", recipe.substring(0, recipe.indexOf('\n'))),
        eq("account", username)
      );
      Bson updateOperation = set("recipe", recipe);
      recipesCollection.updateOne(filter, updateOperation);
    }

    String response = "Posted entry {" + username + ", " + recipe + "}";
    System.out.println(response);
    scanner.close();

    return response;
  }

  private String handleDelete(HttpExchange httpExchange) throws IOException {
    String response = "Invalid GET request";
    URI uri = httpExchange.getRequestURI();
    String query = uri.getRawQuery();
    if (query != null) {
      String username = query.substring(
        query.indexOf("=") + 1,
        query.indexOf('~')
      );
      String name = query.substring(query.indexOf('~') + 1);
      String mealType = query.substring(query.lastIndexOf("~") + 1); // adding to delete mealtype
      name = name.replaceAll("_", " ");
      try (MongoClient mongoClient = MongoClients.create(mongoURI)) {
        MongoDatabase accountDB = mongoClient.getDatabase("account_db");
        MongoCollection<Document> recipesCollection = accountDB.getCollection(
          "recipes"
        );
        Bson filter = and(eq("title", name), eq("account", username), eq("meal_type", mealType)); // adding to delete mealtype
        recipesCollection.deleteOne(filter);
      }
    }
    return response;
  }
}
