package com.example.sgpa.domain.entities.user;

public class User {
    private int institutionalId;
    private String name;
    private String email;
    private String phone;
    private UserType userType;
    private int room;
    private String login;
	public User() {
    }
    public User(int institutionalId) {
        this.institutionalId = institutionalId;
    }
    public User(int institutionalId, String name, String email, String phone, UserType userType) {
        this.institutionalId = institutionalId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
    }
    public User(int institutionalId, String name, String email, String phone, UserType userType, Integer room) {
        this.institutionalId = institutionalId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
        this.room =room;
    }
    public User(int institutionalId, String name, String email, String phone, UserType userType, String login) {
        this.institutionalId = institutionalId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
        this.login = login;
    }
    public int getInstitutionalId() {
        return institutionalId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
    public void setInstitutionalId(int institutionalId) {
        this.institutionalId = institutionalId;
    }
    public int getRoom() {
        return room;
    }
    public void setRoom(int room) {
        this.room = room;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
}
