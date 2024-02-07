package com.vladislavlevchik.actions.initActions.spawnActions;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.actions.Action;
import com.vladislavlevchik.entities.Entity;
import com.vladislavlevchik.entities.Tree;
import com.vladislavlevchik.services.FindRandomEmptyCellService;

import java.util.HashMap;

public class TreeSpawnAction extends Action {
    private int numberOfTrees = 5;

    @Override
    public void prefer(WorldMap worldMap) {
        HashMap<Cell, Entity> hashMap = worldMap.getMap();
        while (numberOfTrees > 0) {
            hashMap.put(FindRandomEmptyCellService.findRandomCell(worldMap), new Tree());
            numberOfTrees--;
        }
    }
}
