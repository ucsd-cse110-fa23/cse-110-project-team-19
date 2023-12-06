package client.model;

import java.util.Comparator;

import client.view.MainMenu.Recipe;
 
public class CompareAlphabetical implements Comparator<Recipe> {
 
    @Override
    public int compare(Recipe recipe1, Recipe recipe2) {
        return recipe1.getRecipeName().getText().compareTo(recipe2.getRecipeName().getText());
    }
}
