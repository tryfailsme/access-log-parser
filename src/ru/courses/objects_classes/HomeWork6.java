package ru.courses.objects_classes;

public class HomeWork6 {
    public static void main(String[] args) {
        Point p1 = new Point(1, 3);
        Point p2 = new Point(2, 8);
        Point p3 = new Point(5, 3);
        Point p4 = new Point(8, 9);

        Broken b1 = new Broken(p1, p2, p3, p4);

        do {
//      1. Создать Ломаную, проходящую через точки {1;5}, {2;8}, {5;3}, {8,9}
            System.out.println(b1);
//      2. Рассчитать длину Ломаной
            System.out.println(b1.getLength());
//      3. Получить у Ломаной массив Линий
            b1.toLines();
//      4. Рассчитать длину массива Линий
            System.out.println(b1.getLinesLength());
//      5. Сравнить длину Ломаной и массива Линий: они должны совпасть
            if (b1.getLength() == b1.getLinesLength())
                System.out.println("Длина Ломаной = Длина Массива");
            else
                System.out.println("Длина Ломаной != Длина Массива");
//      6. Сдвинуть координату Точки {2,8}
            p2.x += 10;
        } while (p2.x <= 12);
    }
}
