package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.Scanner;

public class DisasterVictim extends Person implements DateValidator{
    public String firstName;
    private String lastName;
    private String dateOfBirth;
    private int age;
    private String comments;
    private final int ASSIGNED_SOCIAL_ID; 
    private List<MedicalRecord> medicalRecords;
    private ArrayList<FamilyRelation> familyConnections; 
    private final String ENTRY_DATE;
    private List<Supply> personalBelongings;
    private String gender;
    private static int counter = 0; 
    private Location location;
    private ArrayList<String> dietaryRestrictions;
    enum DietaryRestrictions{
        AVML,
        DBML,
        GFML,
        KSML,
        LSML,
        MOML,
        PFML,
        VGML,
        VJML
    }

    public DisasterVictim(String firstName, String ENTRY_DATE) {
        this.firstName = firstName;
        if (!isValidDateFormat(ENTRY_DATE)) {
            throw new IllegalArgumentException("Invalid date format for entry date. Expected format: YYYY-MM-DD");
        }
        this.ENTRY_DATE = ENTRY_DATE;
        this.ASSIGNED_SOCIAL_ID = generateSocialID();

    }

    private static int generateSocialID() {
        counter++;
        return counter;
    }

    private static boolean isValidDateFormat(String date) {
        String dateFormatPattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return date.matches(dateFormatPattern);
    }

    // Getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        if (!isValidDateFormat(dateOfBirth)) {
            throw new IllegalArgumentException("Invalid date format for date of birth. Expected format: YYYY-MM-DD");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public int getAssignedSocialID() {
        return ASSIGNED_SOCIAL_ID;
    }

    public ArrayList<FamilyRelation> getFamilyConnections() {
        return this.familyConnections;}

    public MedicalRecord[] getMedicalRecords() {
        return medicalRecords.toArray(new MedicalRecord[0]);
    }

    public List<Supply> getPersonalBelongings() {
        return personalBelongings;
    }

    public void setFamilyConnections(ArrayList<FamilyRelation> connections) {
        this.familyConnections = connections;
    }

    public void setMedicalRecords(List<MedicalRecord> records) {
        this.medicalRecords = new ArrayList<>(records);
    }

    public void setPersonalBelongings(Supply[] belongings) {
        this.personalBelongings = new ArrayList<>(Arrays.asList(belongings));
    }

    public void addPersonalBelonging(Supply supply) {
        if (this.personalBelongings == null) {
            this.personalBelongings = new ArrayList<>();
            return;
        }
        if (location.getOccupants().contains(this) && location.getSupplies().contains(supply)) {   
            this.personalBelongings.add(supply);
            int position = location.getSupplies().indexOf(supply);
            location.getSupplies().get(position).setQuantity(location.getSupplies().get(position).getQuantity() - 1);  
            if (location.getSupplies().get(position).getQuantity() == 0) {
                location.removeSupply(supply); 
            }}
    }

    public void removePersonalBelonging(Supply unwantedSupply) {
         if (this.personalBelongings != null) {

            this.personalBelongings.remove(unwantedSupply);
        }
    }

    public void removeFamilyConnection(FamilyRelation exRelation) {
        familyConnections.remove(exRelation);
    }

    public void addFamilyConnection(FamilyRelation familyConnection) throws IllegalArgumentException {  // throws exception
        if (familyConnection.getPersonOne().getFamilyConnections() == null && familyConnection.getPersonTwo().getFamilyConnections() == null) {
            
            if (this.familyConnections == null) {      // checks if the victim has any family connections
                this.familyConnections = new ArrayList<FamilyRelation>();
                this.familyConnections.add(familyConnection);
            }
            else {
                this.familyConnections.add(familyConnection);
            }

            if (this == familyConnection.getPersonOne()) {  // checks if the victim is the first person in the family connection
                if (familyConnection.getPersonTwo().getFamilyConnections() == null) {
                    ArrayList<FamilyRelation> newRelation = new ArrayList<FamilyRelation>();
                    familyConnection.getPersonTwo().setFamilyConnections(newRelation);
                    familyConnection.getPersonTwo().getFamilyConnections().add(familyConnection);
                }
                else {
                    familyConnection.getPersonTwo().getFamilyConnections().add(familyConnection);   // adds the family connection to the second person in the family connection
                }
            }
            else {
                if (familyConnection.getPersonOne().getFamilyConnections() == null) {   // checks if the second person in the family connection has any family connections
                    ArrayList<FamilyRelation> newRelation = new ArrayList<FamilyRelation>();
                    familyConnection.getPersonOne().setFamilyConnections(newRelation);
                    familyConnection.getPersonOne().getFamilyConnections().add(familyConnection);
                }
                else {  // adds the family connection to the first person in the family connection
                    familyConnection.getPersonOne().getFamilyConnections().add(familyConnection);
                }
            }
            return;
        }

        if (familyConnection.getPersonOne().getFamilyConnections() != null) {   // checks if the first person in the family connection has any family connections
            for (int i = 0; i < familyConnection.getPersonOne().getFamilyConnections().size(); i++) {   
                if (familyConnection.getPersonOne().getFamilyConnections().get(i).getPersonOne() != familyConnection.getPersonTwo() && familyConnection.getPersonOne().getFamilyConnections().get(i).getPersonTwo() != familyConnection.getPersonTwo() ) {
                    if (this.familyConnections == null) {
                        this.familyConnections = new ArrayList<FamilyRelation>();
                        this.familyConnections.add(familyConnection);
                    }
                    else {
                        this.familyConnections.add(familyConnection);
                    }
        
                    if (this == familyConnection.getPersonOne()) {  // checks if the victim is the first person in the family connection
                        if (familyConnection.getPersonTwo().getFamilyConnections() == null) {
                            ArrayList<FamilyRelation> newRelation = new ArrayList<FamilyRelation>();
                            familyConnection.getPersonTwo().setFamilyConnections(newRelation);
                            familyConnection.getPersonTwo().getFamilyConnections().add(familyConnection);
                        }
                        else {  // adds the family connection to the second person in the family connection
                            familyConnection.getPersonTwo().getFamilyConnections().add(familyConnection);
                        }
                    }
                    else {  // adds the family connection to the first person in the family connection
                        if (familyConnection.getPersonOne().getFamilyConnections() == null) {   // checks if the second person in the family connection has any family connections
                            ArrayList<FamilyRelation> newRelation = new ArrayList<FamilyRelation>();
                            familyConnection.getPersonOne().setFamilyConnections(newRelation);
                            familyConnection.getPersonOne().getFamilyConnections().add(familyConnection);
                        }
                        else {  // adds the family connection to the first person in the family connection
                            familyConnection.getPersonOne().getFamilyConnections().add(familyConnection);
                        }
                    }
                    return;
                }
            }
            throw new IllegalArgumentException();   // throws exception if the family connection already exists
        }
        else {
            for (int i = 0; i < familyConnection.getPersonTwo().getFamilyConnections().size(); i++) {   // checks if the second person in the family connection has any family connections
                if (familyConnection.getPersonTwo().getFamilyConnections().get(i).getPersonOne() != familyConnection.getPersonOne() && familyConnection.getPersonTwo().getFamilyConnections().get(i).getPersonTwo() != familyConnection.getPersonOne()) {
                    if (this.familyConnections == null) {   // checks if the victim has any family connections
                        this.familyConnections = new ArrayList<FamilyRelation>();
                        this.familyConnections.add(familyConnection);
                    }
                    else {  // adds the family connection to the victim
                        this.familyConnections.add(familyConnection);
                    }
        
                    if (this == familyConnection.getPersonOne()) {  // checks if the victim is the first person in the family connection
                        if (familyConnection.getPersonTwo().getFamilyConnections() == null) {
                            ArrayList<FamilyRelation> newRelation = new ArrayList<FamilyRelation>();
                            familyConnection.getPersonTwo().setFamilyConnections(newRelation);
                            familyConnection.getPersonTwo().getFamilyConnections().add(familyConnection);
                        }
                        else {  // adds the family connection to the second person in the family connection
                            familyConnection.getPersonTwo().getFamilyConnections().add(familyConnection);
                        }
                    }
                    else {  //  adds the family connection to the first person in the family connection
                        if (familyConnection.getPersonOne().getFamilyConnections() == null) {
                            ArrayList<FamilyRelation> newRelation = new ArrayList<FamilyRelation>();
                            familyConnection.getPersonOne().setFamilyConnections(newRelation);
                            familyConnection.getPersonOne().getFamilyConnections().add(familyConnection);
                        }
                        else {  // adds the family connection to the first person in the family connection
                            familyConnection.getPersonOne().getFamilyConnections().add(familyConnection);
                        }
                    }
                    return;
                }
            }
            throw new IllegalArgumentException();   // throws exception if the family connection already exists
        }
    }
    

    public void addMedicalRecord(MedicalRecord record) {
        medicalRecords.add(record);
    }

    public String getEntryDate() {
        return ENTRY_DATE;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getGender() {

        return gender;
    }

    public void setGender(String gender) {
        GenderFileIO genderTextFile = new GenderFileIO("/Users/azlfaanwar/Downloads/IA2/edu/ucalgary/oop/GenderOptions.txt");
        // No try-catch block for IOException needed as readGenderTextFile handles it internally
        if (genderTextFile.readGenderTextFile().contains(gender.toLowerCase())) {
            this.gender = gender.toLowerCase();
        } else {
            System.out.println("Invalid gender option: " + gender);
        }
    }
    public String validateDate(String date) {
        Pattern dateOfBirth_pat = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        Matcher myMatcher = dateOfBirth_pat.matcher(date);
        if(myMatcher.find()) {  // checks if the date is in the correct format
            return date;
        }
        else {
            throw new IllegalArgumentException(); }  // throws exception if the date is not in the correct format
        }
    public void addDietaryRestriction(String diet) throws IllegalArgumentException {
            for (DietaryRestrictions d : DietaryRestrictions.values()) {
                if (diet.equalsIgnoreCase(d.toString())) { // Using equalsIgnoreCase for case-insensitive comparison
                    if (dietaryRestrictions == null) {
                        dietaryRestrictions = new ArrayList<String>();
                    }
                    dietaryRestrictions.add(diet); // No need to convert to uppercase
                    return;
                }
            }
            throw new IllegalArgumentException("Invalid dietary restriction: " + diet); // Including a descriptive message in the exception
        }
        public void removeDietaryRestriction(String diet) {
            if (this.dietaryRestrictions != null) {
                this.dietaryRestrictions.remove(diet);
            }
        }

        

}
