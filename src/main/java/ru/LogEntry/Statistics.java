package ru.LogEntry;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Statistics {
    private long totalTraffic;
    private int totalOS;
    private LocalDateTime minTime, maxTime;
    private final HashSet<String> visitedPages = new HashSet<>();
    private final HashMap<String, Integer> frequencyOS = new HashMap<>();

    public Statistics() {
        this.totalTraffic = 0L;
        this.minTime = null;
        this.maxTime = null;
    }

    public void addEntry(LogEntry entry) {
        if (minTime == null || minTime.isAfter(entry.getRequestDate())) minTime = entry.getRequestDate();
        if (maxTime == null || maxTime.isBefore(entry.getRequestDate())) maxTime = entry.getRequestDate();
        totalTraffic += entry.getSize();

        if (entry.getResponseCode() == 200) visitedPages.add(entry.getPath());
        String os = entry.getUserAgent().getOperatingSystem();
        frequencyOS.put(os, frequencyOS.getOrDefault(os, 0) + 1);
        totalOS++;
    }

    public int getTrafficRate() {
        return (int) (totalTraffic * 60 * 60 / (double) Duration.between(minTime, maxTime).toSeconds());
    }

    public Set<String> getAllVisitedPages() {
        return new HashSet<>(visitedPages);
    }

    public Map<String, Double> getFrequencyOS() {
        Map<String, Double> percentageOS = new HashMap<>();
        for (HashMap.Entry<String, Integer> entry : frequencyOS.entrySet()) {
            double percentage = (double) entry.getValue() / totalOS;
            percentageOS.put(entry.getKey(), percentage);
        }
        return percentageOS;
    }
}