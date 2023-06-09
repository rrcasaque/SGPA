package com.example.sgpa.domain.usecases.historical;

import java.time.LocalDateTime;
import java.util.List;

import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.entities.part.Part;
import com.example.sgpa.domain.entities.user.User;
import com.example.sgpa.domain.usecases.utils.DAO;

public interface EventDAO extends DAO<Event, Integer> {
	List<Event> getReportByDate(LocalDateTime start, LocalDateTime end);
    List<Event> getReportByUser(int userId, LocalDateTime start, LocalDateTime end);
    List<Event> getReportByPart(int patrimonialId, LocalDateTime start, LocalDateTime end);
}
