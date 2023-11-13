package client.model;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IRecipeDetails {
  public void newRecipe(String mealType, String ingredients)
    throws IOException, InterruptedException, URISyntaxException;
    public String getRecipe();
    public String getRecipeName();
}
