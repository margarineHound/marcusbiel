package com.marcusbiel.javacourse.lesson2;

import org.junit.Test;

public class CalSelectorTest {

    @Test
    public void shouldCallMain(){
        String[] arguments = {"BMW", "Porsche"};
        CarSelector.main(arguments);
    }

}
