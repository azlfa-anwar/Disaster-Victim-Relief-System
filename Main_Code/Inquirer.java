package edu.ucalgary.oop;

import java.util.ArrayList;

public class Inquirer {
    private String FIRST_NAME;
    private String LAST_NAME;
    private String INFO;
    private String SERVICES_PHONE;
    private ArrayList<ReliefService> history = new ArrayList<>();

    
    public Inquirer(String firstName, String lastName, String phone, String info) {
        this.FIRST_NAME = firstName; 
        this.LAST_NAME = lastName;
        this.SERVICES_PHONE = phone; 
        this.INFO = info; 
    }

    // Getters
    public String getFirstName() { return FIRST_NAME; }
    public String getLastName() { return LAST_NAME; }
    public String getServicesPhoneNum() { return SERVICES_PHONE; }
    public String getInfo() { return INFO; }
    public ArrayList<ReliefService> getHistory() { return history; }

    // Method to add a relief service log to the history
    public void addLog(ReliefService log) {
        history.add(log);
    }
}
