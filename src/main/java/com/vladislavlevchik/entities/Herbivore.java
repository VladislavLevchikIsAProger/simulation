package com.vladislavlevchik.entities;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.Simulation;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.actions.turnActions.GrassGrowthControlAction;

import java.util.*;

public class Herbivore extends Creature {
    public Herbivore() {
        super("\uD83D\uDC30");
    }


    @Override
    public void makeMove(WorldMap worldMap, Cell cell) {
        HashMap<Cell, Entity> map = worldMap.getMap();

        Herbivore herbivore = (Herbivore) map.get(cell);

        HashMap<Integer, ArrayList<Cell>> eatenGrassMap = GrassGrowthControlAction.eatenGrassMap;

        int numberOfMoves = Simulation.countOfMoves;

        ArrayList<Cell> grassesEatenOnTheCurrentMove = eatenGrassMap.get(numberOfMoves);

        //проверка стоит ли травоядное на траве
        if (grassesEatenOnTheCurrentMove != null && grassesEatenOnTheCurrentMove.contains(cell)) {
            System.out.println(herbivore + "" + cell + "стоит на траве и тратит ход на ее поедание");
        } else {
            Cell targetCell = findCellForTheMove(worldMap, cell);

            //проверяем найденная клетка является ли травой
            if (map.get(targetCell) instanceof Grass) {
                //+1 потому что оно будет записывать в мапу на каком ходу на траву стало травоядное,
                //так как счетчик ходов у меня увеличивается после отработки метода makeMove у всех существ и начинается с 0,
                //то здесь записываю текущий ход +1

                //проверка первая ли трава на которую встали
                if (!eatenGrassMap.containsKey(numberOfMoves + 1)) {
                    eatenGrassMap.put(numberOfMoves + 1, new ArrayList<>());
                    eatenGrassMap.get(numberOfMoves + 1).add(targetCell);
                } else {
                    //если не первая трава, то я к списку занятых клеток травы добавляю еще
                    eatenGrassMap.get(numberOfMoves + 1).add(targetCell);
                }
            }

            printToConsole(cell, targetCell, map);

            map.put(cell, null);
            map.put(targetCell, herbivore);
        }
    }

    @Override
    public boolean isTheRightCellForTheMove(WorldMap worldMap, Cell cell) {
        return !(worldMap.getMap().get(cell) instanceof Creature);
    }

    @Override
    public String toString() {
        return getType();
    }
}
