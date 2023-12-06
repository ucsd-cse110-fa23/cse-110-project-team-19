package client.view.MainMenu;

public class MockRecipe {

  private String recipe;
  private String imageURL;
  private String mealTypeTag;

  private int index;

  public MockRecipe(String mealType) {
    this.mealTypeTag = mealType;
    this.index = 0;
  }

  public void setRecipe(String recipe) {
    this.recipe = recipe;
  }

  public String getImageURL() {
    return this.imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  public String getRecipe() {
    return this.recipe;
  }

  public String getMealType(){
    return this.mealTypeTag;
  }

  public void setMealTypeTag(String mealType){
    this.mealTypeTag = mealType;
  }

  public String toString(){
    return mealTypeTag + "//" + recipe;
  }

  public void setIndex(int index){
    this.index = index;
  }

  public String toLink(){
    return "mongo.link/" + mealTypeTag + "/" + index;
  }
}
