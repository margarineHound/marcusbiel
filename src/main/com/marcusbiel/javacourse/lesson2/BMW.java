package com.marcusbiel.javacourse.lesson2;

public class BMW implements Car,Loggable, Property, Asset {
    public void drive() {
        System.out.println("BMW is driving...");
    }

    @Override
    public int value() {
        return 80000;
    }

    @Override
    public String owner() {
        return "Marcus";
    }

    public String message(){
        return "I am the car of Marcus";
    }
}
