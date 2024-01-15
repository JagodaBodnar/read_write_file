package com.example.reading_writing_files.reading_writing_files;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class AddNewFruitTest {
    private static final String TEST_FILE_PATH = "fruit.json";
    @Test
    void shouldAddFruitToTheList() throws IOException, URISyntaxException {
        //Arrange
        var client = new AddNewFruit(TEST_FILE_PATH);
        var color = "green";
        var fruit = "pear";
        var newFruit = new Fruit(fruit, color);
//        client.emptyFile();
        //Act
        var fruits = client.addFruitToList(fruit, color);
        //Assert
        assertEquals(newFruit.getColor(), fruits.getColor());
    }

}