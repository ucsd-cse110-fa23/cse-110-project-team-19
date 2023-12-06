package server;

import com.sun.net.httpserver.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class MyServer {

  // initialize server port and hostname
  private static final int SERVER_PORT = 8100;
  private static final String SERVER_HOSTNAME = "localhost";

  public static void main(String[] args) throws IOException {
    // create a thread pool to handle requests
    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(
      10
    );

    RequestHandler requestHandler = new RequestHandler();

    // create a server
    HttpServer server = HttpServer.create(
      new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT),
      0
    );

    server.createContext("/", requestHandler);
    server.createContext("/create-account", new CreateAccountHandler());
    server.createContext("/login", new LoginHandler());
    server.createContext("/recipeName", new ShareHandler());
    server.setExecutor(threadPoolExecutor);
    server.start();

    System.out.println("Server started on port " + SERVER_PORT);
  }

  public static boolean isServerRunning() {
    try (Socket socket = new Socket(SERVER_HOSTNAME, SERVER_PORT)) {
      // If the connection is successful, the server is running
      return true;
    } catch (IOException e) {
      // If an exception occurs, the server is not running
      return false;
    }
  }
}
