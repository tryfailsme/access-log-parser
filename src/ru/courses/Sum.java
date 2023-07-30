package ru.courses;

import java.util.Scanner;

public class Sum {
    public static void main(String args[]) {
        double sum = 0;
        for (int i = 0; i < args.length; i++) {
            if (isDigit(args[i])) {
                sum += Double.parseDouble(args[i]);
            } else {
                sum += 0;
            }
        }
        System.out.println(sum);
    }

    private static boolean isDigit(String s) {
        Scanner in = new Scanner(s);
        return in.hasNextDouble();
    }
}