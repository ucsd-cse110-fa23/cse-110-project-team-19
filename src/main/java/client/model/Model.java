package client.model;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
// import javafx.scene.text.TextAlignment;
import org.json.JSONArray;
import org.json.JSONObject;

public class Model {

  //TODO: put your api endpoint
  private static final String API_ENDPOINT = "<api-endpoint>";
  private static final String API_KEY =
    "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";
  private static final String MODEL = "text-davinci-003";
}
