package ru.LogEntry;

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.ZoneOffset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Statistics {
    private long totalTraffic;
    private int entries, userVisits, errorsCounter;
    private LocalDateTime minTime, maxTime;
    private final HashSet<String> visitedPages, nonExistentPages, referers;
    private final HashMap<String, Integer> frequencyOS, frequencyBrowsers, uniqueUserVisits;
    private final HashMap<Long, Integer> perSecVisits;

    public Statistics() {
        this.totalTraffic = 0L;
        this.errorsCounter = 0;
        this.userVisits = 0;
        this.minTime = null;
        this.maxTime = null;
        this.visitedPages = new HashSet<>();
        this.nonExistentPages = new HashSet<>();
        this.referers = new HashSet<>();
        this.frequencyOS = new HashMap<>();
        this.frequencyBrowsers = new HashMap<>();
        this.perSecVisits = new HashMap<>();
        this.uniqueUserVisits = new HashMap<>();
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
            uniqueUserVisits.merge(entry.getIp(), 1, Integer::sum);

            long epoch = entry.getRequestDate().toEpochSecond(ZoneOffset.UTC);
            perSecVisits.put(epoch, perSecVisits.getOrDefault(epoch, 0) + 1);
        }

        if (entry.getReferer() != null) referers.add(getDomain(entry.getReferer()));
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
        return (int) (userVisits / (double) uniqueUserVisits.size());
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

    public Integer getPeakSec() {
        return perSecVisits.values().stream().max(Integer::compareTo).orElse(0);
    }

    public Set<String> getReferers() {
        return new HashSet<>(referers);
    }

    public Map.Entry<String, Integer> getMaxVisitsUser() {
        return uniqueUserVisits.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);
    }

    private Map<String, Double> frequencyCounter(Map<String, Integer> frequency) {
        Map<String, Double> percentages = new HashMap<>();
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            double percentage = (double) entry.getValue() / entries;
            percentages.put(entry.getKey(), percentage);
        }
        return percentages;
    }

    private String getDomain(String referer) {
        Pattern pattern = Pattern.compile("(?:https?:\\/\\/|https%3A%2F%2F)(?:www\\.)?([^\\/&%]+)");
        Matcher matcher = pattern.matcher(referer);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}