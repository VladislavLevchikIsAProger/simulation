package com.vladislavlevchik;

import com.vladislavlevchik.entities.Entity;

import java.util.HashMap;

public class WorldMap {
    private final HashMap<Cell, Entity> map;
    public final int height;
    public final int length;

    public WorldMap(int length, int height) {
        map = new HashMap<>();
        this.length = length;
        this.height = height;
    }

    public HashMap<Cell, Entity> getMap() {
        return map;
    }
}
