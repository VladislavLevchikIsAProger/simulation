package com.vladislavlevchik.actions.turnActions;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.actions.Action;
import com.vladislavlevchik.entities.Creature;
import com.vladislavlevchik.entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//сервис для отслежки статических объектов,
//а именно если сущность стало на статический объект оно не исчезало
//когда сущность уйдет с клетки статический объект обратно появится на карте
public class StaticObjectsTrackingAction extends Action {
    public static final HashMap<Cell, Entity> staticEntityMap = new HashMap<>();

    public static void addToStaticEntityMap(WorldMap worldMap) {
        HashMap<Cell, Entity> hashMap = worldMap.getMap();
        for (int x = 1; x <= worldMap.height; x++) {
            for (int y = 1; y <= worldMap.length; y++) {
                Cell cell = new Cell(x, y);
                Entity entity = hashMap.get(cell);
                if (!(entity instanceof Creature)) {
                    staticEntityMap.put(cell, entity);
                }
            }
        }
    }

    //метод выставляет все стат объекты с которых ушли существа на свои клетки
    @Override
    public void prefer(WorldMap worldMap) {
        //в этом листе будут все клетки травы которую съели,
        //т.к она вырастет сама, то выставлять ее на карту мне не надо
        ArrayList<Cell> cellsOfGrassWaitingToGrow = GrassGrowthControlAction.getCellsOfGrassWaitingToGrow();

        for (Map.Entry<Cell, Entity> item : staticEntityMap.entrySet()) {
            Cell cell = item.getKey();
            if (!cellsOfGrassWaitingToGrow.contains(cell))
                worldMap.getMap().putIfAbsent(cell, item.getValue());
        }
    }
}
