package com.example.sgpa.domain.entities.Session;

import com.example.sgpa.domain.entities.user.Technician;
import com.example.sgpa.domain.entities.user.User;

public class Session {
    private static Session instance;
    private static User loggedTechnician;
    private Session(User technician){
        loggedTechnician = technician;
    }
    public static void makeInstance(User technician){
        if (instance == null){
            instance = new Session(technician);
        }
    }
    public static User getLoggedTechnician() {
        if (instance == null){
            throw new RuntimeException("there is no active session.");
        }
        return instance.getTechnician();
    }
    private User getTechnician(){
        return loggedTechnician;
    }
}
