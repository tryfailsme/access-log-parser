import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите число №1:");
        int firstNumber = new Scanner(System.in).nextInt();
        System.out.println("Введите число №2:");
        int secondNumber = new Scanner(System.in).nextInt();
        int sum = firstNumber + secondNumber;
        int difference = firstNumber - secondNumber;
        int multiplication = firstNumber * secondNumber;
        double quotient = (double) firstNumber / secondNumber;
        System.out.println("Сумма:" + sum);
        System.out.println("Разность:" + difference);
        System.out.println("Произведение:" + multiplication);
        System.out.println("Частное:" + quotient);
    }
}