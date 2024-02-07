package com.vladislavlevchik.entities;

public class Grass extends Entity {
    public Grass() {
        super("\uD83C\uDF40");
    }

    @Override
    public String toString() {
        return getType();
    }
}
