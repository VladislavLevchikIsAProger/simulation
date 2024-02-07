package com.vladislavlevchik.actions.turnActions;

import com.vladislavlevchik.Cell;
import com.vladislavlevchik.Simulation;
import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.actions.Action;
import com.vladislavlevchik.entities.Entity;
import com.vladislavlevchik.entities.Grass;


import java.util.*;

//этот сервис нужен для контроля роста травы
public class GrassGrowthControlAction extends Action {
    //эта мапа нужна для того, чтобы следить за ростом травы
    //хранит в себе ход на которой стали на траву и лист всех клеток травы на которую встали на этом ходу
    public static final HashMap<Integer, ArrayList<Cell>> eatenGrassMap = new HashMap<>();

    //метод выставляет всю траву которую съели, другими словами растит траву заново
    @Override
    public void prefer(WorldMap worldMap) {
        HashMap<Cell, Entity> hashMap = worldMap.getMap();
        ArrayList<Integer> listKeysOfGrassToGrow = new ArrayList<>();
        //добавляю в лист все ключи тех клеток, на которых должна вырасти трава
        for (Map.Entry<Integer, ArrayList<Cell>> item : eatenGrassMap.entrySet()) {
            Integer keyOfGrassToGrowUp = item.getKey();

            if (keyOfGrassToGrowUp <= Simulation.countOfMoves - 4) {
                listKeysOfGrassToGrow.add(keyOfGrassToGrowUp);
            }
        }

        Random random = new Random();
        for (Integer keyOfListGrasses : listKeysOfGrassToGrow) {
            //получаю лист всех клеток, на которых была трава ждущая рост
            ArrayList<Cell> cellsOfWaitToGrowthGrass = eatenGrassMap.get(keyOfListGrasses);
            //пробегаюсь по всем клеткам в этом листе
            //смотрю не стоят ли на этих клетках другие существа
            //если нет, то ставлю туда траву
            for (int j = 0; j < cellsOfWaitToGrowthGrass.size(); j++) {
                Cell cell = cellsOfWaitToGrowthGrass.get(j);
                //проверяю есть ли на этой клетке сущность
                if (hashMap.get(cell) == null) {
                    //шанс 50 процентов что трава которую съели вырастет
                    if (random.nextInt(2) == 1) {
                        hashMap.put(cell, new Grass());
                        System.out.println("На клетке " + cell + "выросла трава");
                    } else {
                        //удаляю из статических объектов, чтобы оно не поставило его
                        StaticObjectsTrackingAction.staticEntityMap.remove(cell);
                    }
                    cellsOfWaitToGrowthGrass.remove(j);
                    j--;
                }
            }
            //если вся трава успешно выросла(т.е. все эти клетки не были заняты),
            //то убираю из мапы
            if (cellsOfWaitToGrowthGrass.isEmpty()) {
                eatenGrassMap.remove(keyOfListGrasses);
            }
        }
    }

    public static ArrayList<Cell> getCellsOfGrassWaitingToGrow() {
        int numberOfMoves = Simulation.countOfMoves + 1;

        ArrayList<Cell> resultList = new ArrayList<>();

        for (Map.Entry<Integer, ArrayList<Cell>> item : eatenGrassMap.entrySet()) {
            if (item.getKey() <= numberOfMoves) {
                resultList.addAll(item.getValue());
            }
        }
        return resultList;
    }


    public static void removeCell(Cell cell) {
        for (Map.Entry<Integer, ArrayList<Cell>> entry : eatenGrassMap.entrySet()) {
            ArrayList<Cell> list = entry.getValue();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(cell)) {
                    list.remove(i);
                    break;
                }
            }
        }
    }
}
