package com.vladislavlevchik.actions.turnActions;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.Simulation;
import com.vladislavlevchik.actions.Action;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.entities.Creature;
import com.vladislavlevchik.entities.Entity;

import java.util.*;

public class MoveCreaturesAction extends Action {

    @Override
    public void prefer(WorldMap worldMap) {
        HashMap<Cell, Entity> hashMap = worldMap.getMap();

        for (int x = 1; x <= worldMap.height; x++) {
            for (int y = 1; y <= worldMap.length; y++) {
                Cell cell = new Cell(x, y);
                Entity entity = hashMap.get(cell);
                if (entity instanceof Creature)
                    ((Creature) entity).setAlreadyMove(false);
            }
        }

        for (int x = 1; x <= worldMap.height; x++) {
            for (int y = 1; y <= worldMap.length; y++) {
                Cell cell = new Cell(x, y);
                Entity entity = hashMap.get(cell);
                if (entity instanceof Creature && !(((Creature) entity).isAlreadyMove())) {
                    ((Creature) entity).setAlreadyMove(true);
                    ((Creature) entity).makeMove(worldMap, cell);
                }
            }
        }

        Simulation.countOfMoves++;
    }
}
