package com.marcusbiel.javacourse.lesson2;


public class CarSelector {


    public static void main(String... args) {
        CarService carService = new CarService();
        for (String argument : args) {
            if (isValid(argument)){
                carService.process(argument);
            }
            else{
                System.err.println("ignoring invalid argument: " + argument);
            }
        }


    }

    private static boolean isValid(String argument) {
        try {
            CarState carstate = CarState.valueOf(argument);

        } catch (IllegalArgumentException e){
            return false;
        } finally {

        }
        return true;
    }


}
