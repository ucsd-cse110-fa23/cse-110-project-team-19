package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockRequestHandler extends RequestHandler{

    Map<String, List<String>> mealTypeToRecipe;

    public MockRequestHandler(){
        mealTypeToRecipe = new HashMap<>();
    }

    public void handlePost(String data){
        String[] parsedData = parseData(data);

        List<String> recipes = mealTypeToRecipe.get(parsedData[0]);
        if(recipes == null)
            recipes = new ArrayList();
        recipes.add(parsedData[1]);
        mealTypeToRecipe.put(parsedData[0], recipes);
    }

    public List<String> retreiveAllRecipesOfMealTypeTag(String mealTypeTag){
        return mealTypeToRecipe.get(mealTypeTag);
    }

    public String retreiveAllRecipesOfMealTypeTag(){
        String allRecipes = "";
        for(String key : mealTypeToRecipe.keySet()){
            List<String> recipes = mealTypeToRecipe.get(key);
            for(String recipe : recipes){
                allRecipes += recipe + " ";
            }
        }
        return allRecipes.substring(0, allRecipes.length()-1);
    }

    private String[] parseData(String data){
        return data.split("//");
    }

    public int getIndex(String mealTypeTag){
        return mealTypeToRecipe.get(mealTypeTag).size() - 1;
    }

    public String handle(String link){
        String[] splitLink = link.split("/");
        return mealTypeToRecipe.get(splitLink[1]).get(Integer.parseInt(splitLink[2]));
    }

    public String toString(List<String> recipes){
        if(recipes == null)
            return null;
        return recipes.get(0);
    }

    public void sort(String style){
        for(String key : mealTypeToRecipe.keySet()){
            Collections.sort(mealTypeToRecipe.get(key));
        }
    }
}
