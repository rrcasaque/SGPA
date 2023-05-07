package com.example.sgpa.domain.entities.historical;

import java.time.LocalDateTime;

public class Event {
    private EventType type;
    private final LocalDateTime timeStamp;

    public Event(EventType type) {
        this.type = type;
        this.timeStamp = LocalDateTime.now();
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

}
