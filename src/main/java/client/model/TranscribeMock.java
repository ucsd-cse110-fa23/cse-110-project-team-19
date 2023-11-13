package client.model;

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

public class TranscribeMock extends ATranscribe {
  
  public boolean recordingMealType;
    
    public String transcribe() throws IOException, URISyntaxException {
        if (recordingMealType) {
          return "lunch";
        }
        return "potatoes, beans";
    }

}
