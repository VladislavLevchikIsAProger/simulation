package com.vladislavlevchik.actions.initActions.spawnActions;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.actions.Action;
import com.vladislavlevchik.entities.Entity;
import com.vladislavlevchik.entities.Grass;
import com.vladislavlevchik.services.FindRandomEmptyCellService;

import java.util.HashMap;

public class GrassSpawnAction extends Action {
    private int numberOfGrasses = 20;

    @Override
    public void prefer(WorldMap worldMap) {
        HashMap<Cell, Entity> hashMap = worldMap.getMap();
        while (numberOfGrasses > 0) {
            hashMap.put(FindRandomEmptyCellService.findRandomCell(worldMap), new Grass());
            numberOfGrasses--;
        }
    }
}
