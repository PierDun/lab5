package com.company;

/**
 * Класс судуков
 * @author Земнухов Владимир
 */
class Chest implements Comparable<Chest> {
    private String name;
    private int sum;

    /**
     * Конструктор сундука
     *
     * @param a - задаваемое имя
     * @param b - задаваемая хранимая сумма
     */
    Chest(String a, String b) {
        name = a;
        sum = Integer.valueOf(b);
    }

    /**
     * Конструктор сундука
     */
    Chest() {
    }

    /**
     * Задать имя сундука
     *
     * @param name - его имя
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Задать сумму, хранимую в сундуке
     *
     * @param sum - сумма на хранение
     */
    void setSum(String sum) {
        try {
            this.sum = Integer.parseInt(sum);
        } catch (NumberFormatException e) {
            System.out.println("Неверный числовой формат в " + this.name + "'е.");
            System.exit(1);
        }
    }

    /**
     * Получить имя сундука
     *
     * @return - полученное имя
     */
    String getName() {
        return this.name;
    }

    /**
     * Узнать хранимую в сундуке сумму
     *
     * @return - хранимая сумма
     */
    int getSum() {
        return sum;
    }

    /**
     * метод сравнения сундуков
     *
     * @param o - сундук для сравнения
     * @return - результат сравнения (1, -1, 0)
     */
    public int compareTo(Chest o) {
        try {
            if (!this.name.equals(o.name)) {
                return this.name.compareTo(o.name);
            } else {
                if (this.sum > o.sum) return 1;
                else return 0;
            }
        } catch (NullPointerException e) {
            return 0;
        }
    }
}