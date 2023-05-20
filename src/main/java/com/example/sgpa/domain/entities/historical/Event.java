package com.example.sgpa.domain.entities.historical;

import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.user.Technician;
import com.example.sgpa.domain.entities.user.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event {
    private int id;
    private EventType type;
    private final LocalDateTime timeStamp;
    private User requester;
    private Technician technician;
    private PartItem itemPart;
    public Event(EventType type, User requester, Technician technician, PartItem itemPart) {
        this.type = type;
        this.requester = requester;
        this.technician = technician;
        this.itemPart = itemPart;
        this.timeStamp = LocalDateTime.now();
    }
    public EventType getType() {
        return type;
    }
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    public int getId() {
        return id;
    }
    public User getRequester() {
        return requester;
    }
    public Technician getTechnician() {
        return technician;
    }
    public PartItem getItemPart() {
        return itemPart;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
