package ru.courses.classobject;

public enum HowHot {

    VERY_HOT("Очень острый"),
    HOT("Острый"),
    NOT_HOT("Не острый");

    private final String hot;

    HowHot(String hot) {
        this.hot = hot;
    }

    public String howHot() {
        return hot;
    }
}
