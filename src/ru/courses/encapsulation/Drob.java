package ru.courses.encapsulation;
public class Drob {
    private int chisl;
    private int znamen;


    public Drob() {
        this(0, 1);
    }

    public Drob(int chisl) {
        this(chisl, 1);
    }

    public Drob(int chisl, int znamen) {
        if (znamen <= 0)
            throw new IllegalArgumentException("Знаменатель должен быть больше 0.");
        this.chisl = chisl;
        this.znamen = znamen;
    }

    private Drob(Drob drob) {
        //invoke the constructor with 2 args:
        this(drob.getChisl(), drob.getZnamen());
    }

    private void setChisl(int chisl) {
        this.chisl = chisl;
    }

    private void setZnamen(int znamen) {
        if (znamen <= 0)
            throw new IllegalArgumentException("Знаменатель должен быть больше 0.");
        this.znamen = znamen;
    }

    public int getChisl() {
        return chisl;
    }

    public int getZnamen() {
        return znamen;
    }

    public String toString() {
        return String.format("%d/%d", getChisl(), getZnamen());
    }


    public Drob sum(Drob frac1) {
        Drob sumResult = new Drob();

        Drob fracOne = new Drob(frac1);

        fracOne.setChisl(fracOne.getChisl() * this.getZnamen());
        fracOne.setZnamen(fracOne.getZnamen() * this.getZnamen());
        this.setChisl(this.getChisl() * frac1.getZnamen());
        this.setZnamen(this.getZnamen() * frac1.getZnamen());

        sumResult.setChisl(fracOne.getChisl() + this.getChisl());
        sumResult.setZnamen(fracOne.getZnamen());

        return sumResult;
    }

    public Drob sum(int x) {
        Drob sumResult = new Drob(x, 1);
        return this.sum(sumResult);
    }

    public Drob sum(double x) {
        int y = 1;
        while (x % 1 != 0){
            x *= 10;
            y *= 10;
        }
        Drob sumResult = new Drob((int)x, y);
        return this.sum(sumResult);
    }

    public Drob minus(Drob frac1) {
        Drob minusAnswer = new Drob();

        Drob fracOne = new Drob(frac1);

        fracOne.setChisl(fracOne.getChisl() * this.getZnamen());
        fracOne.setZnamen(fracOne.getZnamen() * this.getZnamen());
        this.setChisl(this.getChisl() * frac1.getZnamen());
        this.setZnamen(this.getZnamen() * frac1.getZnamen());

        minusAnswer.setChisl(fracOne.getChisl() - this.getChisl());
        minusAnswer.setZnamen(fracOne.getZnamen());

        return minusAnswer;
    }

    public Drob minus(int x) {
        Drob minusAnswer = new Drob(x, 1);
        return this.sum(minusAnswer);
    }
}