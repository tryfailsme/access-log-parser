package ru.courses.objects_classes;

class Line {
    Point beginPoint, endPoint;

    public Line(Point p1, Point p2) {
        this.beginPoint = p1;
        this.endPoint = p2;
    }

    public Line(int x1, int y1, int x2, int y2) {
        this.beginPoint = new Point(x1, y1);
        this.endPoint = new Point(x2, y2);
    }

    public String toString() {
        return "Линия от " + beginPoint +
                " до " + endPoint;
    }

    public Point getStartPoint() {
        return beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void moveStartPToP(Point p) {
        beginPoint.x = p.x;
        beginPoint.y = p.y;
    }

    public void moveEndPToP(Point p) {
        endPoint.x = p.x;
        endPoint.y = p.y;
    }

    public void moveStartPToC(int x, int y) {
        Point newBeginPoint = new Point(x, y);
        this.moveStartPToP(newBeginPoint);
    }

    public void moveEndPToC(int x, int y) {
        Point newEndPoint = new Point(x, y);
        this.moveStartPToP(newEndPoint);
    }

    double getD() {
        double xd = beginPoint.x - endPoint.x;
        double yd = beginPoint.y - endPoint.y;
        return Math.sqrt(xd * xd + yd * yd);
    }
}