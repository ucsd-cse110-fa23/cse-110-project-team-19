package client.model;

import java.util.Comparator;

import client.view.MainMenu.Recipe;
 
public class CompareChrono implements Comparator<Recipe> {
 
    @Override
    public int compare(Recipe recipe1, Recipe recipe2) {
        return recipe1.getTime().compareTo(recipe2.getTime());
    }
}
