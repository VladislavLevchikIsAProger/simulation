package com.vladislavlevchik.services;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.entities.Entity;

import java.util.HashMap;
import java.util.Objects;

public class WorldConsolePrinterService {
    public static void printWorld(WorldMap worldMap) {
        HashMap<Cell, Entity> hashMap = worldMap.getMap();

        for (int x = 1; x <= worldMap.height; x++) {
            for (int y = 1; y <= worldMap.length; y++) {
                Entity entity = hashMap.get(new Cell(x, y));
                System.out.print(Objects.requireNonNullElse(entity, "\uD83C\uDFFE"));
            }
            System.out.println();
        }
    }
}
