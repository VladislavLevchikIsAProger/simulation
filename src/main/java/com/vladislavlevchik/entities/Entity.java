package com.vladislavlevchik.entities;

public abstract class Entity {
    private final String type;

    public Entity(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
