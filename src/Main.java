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
            int yandexBot = 0, googleBot = 0, maxl = 0, minl = 0, counter = 0;
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

//                  Получаю User-Agent
                    line = line.substring(line.lastIndexOf("\" \"") + 3, line.lastIndexOf('"'));
//                  Сразу отсеиваю строки без ботов
                    if (line.contains("YandexBot") || line.contains("Googlebot")) {
//                      Выделите часть, которая находится в первых скобках (боты не всегда в первых скобках, поэтому выделяю через модификатор)
                        line = line.substring(line.lastIndexOf("compatible;"), line.lastIndexOf(')'));
//                      Разделяю эту часть по точке с запятой
                        String[] botParts = line.split(";");
//                      Возьмите второй фрагмент;
//                      Отделите в этом фрагменте часть до слэша.
                        line = botParts[1].substring(1, botParts[1].lastIndexOf('/'));
                        switch (line) {
                            case "YandexBot":
                                yandexBot++;
                                break;
                            case "Googlebot":
                                googleBot++;
                                break;
                        }
                    }
                }
                System.out.println("Общее количество строк в файле:" + counter);
                System.out.println("YandexBot: " + yandexBot + "/" + counter);
                System.out.println("GoogleBot: " + googleBot + "/" + counter);
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