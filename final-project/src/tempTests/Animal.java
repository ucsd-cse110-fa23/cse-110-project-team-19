package tempTests;

public abstract class Animal {

  String name;
  String sound;

  public String getName() {
    return name;
  }

  public String getSound() {
    return sound;
  }

  public abstract void setName();

  public abstract void setSound();
}
