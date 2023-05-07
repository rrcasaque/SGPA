package com.example.sgpa.domain.entities.reservation;

import com.example.sgpa.domain.entities.part.ItemPart;
import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.entities.user.Technician;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.entities.valueobjects.stringvalueobjects.TechnicianLogin;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Reservation {
    private int id;
    private LocalDateTime dateTimeScheduledForCheckout;
    private User requester;
    private Technician technician;
    private final Set<ItemPart> items = new HashSet<>();

    public Reservation(LocalDateTime dateTimeScheduledForCheckout,
                       User requester, Technician technician) {
        this.dateTimeScheduledForCheckout = dateTimeScheduledForCheckout;
        this.requester = requester;
        this.technician = technician;
    }

    public Reservation(int id, LocalDateTime dateTimeScheduledForCheckout,
                       User requester, Technician technician) {
        this(dateTimeScheduledForCheckout, requester,  technician);
        this.id = id;
    }

    public void setDateTimeScheduledForCheckout(LocalDateTime dateTimeScheduledForCheckout) {
        this.dateTimeScheduledForCheckout = dateTimeScheduledForCheckout;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTimeScheduledForCheckout() {
        return dateTimeScheduledForCheckout;
    }

    public User getRequester() {
        return requester;
    }

    public Technician getTechnician() {
        return technician;
    }

    public Set<ItemPart> getItems() {
        return items;
    }

    public void addItem(ItemPart item){
        items.add(item);
    }

    public void addItems(List<ItemPart> items){
        items.addAll(items);
    }

    public void removeItem(ItemPart item){
        items.remove(item);
    }

    public void removeItems(List<ItemPart> items){
        items.removeAll(items);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
