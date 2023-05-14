package com.example.sgpa.domain.entities.Session;

import com.example.sgpa.domain.entities.user.Technician;

public class Session {
    private static Session instance;
    private static Technician loggedTechnician;
    private Session(Technician technician){
        this.loggedTechnician = technician;
    }
    public static void makeInstance(Technician technician){
        if (instance == null){
            instance = new Session(technician);
        }
    }
    public static Technician getLoggedTechnician() {
        if (instance == null){
            throw new RuntimeException("there is no active session.");
        }
        return instance.getTechnician();
    }
    private Technician getTechnician(){
        return loggedTechnician;
    }
}
