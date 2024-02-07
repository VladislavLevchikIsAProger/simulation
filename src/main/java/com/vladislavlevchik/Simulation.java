package com.vladislavlevchik;


import com.vladislavlevchik.actions.Action;
import com.vladislavlevchik.actions.initActions.spawnActions.*;
import com.vladislavlevchik.actions.turnActions.EnsureSufficientGrassAction;
import com.vladislavlevchik.actions.turnActions.GrassGrowthControlAction;
import com.vladislavlevchik.actions.turnActions.MoveCreaturesAction;
import com.vladislavlevchik.actions.initActions.SaveStaticObjectsAction;
import com.vladislavlevchik.actions.turnActions.StaticObjectsTrackingAction;
import com.vladislavlevchik.services.PrintContextMenuService;
import com.vladislavlevchik.services.UserChangeMapService;
import com.vladislavlevchik.services.WorldConsolePrinterService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulation {
    private final WorldMap worldMap;
    private final List<Action> initActions;
    private final List<Action> turnActions;
    public static int countOfMoves = 0;
    private final Object lock = new Object();
    private boolean pause = true;
    private final Scanner scanner = new Scanner(System.in);

    public Simulation(WorldMap worldMap) {
        this.worldMap = worldMap;
        initActions = new ArrayList<>();
        turnActions = new ArrayList<>();
    }

    public void nextTurn() {
        for (Action action : turnActions) {
            action.prefer(worldMap);
        }

        WorldConsolePrinterService.printWorld(worldMap);
        System.out.println();
    }

    public void play() {
        createActions();
        for (Action action : initActions) {
            action.prefer(worldMap);
        }

        Thread thread = createThreadForSimulation();

        System.out.println("Вы решили начать симуляцию, ваша карта:");
        WorldConsolePrinterService.printWorld(worldMap);

        thread.start();

        while (true) {
            printContextMenu();
        }

    }

    private Thread createThreadForSimulation() {
        return new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (pause) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                System.out.println("Вы запустили автоматическую симуляцию, чтобы поставить ее на паузу нажмите [1]");
                nextTurn();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void printContextMenu() {
        if (pause) {

            PrintContextMenuService.pauseSimulationContextMenu();

            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1" -> continueSimulation();
                case "2" -> nextTurn();
                case "3" -> UserChangeMapService.userChangeMap(worldMap);
                case "0" -> System.exit(0);
                default -> System.out.println("Неверный ввод, введите корректное значение!!!");
            }

        } else {
            if (scanner.nextLine().equals("1")) {
                pauseSimulation();
            } else {
                System.out.println("Некорректный ввод!!! Введите [1] чтобы закончить автоматическую симуляцию");
            }
        }
    }

    private void pauseSimulation() {
        pause = true;
    }

    private void continueSimulation() {
        pause = false;
        synchronized (lock) {
            lock.notify();
        }
    }

    public void createActions() {
        initActions.add(new GrassSpawnAction());
        initActions.add(new HerbivoreSpawnAction());
        initActions.add(new PredatorSpawnAction());
        initActions.add(new RockSpawnAction());
        initActions.add(new TreeSpawnAction());
        initActions.add(new SaveStaticObjectsAction());
        turnActions.add(new MoveCreaturesAction());
        turnActions.add(new StaticObjectsTrackingAction());
        turnActions.add(new GrassGrowthControlAction());
        turnActions.add(new EnsureSufficientGrassAction());
    }

}
