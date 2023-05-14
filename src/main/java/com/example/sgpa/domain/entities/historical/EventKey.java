package com.example.sgpa.domain.entities.historical;

import com.example.sgpa.domain.entities.user.Technician;

import java.time.LocalDateTime;
import java.util.Objects;

public class EventKey {
    LocalDateTime timeStamp;
    Technician technician;

    public EventKey(LocalDateTime timeStamp, Technician technician) {
        this.timeStamp = timeStamp;
        this.technician = technician;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public Technician getTechnician() {
        return technician;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventKey eventKey = (EventKey) o;
        return timeStamp.equals(eventKey.timeStamp) && technician.equals(eventKey.technician);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeStamp, technician);
    }
}
