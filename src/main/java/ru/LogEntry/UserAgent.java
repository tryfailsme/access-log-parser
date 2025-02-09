package ru.LogEntry;

public class UserAgent {
    private final String operatingSystem, browser;

    public UserAgent(String userAgent) {
        this.operatingSystem = determineOperatingSystem(userAgent);
        this.browser = determineBrowser(userAgent);
    }

    private String determineOperatingSystem(String userAgent) {
        if (userAgent.contains("Mobile")) return "Mobile";
        else if (userAgent.contains("Windows")) return "Windows";
        else if (userAgent.contains("macOS")) return "macOS";
        else if (userAgent.contains("Linux")) return "Linux";
        else return "Other";
    }

    private String determineBrowser(String userAgent) {
        if (userAgent.contains("OPR/") || userAgent.contains("Presto/")) return "Opera";
        else if (userAgent.contains("Edg/")) return "Edge";
        else if (userAgent.contains("Firefox")) return "Firefox";
        else if (userAgent.contains("Chrome")) return "Chrome";
        else return "Other";
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getBrowser() {
        return browser;
    }
}