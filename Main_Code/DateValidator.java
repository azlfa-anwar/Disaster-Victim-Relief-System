package edu.ucalgary.oop;

public interface DateValidator {      

    /**
     * Validates a date string and returns a formatted date if valid, otherwise an error message is produced.
     * @param date the date string to validate
     * @return a validated and formatted date string or an error message
     */
    String validateDate(String date);
}
