package ru.courses.packages;

import ru.courses.objects_classes.Point;

public class HomeWork8_2 {
    public static void main(String[] args) {
        Point myPoint = new Point(0, 0);
        java.awt.Point javaPoint = new java.awt.Point();
        System.out.println("myPoint" + myPoint);
        System.out.println(javaPoint);
        System.out.println(myPoint.getClass());

        Cat cat = new Cat();
        Animal an = new Animal();

        System.out.println(cat instanceof Animal);
        System.out.println(an instanceof Cat);
    }
}