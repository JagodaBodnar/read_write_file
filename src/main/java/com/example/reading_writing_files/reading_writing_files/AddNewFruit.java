package com.example.reading_writing_files.reading_writing_files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddNewFruit {
    private final Path jsonFile;

    public AddNewFruit(@Value("${fruit-color}") String jsonFile) throws URISyntaxException, FileNotFoundException {
        Path file = Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource(jsonFile)).toURI());
        if (!Files.exists(file)) {
            throw new FileNotFoundException("'" + jsonFile + "' was not found");
        }
        this.jsonFile = file;
    }

    public Fruit addFruitToList(String name, String color) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Fruit fruit = new Fruit("pear","green");

        String json =  readJsonFile();
        List<Fruit> fruitsList;
        if(json.isEmpty()){
            fruitsList =  new ArrayList<>();
        }
        else{
            fruitsList = mapper.readValue(json, new TypeReference<>(){});
        }
        System.out.println("fruitsList = " + fruitsList);
        fruitsList.add(fruit);
        String updatedFruits =  mapper.writeValueAsString(fruitsList);
        writeFile(updatedFruits);
        return fruit;
    }

    public String readJsonFile() throws IOException {
        return Files.readString(jsonFile);
    }
    private void writeFile(String input) throws IOException {
        Files.write(jsonFile, input.getBytes());
    }
    public void emptyFile() throws IOException {
        writeFile("");
    }
}
