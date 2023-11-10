package client.view.RecordScreen;


import java.io.IOException;
import java.net.URISyntaxException;

public class TranscribeMock extends ATranscribe {

  public boolean meal;

  // should probably be updated to read in audio files this was just a quick last minute solution to test functionality with chatgpt mock
  public String transcribe() throws IOException, URISyntaxException {
    if (meal) {
      return "lunch";
    }
    return "potatoes, beans";
  }
}
