package client;

import java.util.HashMap;

import client.GUI.DeleteConfirm;
import client.GUI.EditRecipeScreen;

import client.GUI.MainMenu.MainMenu;
import client.GUI.RecordScreen.RecordScreen;
import client.GUI.RecipeScreen.RecipeScreen;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class View {
    
    HashMap<String, BorderPane> scenes;
    Scene scene;

    public View(){
        scenes = new HashMap<>();
        scenes.put("main", new MainMenu(this));
        scenes.put("record", new RecordScreen(this));
        scenes.put("recipe",new RecipeScreen(this));
        scenes.put("edit",new EditRecipeScreen());
        scenes.put("delete",new DeleteConfirm());

        scene = new Scene(scenes.get("main"), 500, 600);
    }

    public BorderPane getRoot(String key){
        return scenes.get(key);
    }

    public void setRoot(String key){
        scene.setRoot(scenes.get(key));
    }
    
    public Scene getScene(){
        return scene;
    }
}