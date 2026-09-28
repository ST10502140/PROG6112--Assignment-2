/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.wildliferescueapp;

import java.util.ArrayList;
import java.util.Scanner;

public class WildlifeRescueApp {
    private ArrayList<RescueCase> rescueCases = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        WildlifeRescueApp app = new WildlifeRescueApp();
        app.run();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=======================================");
            System.out.println("WILDLIFE RESCUE OPERATIONS SYSTEM");
            System.out.println("=======================================");
            System.out.println("1. Create Rescue Case");
            System.out.println("2. Search Rescue Case");
            System.out.println("3. Update Rescue Status");
            System.out.println("4. Display All Rescue Cases");
            System.out.println("5. Rescue Report");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": createRescueCase(); break;
                case "2": searchRescueCase(); break;
                case "3": updateRescueStatus(); break;
                case "4": displayAllCases(); break;
                case "5": generateRescueReport(); break;
                case "6": 
                    exit = true; 
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid selection. Enter a number between 1 and 6.");
            }
        }
    }

    public boolean isIdUnique(String id) {
        for (RescueCase c : rescueCases) {
            if (c.getRescueCaseId().equalsIgnoreCase(id)) {
                return false;
            }
        }
        return true;
    }

    private void createRescueCase() {
        System.out.println("\n--- Create Rescue Case ---");
        
        String id;
        while (true) {
            System.out.print("Enter Rescue Case ID: ");
            id = scanner.nextLine().trim();
            if (id.isEmpty()) {
                System.out.println("Error: Rescue Case ID cannot be blank.");
            } else if (!isIdUnique(id)) {
                System.out.println("Error: Rescue Case ID must be unique. ID '" + id + "' exists.");
            } else {
                break;
            }
        }

        System.out.print("Enter Animal Name: ");
        String animalName = scanner.nextLine().trim();

        String species;
        while (true) {
            System.out.print("Enter Species: ");
            species = scanner.nextLine().trim();
            if (!species.isEmpty()) break;
            System.out.println("Error: Species cannot be blank.");
        }

        String location;
        while (true) {
            System.out.print("Enter Rescue Location: ");
            location = scanner.nextLine().trim();
            if (!location.isEmpty()) break;
            System.out.println("Error: Rescue Location cannot be blank.");
        }

        String ranger;
        while (true) {
            System.out.print("Enter Assigned Ranger: ");
            ranger = scanner.nextLine().trim();
            if (!ranger.isEmpty()) break;
            System.out.println("Error: Assigned Ranger cannot be blank.");
        }

        int days = getValidInt("Enter Number of Rescue Days (> 0): ", true);
        double dailyCost = getValidDouble("Enter Daily Care Cost (> 0): ", true);

        System.out.println("\nSelect Rescue Type:");
        System.out.println("1. Injured Animal Rescue");
        System.out.println("2. Orphaned Animal Rescue");
        System.out.println("3. Endangered Species Rescue");
        int typeChoice = getValidInt("Select option (1-3): ", false);

        switch (typeChoice) {
            case 1:
                System.out.print("Enter Injury Description: ");
                String injury = scanner.nextLine().trim();
                double vetCost = getValidDouble("Enter Veterinary Cost: ", false);
                boolean surgery = getValidBoolean("Is Surgery Required? (yes/no): ");
                rescueCases.add(new RescueCase(id, animalName, species, location, ranger, days, dailyCost, injury, vetCost, surgery));
                break;
            case 2:
                int age = getValidInt("Enter Estimated Age (Months): ", false);
                double feedingCost = getValidDouble("Enter Feeding Cost: ", false);
                boolean foster = getValidBoolean("Is Foster Care Required? (yes/no): ");
                rescueCases.add(new RescueCase(id, animalName, species, location, ranger, days, dailyCost, age, feedingCost, foster));
                break;
            case 3:
                System.out.print("Enter Conservation Classification: ");
                String classification = scanner.nextLine().trim();
                double securityCost = getValidDouble("Enter Security Cost: ", false);
                boolean specialist = getValidBoolean("Is Specialist Team Required? (yes/no): ");
                rescueCases.add(new RescueCase(id, animalName, species, location, ranger, days, dailyCost, classification, securityCost, specialist, true));
                break;
            default:
                System.out.println("Invalid rescue type. Creation aborted.");
                return;
        }

        System.out.println("Rescue Case created successfully!");
    }

    private void searchRescueCase() {
        System.out.print("\nEnter Rescue Case ID to search: ");
        String id = scanner.nextLine().trim();
        RescueCase c = findCaseById(id);
        if (c != null) {
            System.out.println("\n--- Case Details Found ---");
            System.out.println(c.generateRescueSummary());
        } else {
            System.out.println("No rescue case found with ID: " + id);
        }
    }

    private void updateRescueStatus() {
        System.out.print("\nEnter Rescue Case ID to update: ");
        String id = scanner.nextLine().trim();
        RescueCase c = findCaseById(id);
        if (c != null) {
            System.out.println("Current Status: " + c.getCurrentRescueStatus());
            System.out.println("1. Start Rescue Operation");
            System.out.println("2. Complete Rescue Operation");
            System.out.println("3. Set Custom Status");
            int opt = getValidInt("Select option (1-3): ", false);
            if (opt == 1) {
                c.startRescueOperation();
                System.out.println("Status updated to: Rescue in Progress");
            } else if (opt == 2) {
                c.completeRescueOperation();
                System.out.println("Status updated to: Completed");
            } else if (opt == 3) {
                System.out.print("Enter New Status: ");
                String newStatus = scanner.nextLine().trim();
                c.setCurrentRescueStatus(newStatus);
                System.out.println("Status updated.");
            }
        } else {
            System.out.println("No rescue case found with ID: " + id);
        }
    }

    private void displayAllCases() {
        if (rescueCases.isEmpty()) {
            System.out.println("\nNo rescue cases stored.");
            return;
        }
        System.out.println("\n--- All Rescue Cases ---");
        for (RescueCase c : rescueCases) {
            System.out.println("----------------------------------------");
            System.out.println(c.generateRescueSummary());
        }
    }

    private void generateRescueReport() {
        System.out.println("\n==========================================");
        System.out.println("WILDLIFE RESCUE REPORT");
        System.out.println("==========================================");
        double totalCost = 0.0;
        for (RescueCase c : rescueCases) {
            System.out.println(c.generateRescueSummary());
            System.out.println("------------------------------------------");
            totalCost += c.calculateTotalCost();
        }
        System.out.println("Total Rescue Cases : " + rescueCases.size());
        System.out.printf("Total Rescue Cost  : R%.2f\n", totalCost);
        System.out.println("==========================================");
    }

    public RescueCase findCaseById(String id) {
        for (RescueCase c : rescueCases) {
            if (c.getRescueCaseId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    private int getValidInt(String prompt, boolean mustBeGreaterThanZero) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                if (mustBeGreaterThanZero && val <= 0) {
                    System.out.println("Error: Numeric values must be greater than zero.");
                    continue;
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer.");
            }
        }
    }

    private double getValidDouble(String prompt, boolean mustBeGreaterThanZero) {
        while (true) {
            System.out.print(prompt);
            try {
                double val = Double.parseDouble(scanner.nextLine().trim());
                if (mustBeGreaterThanZero && val <= 0) {
                    System.out.println("Error: Numeric values must be greater than zero.");
                    continue;
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }

    private boolean getValidBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String val = scanner.nextLine().trim().toLowerCase();
            if (val.equals("yes") || val.equals("y") || val.equals("true")) return true;
            if (val.equals("no") || val.equals("n") || val.equals("false")) return false;
            System.out.println("Error: Enter 'yes' or 'no'.");
        }
    }
}