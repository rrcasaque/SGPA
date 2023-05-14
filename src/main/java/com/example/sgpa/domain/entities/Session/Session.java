package com.example.sgpa.domain.entities.Session;

import com.example.sgpa.domain.entities.historical.Event;
import com.example.sgpa.domain.entities.user.Technician;

import java.time.LocalDateTime;
import java.util.Set;
public class Session {
    private static int sessionId;
    private static Technician loggedTechnician;
    private static LocalDateTime loginMoment;
    private static LocalDateTime logoutMoment;

    public Session(Technician technician){
        this.loggedTechnician = technician;
        this.loginMoment = LocalDateTime.now();
    }

    public Session(int sessionId, Technician loggedTechnician, LocalDateTime loginMoment, LocalDateTime logoutMoment) {
        this.sessionId = sessionId;
        this.loggedTechnician = loggedTechnician;
        this.loginMoment = loginMoment;
        this.logoutMoment = logoutMoment;
    }

    public static int getSessionId() {
        return sessionId;
    }

    public static Technician getLoggedTechnician() {
        return loggedTechnician;
    }

    public static LocalDateTime getLoginMoment() {
        return loginMoment;
    }

    public static LocalDateTime getLogoutMoment() {
        return logoutMoment;
    }

    public  void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public  void setLogoutMoment(LocalDateTime logoutMoment) {
        this.logoutMoment = logoutMoment;
    }



}
