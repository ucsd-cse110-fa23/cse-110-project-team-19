package tempTests;

public class Main {

  public static void main(String[] args) {
    System.out.println("Animal 1:");

    Animal animal1 = new Cat();
    animal1.setName();
    animal1.setSound();
    String animal1Name = animal1.getName();
    String animal1Sound = animal1.getSound();
    System.out.println(
      "animalName: " + animal1Name + "\nanimalSound: " + animal1Sound
    );

    System.out.println("\nAnimal 2:");

    Animal animal2 = new Dog();
    animal2.setName();
    animal2.setSound();
    String animal2Name = animal2.getName();
    String animal2Sound = animal2.getSound();
    System.out.println(
      "animalName: " + animal2Name + "\nanimalSound: " + animal2Sound
    );
  }
}
