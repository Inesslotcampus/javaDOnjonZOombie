package com.example.donjonZoobie.model;

public class CharacterForm {

    private long id;
    private String name;
    private String type;
    private  int levelLife;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLevelLife(int levelLife) {
        this.levelLife = levelLife;
    }

    public CharacterForm(String name, String type, int levelLife) {
        this.name = name;
        this.type = type;
        this.levelLife = levelLife;
    }

    public CharacterForm() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLevelLife() {
        return levelLife;
    }


}
