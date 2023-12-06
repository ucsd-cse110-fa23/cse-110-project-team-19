package server;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class LoginHandler implements HttpHandler {

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

    //Sending back response to the client
    httpExchange.sendResponseHeaders(200, response.length());
    OutputStream outStream = httpExchange.getResponseBody();
    outStream.write(response.getBytes());
    outStream.close();
  }

  private String handleGet(HttpExchange httpExchange) throws IOException {
    String response = "Invalid GET request";
    URI uri = httpExchange.getRequestURI();
    String query = uri.getRawQuery();
    String username = query.substring(
      query.indexOf("=") + 1,
      query.indexOf(",")
    );
    String password = query.substring(query.indexOf(",") + 1);
    response = "Incorrect Password";
    try (MongoClient mongoClient = MongoClients.create(mongoURI)) {
      MongoDatabase sampleTrainingDB = mongoClient.getDatabase("account_db");
      MongoCollection<Document> accountsCollection = sampleTrainingDB.getCollection(
        "accounts"
      );
      Bson filter = eq("username", username);
      Document account = accountsCollection.find(filter).first();
      if (accountsCollection.countDocuments(filter) < 1) {
        return "Not Valid Username";
      } else if (!(password.equals(account.getString("password")))) {
        return "Incorrect Password";
      } else if (password.equals(account.getString("password"))) {
        return "Correct Login";
      }
    }
    return response;
  }
}
