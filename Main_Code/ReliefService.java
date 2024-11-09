/*
 * @author Azlfa Anwar
 */
package edu.ucalgary.oop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
     * Constructs a ReliefService object.
     * 
     * @param inquirer         The inquirer making the inquiry.
     * @param missingPerson    The missing person being inquired about.
     * @param dateOfInquiry    The date of the inquiry.
     * @param infoProvided     Information provided.
     * @param lastKnownLocation The last known location of the missing victim.
     */
public class ReliefService {
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private LocalDate dateOfInquiry;
    private String infoProvided;
    private Location lastKnownLocation;

    // Constructor
    public ReliefService(Inquirer inquirer, DisasterVictim missingPerson, String validDate, String infoProvided,
            Location lastKnownLocation) {
        this.inquirer = inquirer;
        this.missingPerson = missingPerson;
        setDateOfInquiry(validDate); // Using the setDateOfInquiry method to properly parse the date
        this.infoProvided = infoProvided;
        this.lastKnownLocation = lastKnownLocation;
        inquirer.addLog(this);
    }

    // Getter and setter for inquirer
    public Inquirer getInquirer() {
        return inquirer;
    }

    public void setInquirer(Inquirer inquirer) {
        this.inquirer = inquirer;
    }

    // Getter and setter for missingPerson
    public DisasterVictim getMissingPerson() {
        return missingPerson;
    }

    public void setMissingPerson(DisasterVictim missingPerson) {
        this.missingPerson = missingPerson;
    }

    // Getter and setter for infoProvided
    public String getInfoProvided() {
        return infoProvided;
    }

    public void setInfoProvided(String infoProvided) {
        this.infoProvided = infoProvided;
    }

    public String getDateOfInquiryAsString() {
        return dateOfInquiry.format(DateTimeFormatter.ISO_DATE);
    }

    // Getter and setter for lastKnownLocation
    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    public void setLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }

    public LocalDate getDateOfInquiry() {
        return dateOfInquiry;
    }

    public void setDateOfInquiry(LocalDate date) {
        this.dateOfInquiry = date;
    }

    public void setDateOfInquiry(String dateString) {
        if (isValidDateFormat(dateString)) {
            this.dateOfInquiry = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
        } else {
            throw new IllegalArgumentException("Invalid date format. Expected format: YYYY-MM-DD");
        }
    }

    // Helper method to check if a string matches the YYYY-MM-DD date format
    private boolean isValidDateFormat(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getLogDetails() {
        return "Inquirer: " + inquirer.getFirstName() +
                ", Missing Person: " + missingPerson.getFirstName() +
                ", Date of Inquiry: " + dateOfInquiry +
                ", Info Provided: " + infoProvided +
                ", Last Known Location: " + lastKnownLocation.getName();
    }
/**
 * Validates that the input string is in the YYYY-MM-DD date format.
 *
 * @param date the string to validate as a date
 * @return the input date string if it is valid
 * @throws IllegalArgumentException if the date string does not match the expected format
 */
public String validateDate(String date) {
    
    Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    Matcher matcher = datePattern.matcher(date);

   
    if (matcher.matches()) {
        return date;
    } else {
       
        throw new IllegalArgumentException("Date \"" + date + "\" is invalid. Expected format: YYYY-MM-DD.");
    }
}

}
