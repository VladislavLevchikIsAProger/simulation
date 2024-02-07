package com.vladislavlevchik.services;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.entities.Creature;
import com.vladislavlevchik.entities.Entity;
import com.vladislavlevchik.entities.Grass;
import com.vladislavlevchik.entities.Herbivore;

import java.util.*;

public class FindCellService {

    public static Cell findCell(WorldMap worldMap, Cell cell) {
        HashMap<Cell, Entity> hashMap = worldMap.getMap();

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        //лист хранит в себе все возможные клетки, на которые травоядное может встать
        ArrayList<Cell> allPossibleCells = new ArrayList<>();

        Creature creature = (Creature) hashMap.get(cell);

        for (int j = 0; j < 4; j++) {
            int nx = cell.getX() + dx[j];
            int ny = cell.getY() + dy[j];

            Cell nextCell = new Cell(nx, ny);

            if (isThisCorrectCell(worldMap, nextCell, creature)) {
                allPossibleCells.add(nextCell);
            }
        }

        if (!allPossibleCells.isEmpty()) {
            //методом рандома выбираю куда оно пойдет
            int random = new Random().nextInt(allPossibleCells.size());
            return allPossibleCells.get(random);
        } else {
            //если возможных ходов нет, то возвращаю ту же клетку(значит травоядное стоит)
            return cell;
        }
    }


    public static List<Cell> findPath(WorldMap worldMap, Cell cell) {
        HashMap<Cell, Entity> hashMap = worldMap.getMap();

        Creature creature = (Creature) hashMap.get(cell);

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<List<Cell>> queue = new LinkedList<>();
        Set<Cell> visited = new HashSet<>();  // Множество для отслеживания посещенных клеток

        List<Cell> initialPath = new ArrayList<>();
        initialPath.add(cell);

        queue.add(initialPath);
        visited.add(cell);

        while (!queue.isEmpty()) {
            List<Cell> currentPath = queue.poll();
            Cell lastPoint = currentPath.get(currentPath.size() - 1);
            Entity currentEntity = hashMap.get(lastPoint);

            if (creature instanceof Herbivore) {
                if (currentEntity instanceof Grass) {
                    return currentPath;
                }
            } else {
                if (currentEntity instanceof Herbivore) {
                    return currentPath;
                }
            }

            for (int j = 0; j < 4; j++) {
                int nx = lastPoint.getX() + dx[j];
                int ny = lastPoint.getY() + dy[j];

                Cell nextCell = new Cell(nx, ny);

                if (isThisCorrectCell(worldMap, nextCell, creature) && !visited.contains(nextCell)) {
                    List<Cell> newPath = new ArrayList<>(currentPath);
                    newPath.add(nextCell);
                    queue.add(newPath);
                    visited.add(nextCell);
                }
            }
        }

        // Возвращаем null, если не найден путь
        return null;
    }

    private static boolean isThisCorrectCell(WorldMap worldMap, Cell cell, Creature creature) {
        int nx = cell.getX();
        int ny = cell.getY();
        return nx >= 1 && nx <= worldMap.height && ny >= 1 && ny <= worldMap.length && creature.isTheRightCellForTheMove(worldMap, cell);
    }

}
