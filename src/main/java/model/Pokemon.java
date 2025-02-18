package model;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private int id;
    private String name;

    public Pokemon() {}

    public Pokemon(String name) {
        this.name = name;
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

}
