package client.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import client.model.ATranscribe;

public class MockRegenerateBehavior {
    
    public String regenerate(ATranscribe transcriber){
        String ingredients = "";
        try {
            ingredients = transcriber.transcribe();
        } catch (IOException | URISyntaxException e) {}
        return ingredients;
    }
}
