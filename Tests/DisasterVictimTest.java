package edu.ucalgary.oop;

import static org.junit.Assert.*;
import java.beans.Transient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.helpers.LocatorImpl;


public class DisasterVictimTest {
    private DisasterVictim victim;
    private List<Supply> suppliesToSet;
    private String expectedFirstName = "Eloise";
    private String EXPECTED_ENTRY_DATE = "2024-04-08";
    private String invalidDate = "15/13/2022";
    private String expectedComments = "Needs medical attention and speaks 2 languages";

    @Before
    public void setUp() {
        victim = new DisasterVictim(expectedFirstName, EXPECTED_ENTRY_DATE);
        suppliesToSet = new ArrayList<>();
        suppliesToSet.add(new Supply("Water Bottle", 10));
        suppliesToSet.add(new Supply("Blanket", 5));
    }

    @Test
    public void testConstructorWithValidEntryDate() {
        String validEntryDate = "2024-01-18";
        DisasterVictim victim = new DisasterVictim("Freda", validEntryDate);
        assertNotNull("Constructor should successfully create an instance with a valid entry date", victim);
        assertEquals("Constructor should set the entry date correctly", validEntryDate, victim.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidEntryDateFormat() {
        String invalidEntryDate = "18/01/2024"; // Incorrect format according to your specifications
        new DisasterVictim("Freda", invalidEntryDate);
        // Expecting IllegalArgumentException due to invalid date format
    }

    @Test
    public void testSetDateOfBirth() {
        String newDateOfBirth = "1987-05-21";
        victim.setDateOfBirth(newDateOfBirth);
        assertEquals("setDateOfBirth should correctly update the date of birth", newDateOfBirth,
                victim.getDateOfBirth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWithInvalidFormat() {
        victim.setDateOfBirth(invalidDate); // This format should cause an exception
    }

    @Test
    public void testSetAndGetFirstName() {
        String newFirstName = "Alice";
        victim.setFirstName(newFirstName);
        assertEquals("setFirstName should update and getFirstName should return the new first name", newFirstName,
                victim.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        String newLastName = "Smith";
        victim.setLastName(newLastName);
        assertEquals("setLastName should update and getLastName should return the new last name", newLastName,
                victim.getLastName());
    }

    @Test
    public void testGetComments() {
        victim.setComments(expectedComments);
        assertEquals("getComments should return the initial correct comments", expectedComments, victim.getComments());
    }

    @Test
    public void testSetComments() {
        victim.setComments(expectedComments);
        String newComments = "Has a minor injury on the left arm";
        victim.setComments(newComments);
        assertEquals("setComments should update the comments correctly", newComments, victim.getComments());
    }

    @Test
    public void testGetAssignedSocialID() {
        DisasterVictim newVictim = new DisasterVictim("Kash", "2024-01-21");
        int expectedSocialId = newVictim.getAssignedSocialID() + 1;
        DisasterVictim actualVictim = new DisasterVictim("Adeleke", "2024-01-22");

        assertEquals("getAssignedSocialID should return the expected social ID", expectedSocialId,
                actualVictim.getAssignedSocialID());
    }

    @Test
    public void testGetEntryDate() {
        assertEquals("getEntryDate should return the expected entry date", EXPECTED_ENTRY_DATE, victim.getEntryDate());
    }

    @Test
    public void testSetGenderWithValidOption() {
        String newGender = "man";
        victim.setGender(newGender);
        assertEquals("setGender should update and getGender should return the new gender", newGender.toLowerCase(),
                victim.getGender());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetGenderWithInvalidOption() {
        String invalidGender = "dog";
        victim.setGender(invalidGender);
    }

    @Test
    public void testAddFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

        FamilyRelation relation = new FamilyRelation(victim2, "Parent", victim1);
        ArrayList<FamilyRelation> expectedRelations = new ArrayList<>();
        expectedRelations.add(relation);
        victim2.setFamilyConnections(expectedRelations);

        ArrayList<FamilyRelation> testFamily = victim2.getFamilyConnections();
        boolean correct = false;

        if ((testFamily != null) && (testFamily.get(0)== expectedRelations.get(0))) {
            correct = true;
        }
        assertTrue("addFamilyConnection should add a family relationship", correct);
    }

    @Test
    public void testAddPersonalBelonging() {
        Supply newSupply = new Supply("Emergency Kit", 1);
        victim.addPersonalBelonging(newSupply);
        List<Supply> testSupplies = victim.getPersonalBelongings();
        boolean correct = false;

        int i;
        for (i = 0; i < testSupplies.size(); i++) {
            if (testSupplies.get(i) == newSupply) {
                correct = true;
            }
        }
        assertTrue("addPersonalBelonging should add the supply to personal belongings", correct);
    }

    @Test
    public void testRemoveFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
        FamilyRelation relation1 = new FamilyRelation(victim, "sibling", victim1);
        FamilyRelation relation2 = new FamilyRelation(victim, "sibling", victim2);
        ArrayList<FamilyRelation> expectedRelations = new ArrayList<FamilyRelation>();
        expectedRelations.add(relation2);
        //FamilyRelation[] originalRelations = {relation1, relation2};
        ArrayList<FamilyRelation> originalRelations = new ArrayList<FamilyRelation>();
        originalRelations.add(relation1);
        originalRelations.add(relation2);
        victim.setFamilyConnections(originalRelations);

        victim.removeFamilyConnection(relation1);

        ArrayList<FamilyRelation> testFamily = victim.getFamilyConnections();
        boolean found = false;
        for (FamilyRelation relation : testFamily) {
            if (relation.equals(relation1)) {
                found = true;
                break;
            }
        }
        assertFalse("removeFamilyConnection should remove the family member", found);
    }

    @Test
    public void testRemovePersonalBelonging() {
        Supply supplyToRemove = suppliesToSet.get(0);
        victim.addPersonalBelonging(supplyToRemove);
        victim.removePersonalBelonging(supplyToRemove);

    List<Supply> testSupplies = victim.getPersonalBelongings();
        boolean found = false;
        for (Supply supply : testSupplies) {
            if (supply.equals(supplyToRemove)) {
                found = true;
                break;
            }
        }
        assertFalse("removePersonalBelonging should remove the supply from personal belongings", found);
    }
    @Test(expected = IllegalArgumentException.class)
    // test invalid dietary restriction
    public void testInvalidDietaryRestriction() {
        DisasterVictim dietVictim2 = new DisasterVictim("Bill", "2024-01-20");
        dietVictim2.addDietaryRestriction("VGAN");;
    }
    @Test
    // test add dietary restriction
    public void testAddDietaryRestriction() {
       DisasterVictim dietVictim = new DisasterVictim("Azlfa", "2024-10-03");
       dietVictim.addDietaryRestriction("VJML");
       dietVictim.addDietaryRestriction("AVML");
       assertEquals("setDietaryRestrictions should correctly set the field if the input is in the enum dietary restrictions class", 2, dietVictim.getDietaryRestrictions().size());
    }


    @Test
    public void testSetFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
        FamilyRelation relation = new FamilyRelation(victim1, "sibling", victim2);
        ArrayList<FamilyRelation> expectedRelations = new ArrayList<FamilyRelation>();
        expectedRelations.add(relation);
        victim1.setFamilyConnections(expectedRelations);
        boolean correct = true;


        boolean found = false;
        for (FamilyRelation rel : actualRecords.size()) {
            if (rel.equals(relation)) {
                found = true;
                break;
            }
        }
        assertTrue("Family relation should be set", found);
    }

    @Test
    public void testSetMedicalRecords() {
        Location testLocation = new Location("Shelter Z", "1234 Shelter Ave");
        MedicalRecord testRecord = new MedicalRecord(testLocation, "test for strep", "2024-02-09");
        boolean correct = true;

        MedicalRecord[] newRecords = { testRecord };
        victim.setMedicalRecords(Arrays.asList(newRecords));
        MedicalRecord[] actualRecords = victim.getMedicalRecords();

        if (newRecords.length != actualRecords.length) {
            correct = false;
        } else {
            int i;
            for (i = 0; i < newRecords.length; i++) {
                if (!actualRecords[i].equals(newRecords[i])) {
                    correct = false;
                    break;
                }
            }
        }
        assertTrue("setMedicalRecords should correctly update medical records", correct);
    }

    @Test
    public void testSetPersonalBelongings() {
        Supply one = new Supply("Tent", 1);
        Supply two = new Supply("Jug", 3);
        List<Supply> newSupplies = new ArrayList<>();

        newSupplies.add(one);
        newSupplies.add(two);
        boolean correct = true;

        victim.setPersonalBelongings(newSupplies);
        List<Supply> actualSupplies = victim.getPersonalBelongings();
        if (newSupplies.size() != actualSupplies.size()) {
            correct = false;
        } else {
            int i;
            for (i = 0; i < newSupplies.size(); i++) {
                if (!actualSupplies.get(i).equals(newSupplies.get(i))) {
                    correct = false;
                    break;
                }
            }
        }
        assertTrue("setPersonalBelongings should correctly update personal belongings", correct);
    }
    @Test(expected = IllegalArgumentException.class)
    // test age then birthdate
    public void testAgethenBirthdate() {
        DisasterVictim testVictim1 = new DisasterVictim("Bill", "2024-01-20");
        testVictim1.setDateOfBirth("1987-05-21");
    } 

}


