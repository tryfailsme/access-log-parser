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
        System.out.println("Средний трафик за час: " + stats.getTrafficRate() + " байт/час");
        System.out.println("Статистика операционных систем "+ stats.getFrequencyOS());
    }
}