package com.vladislavlevchik.actions.initActions.spawnActions;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.actions.Action;
import com.vladislavlevchik.entities.Entity;
import com.vladislavlevchik.entities.Rock;
import com.vladislavlevchik.services.FindRandomEmptyCellService;

import java.util.HashMap;

public class RockSpawnAction extends Action {
    private int numberOfRocks = 2;

    @Override
    public void prefer(WorldMap worldMap) {
        HashMap<Cell, Entity> hashMap = worldMap.getMap();
        while (numberOfRocks > 0) {
            hashMap.put(FindRandomEmptyCellService.findRandomCell(worldMap), new Rock());
            numberOfRocks--;
        }
    }
}
