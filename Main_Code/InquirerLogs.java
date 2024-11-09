package edu.ucalgary.oop;

import java.util.ArrayList;

/**
 * Represents the history of relief services associated with an inquirer.
 * <p>
 * This class tracks the relief services inquiries made by a specific inquirer,
 * providing access to the inquirer's details and the history of their relief service logs.
 */
public class InquirerLogs {
    private final Inquirer inquirer;
    private final ArrayList<ReliefService> history; // Using ArrayList directly
    
    /**
     * Constructs an instance of InquirerLogs for the specified inquirer.
     * 
     * @param inquirer the inquirer associated with this history
     */
    public InquirerLogs(Inquirer inquirer) {
        this.inquirer = inquirer;
        this.history = new ArrayList<>(); // Instantiated as an ArrayList
    }

    /**
     * Returns the inquirer associated with this history.
     * 
     * @return the inquirer
     */
    public Inquirer getInquirer() {
        return inquirer;
    }

    /**
     * Returns the history of relief service logs.
     * 
     * @return the history as an ArrayList of {@link ReliefService}
     */
    public ArrayList<ReliefService> getHistory() { // Returns an ArrayList
        return history;
    }

    /**
     * Adds a relief service log to the history.
     * 
     * @param log the relief service log to add
     */
    public void addLog(ReliefService log) {
        history.add(log);
    }
}
