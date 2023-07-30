package ru.courses.encapsulation;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Integer> massiveGrades = new ArrayList<>();

    public Student(String name) {
        Student student = new Student(name, null);
    }

    public Student(String name, Integer... grades) {
        this.name = name;
        for (Integer grade : grades) {
            if (grade > 5 || grade < 2)
                throw new IllegalArgumentException("Все оценки должны быть в диапазоне от 2 до 5.");
            this.massiveGrades.add(grade);
        }
    }

    public String toString() {
        return name + ": " + massiveGrades;
    }

    public Student addGrade(Integer... grades) {
        for (Integer grade : grades) {
            if (grade > 5 || grade < 2)
                throw new IllegalArgumentException("Все оценки должны быть в диапазоне от 2 до 5.");
            this.massiveGrades.add(grade);
        }
        return this;
    }

    public List<Integer> getGrades() {
        return new ArrayList<>(massiveGrades);
    }
}