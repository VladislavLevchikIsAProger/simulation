package com.vladislavlevchik.services;

public class PrintContextMenuService {
    public static void startContextMenu() {
        System.out.println("Добро пожаловать в Симуляцию 2д мира");
        System.out.println("""
                Введите:\s
                [1] - запустить симуляцию
                [2] - выйти из приложения""");
    }

    public static void pauseSimulationContextMenu() {
        System.out.println("""
                Введите:\s
                [1] - запустить автоматическую симуляцию
                [2] - сделать шаг симуляции
                [3] - изменить карту
                [0] - завершить программу""");
    }

    public static void userContextMenu() {
        System.out.println("""
                Введите:\s
                [1] - добавить сущность в случайное место на карте
                [2] - удалить сущность по клетке
                [3] - поставить сущность на клетку
                [0] - вернуться назад""");
    }

    public static void userChooseAnEntityContextMenu() {
        System.out.println("""
                Введите:\s
                [1] - выбрать \uD83C\uDF40
                [2] - выбрать \uD83D\uDC30
                [3] - выбрать \uD83E\uDD81
                [4] - выбрать \uD83D\uDDFB
                [5] - выбрать \uD83C\uDF33""");
    }

}
