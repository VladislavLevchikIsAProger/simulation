package com.vladislavlevchik.actions.initActions.spawnActions;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.actions.Action;
import com.vladislavlevchik.entities.Entity;
import com.vladislavlevchik.entities.Predator;
import com.vladislavlevchik.services.FindRandomEmptyCellService;

import java.util.HashMap;

public class PredatorSpawnAction extends Action {
    private int numberOfPredators = 1;

    @Override
    public void prefer(WorldMap worldMap) {
        HashMap<Cell, Entity> hashMap = worldMap.getMap();
        while (numberOfPredators > 0) {
            hashMap.put(FindRandomEmptyCellService.findRandomCell(worldMap), new Predator());
            numberOfPredators--;
        }
    }
}
