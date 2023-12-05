package client.model;

import client.view.MainMenu.Recipe;
import java.util.Comparator;

public class CompareChrono implements Comparator<Recipe> {

  @Override
  public int compare(Recipe recipe1, Recipe recipe2) {
    return recipe1.getTime().compareTo(recipe2.getTime());
  }
}
