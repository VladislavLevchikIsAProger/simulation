package com.vladislavlevchik.entities;

public class Tree extends Entity {
    public Tree() {
        super("\uD83C\uDF33");
    }

    @Override
    public String toString() {
        return getType();
    }
}
