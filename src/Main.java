import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите текст и нажмите <Enter>:");
        String text = new Scanner(System.in).nextLine();
        System.out.println("Длина текста: " + text.length());

        int n = 0;
        while (true) {
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();
            if (!fileExists || isDirectory) {
                System.out.println("Файл не существует или указанный путь является путём к папке, а не к файлу.");
                continue;
            } else {
                n++;
                System.out.println("Путь указан верно.");
                System.out.println("Это файл номер: " + n);
            }
            int counter = 0;
            int minl = 0;
            int maxl = 0;
            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    counter++;
                    if (length > 1024)
                        throw new TooLongLineException("В файле встретилась строка длиннее 1024 символов");
                    if (maxl == minl || length <= minl) minl = length;
                    if (length >= maxl) maxl = length;
                }
                System.out.println("Общее количество строк в файле:" + counter);
                System.out.println("Длина самой длинной строки в файле:" + maxl);
                System.out.println("Длина самой короткой строки в файле:" + minl);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public static class TooLongLineException extends RuntimeException {
        public TooLongLineException(String message) {
            super(message);
        }
    }
}