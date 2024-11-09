package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

/**
 * Tests for the GenderFileIO class.
 * These tests aim to cover various scenarios, including valid files, invalid files,
 * and edge cases like empty files or files with unusual content.
 */
public class GenderFileIOTest {

    // Tests assume the existence of specific test files in the same directory.
    private static final String VALID_FILE_PATH = "/Users/azlfaanwar/Downloads/IA2/edu/ucalgary/oop/GenderOptions.txt";
    private static final String INVALID_FILE_PATH = "NonExistentFile.txt";
    private static final String EMPTY_FILE_PATH = "EmptyGenderOptions.txt";
    private static final String WHITESPACE_ONLY_FILE_PATH = "WhitespaceOnlyGenderOptions.txt";

    /**
     * Verifies that a GenderFileIO object is not null after instantiation.
     */
    @Test
    public void objectCreationNotNull() {
        GenderFileIO genderFileIO = new GenderFileIO(VALID_FILE_PATH);
        assertNotNull("GenderFileIO object should be successfully created", genderFileIO);
    }

    /**
     * Tests that a valid file produces a non-empty list of gender options.
     */
    @Test
    public void readValidFileProducesNonEmptyList() {
        GenderFileIO genderFileIO = new GenderFileIO(VALID_FILE_PATH);
        List<String> contents = genderFileIO.readGenderTextFile();
        assertFalse("Reading from a valid file should produce a non-empty list", contents.isEmpty());
    }

    /**
     * Tests that an invalid file path results in an empty list, simulating error handling for missing files.
     */
    @Test
    public void readInvalidFileProducesEmptyList() {
        GenderFileIO genderFileIO = new GenderFileIO(INVALID_FILE_PATH);
        List<String> contents = genderFileIO.readGenderTextFile();
        assertTrue("Attempting to read from an invalid file path should result in an empty list", contents.isEmpty());
    }

    /**
     * Tests that an empty file results in an empty list, checking the class's ability to handle files with no content.
     */
    @Test
    public void readEmptyFileProducesEmptyList() {
        GenderFileIO genderFileIO = new GenderFileIO(EMPTY_FILE_PATH);
        List<String> contents = genderFileIO.readGenderTextFile();
        assertTrue("Reading from an empty file should result in an empty list", contents.isEmpty());
    }

    /**
     * Tests that a file containing only whitespace results in an empty list, assessing the class's handling of non-meaningful content.
     */
    @Test
    public void readWhitespaceOnlyFileProducesEmptyList() {
        GenderFileIO genderFileIO = new GenderFileIO(WHITESPACE_ONLY_FILE_PATH);
        List<String> contents = genderFileIO.readGenderTextFile();
        assertTrue("A file with only whitespace should be treated as empty, resulting in an empty list", contents.isEmpty());
    }


}
