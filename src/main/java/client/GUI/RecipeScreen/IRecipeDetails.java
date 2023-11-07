package client.GUI.RecipeScreen;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IRecipeDetails {
    public void newRecipe(String mealType, String ingredients) 
        throws IOException, InterruptedException, URISyntaxException;
}
