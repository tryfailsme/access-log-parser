package ru.LogEntry;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Statistics {
    private long totalTraffic;
    private int entries;
    private LocalDateTime minTime, maxTime;
    private final HashSet<String> visitedPages, nonExistentPages;
    private final HashMap<String, Integer> frequencyOS, frequencyBrowsers;

    public Statistics() {
        this.totalTraffic = 0L;
        this.minTime = null;
        this.maxTime = null;
        this.visitedPages = new HashSet<>();
        this.nonExistentPages = new HashSet<>();
        this.frequencyOS = new HashMap<>();
        this.frequencyBrowsers = new HashMap<>();
    }

    public void addEntry(LogEntry entry) {
        if (minTime == null || minTime.isAfter(entry.getRequestDate())) minTime = entry.getRequestDate();
        if (maxTime == null || maxTime.isBefore(entry.getRequestDate())) maxTime = entry.getRequestDate();
        totalTraffic += entry.getSize();

        UserAgent a = entry.getUserAgent();
        if (entry.getResponseCode() == 200) visitedPages.add(entry.getPath());
        else if (entry.getResponseCode() == 404) nonExistentPages.add(entry.getPath());

        frequencyOS.put(a.getOperatingSystem(), frequencyOS.getOrDefault(a.getOperatingSystem(), 0) + 1);
        frequencyBrowsers.put(a.getBrowser(), frequencyBrowsers.getOrDefault(a.getBrowser(), 0) + 1);

        entries++;
    }

    public int getTrafficRate() {
        return (int) (totalTraffic * 60 * 60 / (double) Duration.between(minTime, maxTime).toSeconds());
    }

    public Set<String> getAllVisitedPages() {
        return new HashSet<>(visitedPages);
    }

    public Set<String> getNonExistentPages() {
        return new HashSet<>(nonExistentPages);
    }

    public Map<String, Double> getFrequencyOS() {
        return frequencyCounter(frequencyOS);
    }

    public Map<String, Double> getFrequencyBrowsers() {
        return frequencyCounter(frequencyBrowsers);
    }

    private Map<String, Double> frequencyCounter(Map<String, Integer> frequency) {
        Map<String, Double> percentages = new HashMap<>();
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            double percentage = (double) entry.getValue() / entries;
            percentages.put(entry.getKey(), percentage);
        }
        return percentages;
    }
}