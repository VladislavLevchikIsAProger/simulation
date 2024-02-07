package com.vladislavlevchik.entities;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.services.FindCellService;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public abstract class Creature extends Entity {
    private boolean alreadyMove;

    public Creature(String type) {
        super(type);
    }

    public abstract void makeMove(WorldMap worldMap, Cell cell);

    public abstract boolean isTheRightCellForTheMove(WorldMap worldMap, Cell cell);

    protected void printToConsole(Cell cell, Cell targetCell, HashMap<Cell, Entity> hashMap) {
        if (cell.equals(targetCell))
            System.out.println(this + "" + cell + " стоит на месте, ей некуда идти");
        else {
            Entity entity = hashMap.get(targetCell);
            System.out.println(this + "" + cell + " походило на клетку " + Objects.requireNonNullElse(entity, "\uD83C\uDFFE") + targetCell);
        }
    }

    protected Cell findCellForTheMove(WorldMap worldMap, Cell cell) {
        List<Cell> path = FindCellService.findPath(worldMap, cell);
        Cell targetCell;
        if (path != null)
            targetCell = path.get(1);
        else {
            targetCell = FindCellService.findCell(worldMap, cell);
        }
        return targetCell;
    }

    public void setAlreadyMove(boolean flag) {
        alreadyMove = flag;
    }

    public boolean isAlreadyMove() {
        return alreadyMove;
    }
}
