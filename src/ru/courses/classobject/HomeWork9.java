package ru.courses.classobject;

public class HomeWork9 {
    public static void main(String[] args) throws Exception{
        Sauce s1 = new Sauce("1", HowHot.VERY_HOT);
        Sauce s2 = new Sauce("2", HowHot.HOT);
        Sauce s3 = new Sauce("3", HowHot.NOT_HOT);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}

