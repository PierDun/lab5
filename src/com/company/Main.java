package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Основной класс программы
 * @author Земнухов Владимир
*/
public class Main {
private static boolean a = true;

private static String[] readAndParseCommand() {
    StringBuilder command = new StringBuilder();
    Scanner scan = new Scanner(System.in);
    String nextline;
    try {
        int counter;
        do {
            counter = 0;
            nextline = scan.nextLine();
            command.append(nextline);
            char[] commands = command.toString().toCharArray();
            for (char symbol : commands) {
                if (symbol == '{') counter++;
                if (symbol == '}') counter--;
            }
        } while (counter != 0 && counter > 0);
    } catch (NullPointerException | NoSuchElementException ex) {
        command = new StringBuilder("null");
    }
    return command.toString().trim().split(" ", 2);
}

public static void main(String[] args) {
    ChestCollection mainTree = new ChestCollection();
    JsonParcerofComands cmdParse = new JsonParcerofComands();
    Thread mainThread = Thread.currentThread();

    /*Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        try {
            mainTree.save();
            System.out.print("Данные сохранены.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mainThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }));*/

    try {
        mainTree.setPath(System.getenv("Lab5"));
    } catch (FileNotFoundException | NullPointerException e) {
        try {
            System.out.println("Переменная окружения Lab5 либо не существует, либо указывает на не тот файл.");
            System.out.println("Будет использоваться файл lab5.csv.");
            mainTree.setPath("/home/s245031/lab5/lab5.csv");
        } catch (FileNotFoundException e1) {
            System.out.println("Создайте переменную окружения Lab5 и укажите путь к файлу.");
        }
    } finally {
        mainTree.readElements(mainTree.input);
        mainTree.writeElements();

        while (a) {
            System.out.println("Введите команду (info - команда для справки):");

            String[] comands = readAndParseCommand();

            switch (comands[0]) {
            case "info":
                System.out.println("exit - Завершение работы.");
                System.out.println("clear - Очистить коллекцию.");
                System.out.println("import {путь к файлу} - Добавить в коллекцию элементы из заданного файла.");
                System.out.println("add {JsonObj} - Добавить в коллекцию элемент.");
                System.out.println("add_if_max {JsonObj} - Добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции.");
                System.out.println("add_if_min {JsonObj} - Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
                System.out.println("remove_lower {JsonObj} - Удалить из коллекции все элементы, меньшие, чем заданный");
                System.out.println("save - Сохранить элеметы коллекции в файл.");
                break;
            case "exit":
                    System.out.print("Завершение работы. ");
                    a = false;
                break;
            case "clear":
                mainTree.types.clear();
                System.out.println("Коллекция очищена.");
                break;
            case "save":
                try {
                    mainTree.save();
                    System.out.println("Данные сохранены");
                } catch (IOException e2) {
                    System.out.println("Невозможно сохранить файл.");
                }
                break;
            case "import":
                mainTree.import_All(comands[1]);
                break;
            case "null":
                a = false;
                System.out.println("Завершение работы...");
                break;
            default:
                    try {
                        cmdParse.command(comands[0], comands[1], mainTree);
                    } catch (IOException e) {
                        System.out.println("Парсинг невозможен.");
                    } catch (ArrayIndexOutOfBoundsException e1) {
                        System.out.println("Неизвестная команда.");
                    }
                    break;
                }
            }
        }
    }
}