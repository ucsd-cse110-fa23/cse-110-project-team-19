package server;

import static com.mongodb.client.model.Filters.eq;

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

public class CreateAccountHandler implements HttpHandler {

  String mongoURI =
    "mongodb+srv://user:RbrRuGgkHtbSgqai@pantrypal.mz6l5wo.mongodb.net/?retryWrites=true&w=majority";

  public void handle(HttpExchange httpExchange) throws IOException {
    String response = "Request Received";
    String method = httpExchange.getRequestMethod();

    try {
      if (method.equals("POST")) {
        response = handlePost(httpExchange);
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

  private String handlePost(HttpExchange httpExchange) throws IOException {
    InputStream inStream = httpExchange.getRequestBody();
    Scanner scanner = new Scanner(inStream);
    String postData = scanner.nextLine();
    String username = postData.substring(0, postData.indexOf(","));
    String password = postData.substring(postData.indexOf(",") + 1);

    try (MongoClient mongoClient = MongoClients.create(mongoURI)) {
      MongoDatabase accountDB = mongoClient.getDatabase("account_db");
      MongoCollection<Document> accountsCollection = accountDB.getCollection(
        "accounts"
      );
      Bson filter = eq("username", username);
      if (accountsCollection.countDocuments(filter) > 0) {
        return "Duplicate Username";
      }
      Document account = new Document("username", username);
      account.append("password", password);
      accountsCollection.insertOne(account);
    }

    String response = "Posted entry {" + username + ", " + password + "}";
    System.out.println(response);
    scanner.close();

    return response;
  }
}
