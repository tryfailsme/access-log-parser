package ru.courses.encapsulation;

public class HomeWork7 {
    public static void main(String[] args) {
//      Создать несколько экземпляров дробей
        Drob drob1 = new Drob();
        Drob drob2 = new Drob();
        Drob drob3 = new Drob();

        System.out.println("Fraction1: " + drob1.sum(2).sum(new Drob(3,5)).sum(2.3));
        System.out.println("Fraction2: " + drob2.sum(3.6).sum(new Drob(49,12)).sum(3).sum(new Drob(3,2)));
        System.out.println("Fraction3: " + drob3.sum(new Drob(1,3).sum(1)));
    }
}
