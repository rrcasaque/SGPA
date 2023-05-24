package com.example.sgpa.application.repository.inmemory;

import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.historical.EventDAO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class InMemoryEventDAO implements EventDAO {
    private static final Map<Integer, Event> events = new HashMap<>();
    private static int nbr_events;
    @Override
    public Integer create(Event event) {
        nbr_events++;
        events.put(nbr_events, event);
        return nbr_events;
    }
    @Override
    public Optional<Event> findOne(Integer key) {
        return events.values().stream().filter(event -> event.getId() == key).findFirst();
    }
    @Override
    public List<Event> findAll() {
        return events.values().stream().toList();
    }
    @Override
    public void update(Event event) {
        if (!events.containsKey(event.getId()))
           throw new RuntimeException("There is no such event in data base to be updated.");
        events.put(event.getId(), event);
    }
    @Override
    public boolean delete(Event event) {
        return events.remove(event.getId(), event);
    }
	@Override
	public List<Event> getReportByDate(LocalDateTime start, LocalDateTime end) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Event> getReportByUser(User user, LocalDateTime start, LocalDateTime end) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Event> getReportByPart(Part part, LocalDateTime start, LocalDateTime end) {
		// TODO Auto-generated method stub
		return null;
	}
}
