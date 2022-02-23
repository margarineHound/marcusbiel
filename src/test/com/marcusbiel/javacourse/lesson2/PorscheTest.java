package com.marcusbiel.javacourse.lesson2;
import org.junit.Test;

import static org.junit.Assert.*;

public class PorscheTest {

    @Test
    public void shouldCloneStringArray(){
        String[] array = {"one", "two", "three"};
        String[]  copiedArray = array.clone();
        assertSame(array, array);
        assertNotSame(array, copiedArray);
        for(String str: array){

            System.out.println(str);
        }
    }

    @Test
    public void shouldClonePorsche() throws CloneNotSupportedException {
        Porsche porsche = new Porsche("Takin");
        Porsche marcusPorsche = porsche.clone();
        assertNotSame(marcusPorsche,porsche);
        marcusPorsche.owner = "Marcus";
        assertEquals("Porsche of Marcus", marcusPorsche.asString());
        assertEquals("Porsche of Takin", porsche.asString());
    }
}
