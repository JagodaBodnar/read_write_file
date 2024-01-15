package com.example.reading_writing_files.reading_writing_files;

public class Fruit {
    private String name;
    private String color;
    private int id;

    public Fruit(int id, String name, String color){
        this.id = id;
        this.name = name;
        this.color =color;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
