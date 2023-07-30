package ru.courses.polymorphism;

class Fraction extends Number {
    int num, denum;

    public Fraction(int num, int denum) {
        this.num = num;
        this.denum = denum;
    }

    public String toString() {
        return num + "/" + denum;
    }

    public int intValue() {
        return num / denum;
    }

    public long longValue() {
        return (long) num / (long) denum;
    }

    public float floatValue() {
        return (float) num / (float) denum;
    }

    public double doubleValue() {
        return (double) num / (double) denum;
    }
}