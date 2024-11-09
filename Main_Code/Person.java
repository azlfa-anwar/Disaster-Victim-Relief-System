package edu.ucalgary.oop;

/**
 * Abstract class representing a person.
 * This class serves as a blueprint for creating objects that represent individuals.
 */
public abstract class Person {
    // Attributes of a person
    public String firstName;
    public String lastName;
    public int age;
    public String dateOfBirth;

    /**
     * Getter method for retrieving the first name of the person.
     * @return The first name of the person.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter method for retrieving the last name of the person.
     * @return The last name of the person.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter method for updating the first name of the person.
     * @param firstName The new first name to be set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Setter method for updating the last name of the person.
     * @param lastName The new last name to be set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

  
}
