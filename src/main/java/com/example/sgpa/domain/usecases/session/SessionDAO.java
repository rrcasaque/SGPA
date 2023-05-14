package com.example.sgpa.domain.usecases.session;

import com.example.sgpa.domain.entities.Session.Session;
import com.example.sgpa.domain.usecases.utils.DAO;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionDAO extends DAO<Session, Integer> {
    List<Session> findByPeriod(LocalDateTime startingAt, LocalDateTime finishingAt);
}
