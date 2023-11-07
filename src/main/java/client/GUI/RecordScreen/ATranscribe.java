package client.GUI.RecordScreen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class ATranscribe {

    // FIELDS
    private static final String API_ENDPOINT =
        "https://api.openai.com/v1/audio/transcriptions";
    private static final String TOKEN =
        "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";
    private static final String MODEL = "whisper-1";
    private static final String FILE_PATH = "ingredients.wav";

    // CONCRETE METHODS
    public static String getEndpoint()
    {
        return API_ENDPOINT;
    }

    public static String getToken()
    {
        return TOKEN;
    }

    public static String getModel()
    {
        return MODEL;
    }

    public static String getFilePath()
    {
        return FILE_PATH;
    }

    public static String checkMealType(String input) 
    {
        input = input.replaceAll("\\p{Punct}", "");
        
        if (input.toLowerCase().equals("breakfast")) {
            return "breakfast";
        } else if (input.toLowerCase().equals("lunch")) {
            return "lunch";
        } else if (input.toLowerCase().equals("dinner")) {
            return "dinner";
        }
        return null;
    }

    // ABSTRACT METHODS
    abstract public String transcribe() throws IOException, URISyntaxException;
}
