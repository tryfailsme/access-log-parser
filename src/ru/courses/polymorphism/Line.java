package ru.courses.polymorphism;

class Line implements Lengthable {
    Point beginPoint, endPoint;

    public Line(Point p1, Point p2) {
        this.beginPoint = p1;
        this.endPoint = p2;
    }

    public double length() {
        double xd = beginPoint.x - endPoint.x;
        double yd = beginPoint.y - endPoint.y;
        return Math.sqrt(xd * xd + yd * yd);
    }
}