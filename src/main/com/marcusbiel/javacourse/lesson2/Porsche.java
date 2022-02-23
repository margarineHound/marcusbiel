package com.marcusbiel.javacourse.lesson2;

public class Porsche implements Car, Cloneable{

    String owner;

    public Porsche(String owner) {
        this.owner = owner;

    }

    public void drive(){
        System.out.println("Porsche is driving...");
    }
    public void sellTo(String ownerName){
        this.owner = ownerName;
    }

    public String asString(){
        return ("Porsche of " + this.owner);
    }
    @Override
    public Porsche clone() throws CloneNotSupportedException {
        try {
            return (Porsche) super.clone();
        }catch (CloneNotSupportedException e){
            throw new AssertionError();
        }

    }
}
