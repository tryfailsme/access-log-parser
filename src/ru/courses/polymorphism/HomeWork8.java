package ru.courses.polymorphism;

public class HomeWork8 {
    public static void main(String[] args) {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Point p3 = new Point(3, 3);
        Point p4 = new Point(4, 1);
        PolyLine A = new PolyLine(p1, p2, p3, p4);
        Polygon B = new Polygon(p1, p2, p3, p4);
        System.out.println(getLinesLength(A));
        System.out.println(getLinesLength(B));
    }

    static double getLinesLength(Lengthable l){
        return l.length();
    }
}
