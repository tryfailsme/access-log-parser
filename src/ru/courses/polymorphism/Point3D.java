package ru.courses.polymorphism;

class Point3D extends Point{
    private int z;

    Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    Point3D(Point p, int z) {
        super(p.x, p.y);
        this.z = z;
    }
}