/**
 * @author Azlfa Anwar
 * InquirerDataRetriever is a utility class for retrieving details of inquirers from a database.
 * It connects to a MySQL database and executes a query to fetch data from the 'Inquirer' table.
 * The retrieved details include the inquirer's ID, first name, last name, phone number, and additional information.
 */
package edu.ucalgary.oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InquirerDataRetriever {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ensf380project";
    private static final String USERNAME = "oop";
    private static final String PASSWORD = "ucalgary";

    /**
     * The main method of the InquirerDataRetriever class.
     * It connects to the database and retrieves inquirer details by calling fetchInquirerDetails() method.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        fetchInquirerDetails();
    }

    /**
     * Connects to the database and retrieves details of inquirers from the 'Inquirer' table.
     * Prints the retrieved details.
     */
    private static void fetchInquirerDetails() {
        String query = "SELECT * FROM Inquirer";

        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // Goes through the result set and print inquirer details
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phoneNumber = rs.getString("phoneNumber");
                String info = rs.getString("info");

                // Print inquirer details
                System.out.println("Inquirer ID: " + id);
                System.out.println("Inquirer First Name: " + firstName);
                System.out.println("Inquirer Last Name: " + lastName);
                System.out.println("Inquirer Phone Number: " + phoneNumber);
                System.out.println("Inquirer Info: " + info);
                System.out.println("-------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
