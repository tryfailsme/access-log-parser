import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
            String resultString = null;
            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    if (length > 1024)
                        throw new TooLongLineException("В файле встретилась строка длиннее 1024 символов");
                    if (maxl == minl || length <= minl) minl = length;
                    if (length >= maxl) maxl = length;
                    counter++;

//                  Получаю User-Agent
                    resultString = line.substring(line.lastIndexOf("\" \"") + 3, line.lastIndexOf('"'));
                    System.out.println(resultString);

//                  Пройти шаги из задания отделения части бота не представляется возможным, так как
//                  1) Бот может быть указан не в первых скобках
//                  2) Бот после использования split может быть в 2й и 3й позиции
//                  3) Некоторые боты не имеют слэшей.
//                  В следствии чего, дабы сохранить минимально совпадение по описываемым шагам, я на раннем этапе решил отсеить ненужные записи.
                    if (resultString.contains("YandexBot") || resultString.contains("Googlebot")) {

//                      Выделите часть, которая находится в первых скобках (боты не всгда в первых скобках, поэтому выделяю через модификатор)
                        resultString = resultString.substring(resultString.lastIndexOf("compatible;") + 1, resultString.lastIndexOf(')'));

//                      Разделяю эту часть по точке с запятой
                        String[] botParts = resultString.split(";");

//                      Очистьте от пробелов каждый получившийся фрагмент;
//                      Возьмите второй фрагмент;
//                      Отделите в этом фрагменте часть до слэша.
                        resultString = botParts[1].substring(1, botParts[1].lastIndexOf('/'));
                        switch (resultString) {
                            case "YandexBot":
                                yandexBot++;
                                break;
                            case "Googlebot":
                                googleBot++;
                                break;
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                System.out.println("Общее количество строк в файле:" + counter);
                System.out.println("YandexBot: " + yandexBot + "/" + counter);
                System.out.println("GoogleBot: " + googleBot + "/" + counter);
            }
        }
    }

    public static class TooLongLineException extends Exception {
        public TooLongLineException(String message) {
            super(message);
        }
    }
}
