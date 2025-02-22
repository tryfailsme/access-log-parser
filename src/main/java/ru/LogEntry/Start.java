package ru.LogEntry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Start {
    public static void main(String[] args) {
        Statistics stats = new Statistics();
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/grannev/Downloads/access.log"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LogEntry entry = new LogEntry(line);
                stats.addEntry(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("Средний трафик за час: " + stats.getTrafficRate() + " байт/час");
//        System.out.println("Статистика операционных систем " + stats.getFrequencyOS());
//        System.out.println("Статистика браузерных систем " + stats.getFrequencyBrowsers());
//        System.out.println("Среднее посещений за час " + stats.getAverageUsers());
//        System.out.println("Среднее ошибочных запросов за час " + stats.getAverageErrors());
//        System.out.println("Средняя посещаемость " + stats.getAveragePerUser());
        System.out.println("Пиковая посещаемости в сек: " + stats.getPeakSec());
        System.out.println("Cписок сайтов рефереров: " + stats.getReferers());
        System.out.println("Максимум посещений: " + stats.getMaxVisitsUser());
    }
}