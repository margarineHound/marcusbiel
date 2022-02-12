package com.marcusbiel.javacourse.lesson2;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarServiceTest {

    @Test
    public void shouldDemonstrateLogging(){
        CarService carService = new CarService();
//        carService.process("BMW");
        carService.drive();

    }



}
