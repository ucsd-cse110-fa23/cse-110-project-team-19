import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import client.GUI.MainMenu.*;
import client.GUI.RecipeScreen.*;
import client.GUI.RecordScreen.*;
import client.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Label;
import javafx.geometry.Insets;

public class AppTest {
    TranscribeMock mockWhisper;
    RecipeDetailsMock mockGPT;
    
    @BeforeEach 
    void setUp(){
        mockGPT = new RecipeDetailsMock();
        mockWhisper = new TranscribeMock();
    }
    /*
     * newRecipe() should:  1) take in two strings
     *                      2) have the proper request body to send
     * 
     * UNIT TEST - FEATURE 1
     */
    @Test
    void testNewRecipe(){
        String mealType = "lunch";
        String ingredients = "potatoes, beans";
        try{
            mockGPT.newRecipe(mealType, ingredients);
        }catch (Exception e){}
        
        assertEquals("{\"max_tokens\":100,\"temperature\":1,\"model\":\"text-davinci-003\",\"prompt\":\"Can you create me a recipe for lunch with these ingredients potatoes, beans\"}", 
        mockGPT.requestBody.toString());

    }

    /*
     * This tests how whisper and chatgpt work together and makes sure the request is correct
     * 
     * uses TranscribeMock and RecipeDetailsMock classes
     * NOT TranscribeWhisper and RecipeDetailsChatGPT classes!!!
     * 
     * STORY TEST - FEATURE 1
     */
    @Test
    void testCreateRecipeStory()
    {
        String transcribedMealType = "";
        String transcribedIngredients = "";

        mockWhisper.meal = true;
        try{
                transcribedMealType = mockWhisper.transcribe();
        }catch (Exception e){}
        mockWhisper.meal = false;
        try{
                transcribedIngredients = mockWhisper.transcribe();
        }catch (Exception e){}
        
        
        try{
            mockGPT.newRecipe(transcribedMealType, transcribedIngredients);
        }catch (Exception e){}
        
        assertEquals("{\"max_tokens\":100,\"temperature\":1,\"model\":\"text-davinci-003\",\"prompt\":\"Can you create me a recipe for lunch with these ingredients potatoes, beans\"}", 
        mockGPT.requestBody.toString());

    }

}
