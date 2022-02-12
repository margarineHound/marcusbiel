package com.marcusbiel.javacourse.lesson2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarService {

    private final Logger log = LoggerFactory.getLogger(CarService.class);
//    setLoggingLevel(ch.qos.logback.classic.Level.DEBUG);


    public void process(String input) {
//        log.info("INFO processing car: {}", input);

//        if(log.isDebugEnabled()){
//        log.debug("processing car: {}",  input);
//        }

//        CarState carState = CarState.from(input);
//        System.out.println("valid state:" + carState);
    }

    public void drive() {

        Car[] cars = {new BMW(), new Porsche(), new Mercedes()};
        for(Car car: cars){
            car.drive();
        };
    }
}
