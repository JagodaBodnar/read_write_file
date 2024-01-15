package com.example.reading_writing_files.reading_writing_files;

import com.fasterxml.jackson.core.JsonParser;
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

    public String addFruitToList(int i, String name, String color) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        AddFruitDto fruit = new AddFruitDto(name, color);

        String json = readJsonFile();
        List<AddFruitDto> fruitsList;
        if (json.isEmpty()) {
            fruitsList = new ArrayList<>();
        } else {
            fruitsList = mapper.readValue(json, new TypeReference<List<AddFruitDto>>(){});
        }
        fruitsList.add(fruit);
        String updatedFruits = mapper.writeValueAsString(fruitsList);
        writeFile(updatedFruits);
        return name;
    }

    public String getFruitFromList() throws IOException {
        List<Fruit> fruits = new ObjectMapper().readValue((JsonParser) jsonFile, new TypeReference<>() {
        });
        System.out.println("fruits = " + fruits);
        Fruit fruitFiltered = fruits
                .stream()
                .filter(fruit -> fruit.getColor() == "yellow")
                .findFirst()
                .orElse(null);
        return fruitFiltered.getName();
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
