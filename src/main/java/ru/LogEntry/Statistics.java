package ru.LogEntry;

import java.time.LocalDateTime;
import java.time.Duration;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime, maxTime;

    public Statistics() {
        this.totalTraffic = 0L;
        this.minTime = null;
        this.maxTime = null;
    }
    public void addEntry(LogEntry entry) {
        if (minTime == null || minTime.isAfter(entry.getRequestDate())) minTime = entry.getRequestDate();
        if (maxTime == null || maxTime.isBefore(entry.getRequestDate())) maxTime = entry.getRequestDate();
        totalTraffic += entry.getSize();
    }
    public int getTrafficRate() {
        return (int) (totalTraffic * 60 * 60 / (double) Duration.between(minTime, maxTime).toSeconds());
    }
}