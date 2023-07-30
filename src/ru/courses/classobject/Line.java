package ru.courses.classobject;

import java.util.Objects;

class Line {
    Point start, end;

    public Line(Point p1, Point p2) {
        this.start = p1;
        this.end = p2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(start, line.start) && Objects.equals(end, line.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    protected Line clone() throws CloneNotSupportedException {
        return (Line) super.clone();
    }
}