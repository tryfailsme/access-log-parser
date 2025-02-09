package ru.LogEntry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;

public class LogEntry {
    private final String ip, referer;
    private final LocalDateTime requestDate;
    private final HttpMethod method;
    private final String path;
    private final int responseCode, size;
    private final UserAgent agent;

    public LogEntry(String line) {
        String[] splits = line.split("\"");
        this.ip = splits[0].substring(0,splits[0].indexOf(' '));
        this.requestDate = LocalDateTime.parse(splits[0].substring(splits[0].indexOf('[')+1, splits[0].indexOf("] ")), DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH));
        this.method = HttpMethod.valueOf(splits[1].substring(0, splits[1].indexOf(" /")));
        this.path = splits[1].substring(splits[1].indexOf('/'), splits[1].lastIndexOf(" "));
        this.responseCode = Integer.parseInt(splits[2].split(" ")[1]);
        this.size = Integer.parseInt(splits[2].split(" ")[2]);
        this.referer = splits[3].equals("-") ? null : splits[3];
        this.agent = new UserAgent(splits[5]);
    }

    public String getIp() {
        return ip;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public int getSize() {
        return size;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getUserAgent() {
        return agent;
    }
    public enum HttpMethod {
        GET, HEAD, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE, PATCH
    }
}