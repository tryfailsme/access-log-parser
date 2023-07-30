package ru.courses.objects_classes;

import java.util.ArrayList;
import java.util.Arrays;

public class Broken {
    private Line l;
    private ArrayList<Point> brokenLine = new ArrayList<>();
    private ArrayList<Line> massiveLines = new ArrayList<>();

    public Broken(Point... points) {
        this.brokenLine.addAll(Arrays.asList(points));
        for (int i = 0; i < brokenLine.size() - 1; i++) {
            l = new Line(brokenLine.get(i), brokenLine.get(i + 1));
            massiveLines.add(l);
        }
    }

    double getLength() {
        if (brokenLine.size() < 1) return 0;
        double length = 0;
        for (int i = 0; i < brokenLine.size() - 1; i++) {
            double xd = brokenLine.get(i + 1).x - brokenLine.get(i).x;
            double yd = brokenLine.get(i + 1).y - brokenLine.get(i).y;
            length += Math.sqrt(xd * xd + yd * yd);
        }
        return length;
    }

    public String toString() {
        return "Линия " + brokenLine;
    }

    public void toLines() {
        System.out.println("Массив " + massiveLines);
    }

    double getLinesLength() {
        if (massiveLines.size() < 1) return 0;
        double length = 0;
        for (Line l : massiveLines) {
            length += l.getD();
        }
        return length;
    }
}
