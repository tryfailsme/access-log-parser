package ru.courses.polymorphism;

public class Polygon extends PolyLine {
    public Polygon(Point... points) {
        super(points);
    }

    public double length() {
        double sum, len1, len2;
        len1 = points[points.length - 1].x - points[0].x;
        len2 = points[points.length - 1].y - points[0].y;
        sum = super.length() + Math.sqrt(len1 * len1 + len2 * len2);
        return sum;
    }
}