package edu.ucalgary.oop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading gender options from a text file.
 * This class demonstrates a less modern approach for educational purposes
 * or compatibility with older Java versions.
 */
public class GenderFileIO {
    private String filepath;

    /**
     * Constructor for the GenderFileIO.
     *
     * @param filepath the path to the gender options text file
     */
    public GenderFileIO(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Reads gender options from a file and returns them as a list of strings.
     * Each line in the file is considered one gender option.
     *
     * @return a list of gender options from the file
     */
    public List<String> readGenderTextFile() {
        List<String> genderOptions = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = reader.readLine()) != null) {
                genderOptions.add(line.trim()); // Trim whitespace from each line
            }
        } catch (IOException e) {
            // Log error to standard error stream if file cannot be read
            System.err.println("Error reading gender options from file: " + filepath);
        } finally {
            if (reader != null) {
                try {
                    reader.close(); // Ensure the BufferedReader is closed
                } catch (IOException e) {
                    // Log error if closing the reader fails
                    System.err.println("Failed to close the file reader: " + filepath);
                }
            }
        }
        return genderOptions;
    }

    public static void main(String[] args) {
        String filepath = "/Users/azlfaanwar/Downloads/IA2/edu/ucalgary/oop/GenderOptions.txt";
        GenderFileIO genderFileIO = new GenderFileIO(filepath);
        List<String> genderOptions = genderFileIO.readGenderTextFile();
        
        // Print the gender options to the console
        System.out.println("Gender Options:");
        for (String option : genderOptions) {
            System.out.println(option);
        }
    }
}
