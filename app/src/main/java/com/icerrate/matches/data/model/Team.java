package com.icerrate.matches.data.model;

/**
 * @author icerrate
 */
public class Team {

    private int id;

    private String name;

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
