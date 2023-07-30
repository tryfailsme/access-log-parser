package ru.courses.classobject;

public class Sauce {

    private String name;
    private HowHot hot;

    public Sauce(String name, HowHot hot) {
        this.name = name;
        this.hot = hot;
    }

    @Override
    public String toString() {
        return "Соус " + name + ": " + hot.howHot();
    }
}