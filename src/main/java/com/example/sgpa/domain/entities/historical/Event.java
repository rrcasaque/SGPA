package com.example.sgpa.domain.entities.historical;

import com.example.sgpa.domain.entities.part.PartItem;
import com.example.sgpa.domain.entities.user.User;
import javafx.fxml.FXML;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event {
    private int id;
    private final EventType eventType;
    private final LocalDateTime timeStamp;
    private final User requester;
    private final User technician;
    private final PartItem itemPart;
    public Event(EventType type, User requester, User technician, PartItem itemPart) {
        this.eventType = type;
        this.requester = requester;
        this.technician = technician;
        this.itemPart = itemPart;
        this.timeStamp = LocalDateTime.now();
    }
    public EventType getEventType() {
        return eventType;
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
    public User getTechnician() {
        return technician;
    }
    public PartItem getItemPart() {
        return itemPart;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Integer getPatrimonialId(){
        return itemPart.getPatrimonialId();
    }
    public String getPartType(){
        return itemPart.getType();
    }
    public String getRequesterName(){
        return requester.getName();
    }
    public String getTechnicianName(){
        return technician.getName();
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
