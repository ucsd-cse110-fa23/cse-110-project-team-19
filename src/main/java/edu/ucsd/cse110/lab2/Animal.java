package edu.ucsd.cse110.lab2;

public abstract class Animal {
    String name;
    String sound;

    String getName(){
        return name;
    }
    String getSound(){
        return sound;
    }

    abstract void setName();
    abstract void setSound();
}
