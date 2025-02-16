package ru.LogEntry;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Statistics {
    private long totalTraffic;
    private int entries, userVisits, errorsCounter;
    private LocalDateTime minTime, maxTime;
    private final HashSet<String> visitedPages, nonExistentPages, uniqueUsers;
    private final HashMap<String, Integer> frequencyOS, frequencyBrowsers;

    public Statistics() {
        this.totalTraffic = 0L;
        this.errorsCounter = 0;
        this.userVisits = 0;
        this.minTime = null;
        this.maxTime = null;
        this.visitedPages = new HashSet<>();
        this.nonExistentPages = new HashSet<>();
        this.uniqueUsers = new HashSet<>();
        this.frequencyOS = new HashMap<>();
        this.frequencyBrowsers = new HashMap<>();
    }

    public void addEntry(LogEntry entry) {
        if (minTime == null || minTime.isAfter(entry.getRequestDate())) minTime = entry.getRequestDate();
        if (maxTime == null || maxTime.isBefore(entry.getRequestDate())) maxTime = entry.getRequestDate();
        totalTraffic += entry.getSize();

        UserAgent a = entry.getUserAgent();
        int code = entry.getResponseCode();
        if (code == 200) visitedPages.add(entry.getPath());
        else if (code == 404) {
            nonExistentPages.add(entry.getPath());
            errorsCounter++;
        }
        else if (code >= 400 && code < 600) errorsCounter++;

        frequencyOS.put(a.getOperatingSystem(), frequencyOS.getOrDefault(a.getOperatingSystem(), 0) + 1);
        frequencyBrowsers.put(a.getBrowser(), frequencyBrowsers.getOrDefault(a.getBrowser(), 0) + 1);

        if (!a.isBot()) {
            userVisits++;
            uniqueUsers.add(entry.getIp());
        }

        entries++;
    }

    public int getTrafficRate() {
        return (int) (totalTraffic * 60 * 60 / (double) Duration.between(minTime, maxTime).toSeconds());
    }
    public int getAverageUsers() {
        return (int) (userVisits * 60 * 60 / (double) Duration.between(minTime, maxTime).toSeconds());
    }
    public int getAverageErrors() {
        return (int) (errorsCounter * 60 * 60 / (double) Duration.between(minTime, maxTime).toSeconds());
    }
    public int getAveragePerUser() {
        return (int) (userVisits / (double) uniqueUsers.size());
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