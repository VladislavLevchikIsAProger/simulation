package com.vladislavlevchik.entities;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.services.FindCellService;

import java.util.HashMap;
import java.util.List;

public class Predator extends Creature {

    public Predator() {
        super("\uD83E\uDD81");
    }

    @Override
    public void makeMove(WorldMap worldMap, Cell cell) {
        HashMap<Cell, Entity> map = worldMap.getMap();

        Predator predator = (Predator) map.get(cell);

        Cell targetCell = findCellForTheMove(worldMap, cell);

        printToConsole(cell, targetCell, map);

        map.put(cell, null);
        map.put(targetCell, predator);
    }

    @Override
    public boolean isTheRightCellForTheMove(WorldMap worldMap, Cell cell) {
        return !(worldMap.getMap().get(cell) instanceof Predator);
    }

    @Override
    public String toString() {
        return getType();
    }

}
