package com.vladislavlevchik.services;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.entities.Entity;

import java.util.HashMap;
import java.util.Random;

public class FindRandomEmptyCellService {
    public static Cell findRandomCell(WorldMap worldMap) {
        HashMap<Cell, Entity> hashMap = worldMap.getMap();
        if (!isTheCardFull(hashMap, worldMap.length, worldMap.height)) {
            Random random = new Random();
            int x = random.nextInt(worldMap.height) + 1;
            int y = random.nextInt(worldMap.length) + 1;
            while (hashMap.get(new Cell(x, y)) != null) {
                x = random.nextInt(worldMap.height) + 1;
                y = random.nextInt(worldMap.length) + 1;
            }
            return new Cell(x, y);
        }
        return null;
    }

    private static boolean isTheCardFull(HashMap<Cell, Entity> hashMap, int length, int height) {
        int countOfEntity = 0;
        for (int x = 1; x <= height; x++) {
            for (int y = 1; y <= length; y++) {
                if (hashMap.get(new Cell(x, y)) != null)
                    countOfEntity++;
            }
        }
        return countOfEntity == length * height;
    }
}

