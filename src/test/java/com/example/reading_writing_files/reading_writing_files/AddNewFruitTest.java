package com.example.reading_writing_files.reading_writing_files;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class AddNewFruitTest {
    private static final String TEST_FILE_PATH = "fruit.json";
    @Test
    void shouldAddFruitToEmptyFile() throws IOException, URISyntaxException {
        //Arrange
        var client = new AddNewFruit(TEST_FILE_PATH);
        var fruitTest = "watermelon";
        var colorTests = "red";
        var newFruit = new Fruit(1, fruitTest, colorTests);
        //Act
        client.emptyFile();
        var result = client.addFruitToList(1,"watermelon", "red");
        //Assert
        assertEquals(newFruit.getName(), result);
    }

    @Test
    void shouldWriteFruitsToFileWithOtherData() throws IOException, URISyntaxException {
        //Arrange
        var fruit = "apple";
        var color = "green";
        var client = new AddNewFruit(TEST_FILE_PATH);
        client.emptyFile();
        client.addFruitToList(2, "orange", "orange");
        //Act
        var result = client.addFruitToList(3,fruit, color);
        //Assert
        assertEquals(fruit,result);
    }
}