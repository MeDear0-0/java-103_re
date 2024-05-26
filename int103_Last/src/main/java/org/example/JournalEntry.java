package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JournalEntry {
    private LocalDateTime timestamp;
    private String text;

    public JournalEntry(LocalDateTime timestamp, String text) {
        this.timestamp = timestamp;
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return formatter.format(timestamp) + " - " + text;
    }
}
