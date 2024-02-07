package com.vladislavlevchik.services;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.actions.turnActions.GrassGrowthControlAction;
import com.vladislavlevchik.actions.turnActions.StaticObjectsTrackingAction;
import com.vladislavlevchik.entities.*;


import java.util.HashMap;
import java.util.Scanner;

public class UserChangeMapService {

    private static final Scanner scanner = new Scanner(System.in);

    public static void userChangeMap(WorldMap worldMap) {
        String userInput;
        while (true) {
            PrintContextMenuService.userContextMenu();
            userInput = scanner.nextLine();
            switch (userInput) {
                case "1" -> {
                    addTheEntityToARandomCellOnTheMap(worldMap);
                    return;
                }
                case "2" -> {
                    removeTheEntityByCellOnTheMap(worldMap);
                    return;
                }
                case "3" -> {
                    putTheEntityByCellOnTheMap(worldMap);
                    return;
                }
                case "0" -> {
                    System.out.println("Вы вернулись назад");
                    return;
                }
                default -> System.out.println("Некорректный ввод!!!");
            }
        }
    }

    private static void addTheEntityToARandomCellOnTheMap(WorldMap worldMap) {
        Entity entity = userSelectsAnimal();

        userSpawnEntity(worldMap, entity);

        printMap(worldMap);
    }

    private static void removeTheEntityByCellOnTheMap(WorldMap worldMap) {
        HashMap<Cell, Entity> hashMap = worldMap.getMap();

        Cell cell = getCellThatTheUserEntered(worldMap);
        if (hashMap.get(cell) != null) {
            Entity entity = hashMap.remove(cell);
            System.out.println("Сущность " + entity + " с клетки " + cell + " была удалена");
            if (!(entity instanceof Creature)) {
                StaticObjectsTrackingAction.staticEntityMap.remove(cell);
            }
        } else {
            System.out.println("На этой клетке нету сущности");
        }
        printMap(worldMap);
    }

    private static void putTheEntityByCellOnTheMap(WorldMap worldMap) {
        Entity entity = userSelectsAnimal();
        Cell cell = getCellThatTheUserEntered(worldMap);
        userSpawnEntityToCell(worldMap, entity, cell);
        printMap(worldMap);
    }

    private static Cell getCellThatTheUserEntered(WorldMap worldMap) {
        int x = userInputCoordinates(worldMap.height, "X");
        int y = userInputCoordinates(worldMap.length, "Y");

        return new Cell(x, y);
    }

    private static int userInputCoordinates(int endOfRange, String whatFind) {
        int coordinate = 0;
        boolean flag = false;
        String userInput;
        while (!flag) {
            System.out.println("Введите " + whatFind + " в диапазоне [1;" + endOfRange + "]");
            userInput = scanner.nextLine();
            try {
                coordinate = Integer.parseInt(userInput);
                if (coordinate >= 1 && coordinate <= endOfRange) {
                    flag = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод, попробуйте снова");
            }
        }
        return coordinate;
    }

    private static Entity userSelectsAnimal() {
        Entity entity = null;
        boolean flag = false;
        while (!flag) {
            PrintContextMenuService.userChooseAnEntityContextMenu();
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1" -> {
                    entity = new Grass();
                    flag = true;
                }
                case "2" -> {
                    entity = new Herbivore();
                    flag = true;
                }
                case "3" -> {
                    entity = new Predator();
                    flag = true;
                }
                case "4" -> {
                    entity = new Rock();
                    flag = true;
                }
                case "5" -> {
                    entity = new Tree();
                    flag = true;
                }
                default -> System.out.println("Некорректный ввод");
            }
        }
        return entity;
    }

    private static void printMap(WorldMap worldMap) {
        System.out.println("Теперь ваша карта выглядит так:");
        WorldConsolePrinterService.printWorld(worldMap);
    }

    public static void userSpawnEntity(WorldMap worldMap, Entity entity) {
        Cell cell = FindRandomEmptyCellService.findRandomCell(worldMap);
        if (cell != null) {
            worldMap.getMap().put(cell, entity);
            System.out.println("Сущность " + entity + " появилось на клетке " + cell);
            //если добавили новый стат объект,надо добавить его в мапу стат объектов
            if (!(entity instanceof Creature)) {
                StaticObjectsTrackingAction.staticEntityMap.put(cell, entity);
            }
        } else {
            System.out.println("Карта заполнена, ставить сущность некуда!");
        }
    }

    public static void userSpawnEntityToCell(WorldMap worldMap, Entity entity, Cell cell) {
        HashMap<Cell, Entity> hashMap = worldMap.getMap();
        Entity entityOnTheMap = hashMap.get(cell);
        hashMap.put(cell, entity);

        //если стат объект заменяем на другой стат объект
        //если на место стат объекта ставим не стат объект
        if (!(entityOnTheMap instanceof Creature)) {
            if (!((entity) instanceof Creature)) {
                StaticObjectsTrackingAction.staticEntityMap.put(cell, entity);
            } else {
                StaticObjectsTrackingAction.staticEntityMap.remove(cell);
            }
        }

        if (entityOnTheMap != null) {
            System.out.println("Вы поставили сущность " + entity + " на клетку " + cell + " на которой находилось " + entityOnTheMap + cell);
        } else {
            System.out.println("Вы поставили сущность " + entity + " на клетку " + cell + " на которой никого не было");
            //если на клетки никого нет, то есть вероятность, что на ней съели траву и
            //тут должна вырасти новая, надо проверить, если там что то ждет роста, то
            //удалить эту клетку из ждущих роста
            GrassGrowthControlAction.removeCell(cell);
        }
    }
}
