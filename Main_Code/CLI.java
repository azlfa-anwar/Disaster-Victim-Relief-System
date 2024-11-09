package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.Scanner;

public class CLI {
    public static void main(String[] args) {
        ArrayList<Location> sites = initializeLocations(); // Initialize locations

        Scanner scanner = new Scanner(System.in); // Initialize scanner

        while (true) {
            // Display menu options
            System.out.println("--------------------------------------------------");
            System.out.println("Welcome to DisasterVictim CLI");
            System.out.println("1.) See available locations");
            System.out.println("2.) See occupants at a location");
            System.out.println("3.) Add a disaster victim to the system");
            System.out.println("4.) Exit the program");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        displayAvailableLocations(sites);
                        break;
                    case 2:
                        displayOccupants(sites, scanner);
                        break;
                    case 3:
                        addDisasterVictim(sites, scanner);
                        break;
                    case 4:
                        System.out.println("Exiting program.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }

    // Method to initialize locations
    private static ArrayList<Location> initializeLocations() {
        Location siteA = new Location("A", "Ex 123 street SW");
        Location siteB = new Location("B", "Ex 123 street SW");
        Location siteC = new Location("C", "Ex 123 street SW");
        Location siteD = new Location("D", "Ex 123 street SW");

        ArrayList<Location> sites = new ArrayList<>();
        sites.add(siteA);
        sites.add(siteB);
        sites.add(siteC);
        sites.add(siteD);

        return sites;
    }

    // Method to display available locations
    private static void displayAvailableLocations(ArrayList<Location> sites) {
        System.out.println("--------------------------------------------------");
        System.out.println("Available locations: ");
        for (Location site : sites) {
            System.out.println(site.getName());
        }
    }

    // Method to display occupants at a selected location
    private static void displayOccupants(ArrayList<Location> sites, Scanner scanner) {
        System.out.println("--------------------------------------------------");
        System.out.println("Select a location to view occupants: ");
        for (int i = 0; i < sites.size(); i++) {
            System.out.println((i + 1) + ".) " + sites.get(i).getName());
        }
        int choice = scanner.nextInt();
        // Implement logic to display occupants of the selected location
    }

    // Method to add a disaster victim to the system
    private static void addDisasterVictim(ArrayList<Location> sites, Scanner scanner) {
        System.out.println("--------------------------------------------------");
        System.out.println("Enter the location to place the disaster victim: ");
        for (int i = 0; i < sites.size(); i++) {
            System.out.println((i + 1) + ".) " + sites.get(i).getName());
        }
        int locationIndex = scanner.nextInt();
        scanner.nextLine(); // Clear the input buffer
        // Implement logic to add a disaster victim to the selected location
    }
}
