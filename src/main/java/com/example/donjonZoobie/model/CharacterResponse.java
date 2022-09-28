package com.example.donjonZoobie.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CharacterResponse {
    private long id;
    private String name;
    private String type;
    private  int levelLife;


    public CharacterResponse(long id, String name, String type, int levelLife) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.levelLife = levelLife;
    }
    public CharacterResponse(){

    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getLevelLife() {
        return levelLife;
    }
    public void setLevelLife(int levelLife) {
        this.levelLife = levelLife;
    }
}
