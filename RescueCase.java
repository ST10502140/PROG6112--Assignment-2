/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.wildliferescueapp;

public class RescueCase {
    // Shared Attributes
    private String rescueCaseId;
    private String animalName;
    private String species;
    private String rescueLocation;
    private String assignedRanger;
    private int numberOfRescueDays;
    private double dailyCareCost;
    private String currentRescueStatus;
    private String rescueType; // "Injured", "Orphaned", "Endangered"

    // Subclass / Specific Attributes
    private String injuryDescription;
    private double veterinaryTreatmentCost;
    private boolean surgeryRequired;

    private int estimatedAgeMonths;
    private double feedingCost;
    private boolean fosterCareRequired;

    private String conservationClassification;
    private double securityCost;
    private boolean specialistTeamRequired;

    // Constructor for Injured Animal Rescue
    public RescueCase(String rescueCaseId, String animalName, String species,
                      String rescueLocation, String assignedRanger,
                      int numberOfRescueDays, double dailyCareCost,
                      String injuryDescription, double veterinaryTreatmentCost,
                      boolean surgeryRequired) {
        this.rescueCaseId = rescueCaseId;
        this.animalName = animalName;
        this.species = species;
        this.rescueLocation = rescueLocation;
        this.assignedRanger = assignedRanger;
        this.numberOfRescueDays = numberOfRescueDays;
        this.dailyCareCost = dailyCareCost;
        this.currentRescueStatus = "Pending";
        this.rescueType = "Injured Animal Rescue";

        this.injuryDescription = injuryDescription;
        this.veterinaryTreatmentCost = veterinaryTreatmentCost;
        this.surgeryRequired = surgeryRequired;
    }

    // Constructor for Orphaned Animal Rescue
    public RescueCase(String rescueCaseId, String animalName, String species,
                      String rescueLocation, String assignedRanger,
                      int numberOfRescueDays, double dailyCareCost,
                      int estimatedAgeMonths, double feedingCost,
                      boolean fosterCareRequired) {
        this.rescueCaseId = rescueCaseId;
        this.animalName = animalName;
        this.species = species;
        this.rescueLocation = rescueLocation;
        this.assignedRanger = assignedRanger;
        this.numberOfRescueDays = numberOfRescueDays;
        this.dailyCareCost = dailyCareCost;
        this.currentRescueStatus = "Pending";
        this.rescueType = "Orphaned Animal Rescue";

        this.estimatedAgeMonths = estimatedAgeMonths;
        this.feedingCost = feedingCost;
        this.fosterCareRequired = fosterCareRequired;
    }

    // Constructor for Endangered Species Rescue
    public RescueCase(String rescueCaseId, String animalName, String species,
                      String rescueLocation, String assignedRanger,
                      int numberOfRescueDays, double dailyCareCost,
                      String conservationClassification, double securityCost,
                      boolean specialistTeamRequired, boolean isEndangered) {
        this.rescueCaseId = rescueCaseId;
        this.animalName = animalName;
        this.species = species;
        this.rescueLocation = rescueLocation;
        this.assignedRanger = assignedRanger;
        this.numberOfRescueDays = numberOfRescueDays;
        this.dailyCareCost = dailyCareCost;
        this.currentRescueStatus = "Pending";
        this.rescueType = "Endangered Species Rescue";

        this.conservationClassification = conservationClassification;
        this.securityCost = securityCost;
        this.specialistTeamRequired = specialistTeamRequired;
    }

    // Total Cost Calculation
    public double calculateTotalCost() {
        double baseCost = numberOfRescueDays * dailyCareCost;
        switch (rescueType) {
            case "Injured Animal Rescue" -> {
                baseCost += veterinaryTreatmentCost;
                if (surgeryRequired) {
                    baseCost += 5000.00;
                }
            }
            case "Orphaned Animal Rescue" -> {
                baseCost += feedingCost;
                if (fosterCareRequired) {
                    baseCost += 2500.00;
                }
            }
            case "Endangered Species Rescue" -> {
                baseCost += securityCost;
                if (specialistTeamRequired) {
                    baseCost += 8000.00;
                }
            }
            default -> {
            }
        }
        return baseCost;
    }

    // Priority Determination
    public String determinePriority() {
        switch (rescueType) {
            case "Injured Animal Rescue" -> {
                if (surgeryRequired || veterinaryTreatmentCost > 10000) {
                    return "Critical";
                } else if (veterinaryTreatmentCost > 3000) {
                    return "High";
                }
            }
            case "Orphaned Animal Rescue" -> {
                if (estimatedAgeMonths < 3 || fosterCareRequired) {
                    return "High";
                }
            }
            case "Endangered Species Rescue" -> {
                if ("Critically Endangered".equalsIgnoreCase(conservationClassification) || specialistTeamRequired) {
                    return "Critical";
                }
                return "High";
            }
            default -> {
            }
        }
        return "Medium";
    }

    // Operations
    public void startRescueOperation() {
        this.currentRescueStatus = "Rescue in Progress";
    }

    public void completeRescueOperation() {
        this.currentRescueStatus = "Completed";
    }

    public String generateRescueSummary() {
        return String.format("""
                             Case ID        : %s
                             Type           : %s
                             Species        : %s
                             Priority       : %s
                             Status         : %s
                             Total Cost     : R%.2f""",
            rescueCaseId, rescueType, species, determinePriority(), currentRescueStatus, calculateTotalCost()
        );
    }

    // Getters and Setters
    public String getRescueCaseId() { return rescueCaseId; }
    public String getAnimalName() { return animalName; }
    public String getSpecies() { return species; }
    public String getRescueLocation() { return rescueLocation; }
    public String getAssignedRanger() { return assignedRanger; }
    public int getNumberOfRescueDays() { return numberOfRescueDays; }
    public double getDailyCareCost() { return dailyCareCost; }
    public String getCurrentRescueStatus() { return currentRescueStatus; }
    public void setCurrentRescueStatus(String status) { this.currentRescueStatus = status; }
    public String getRescueType() { return rescueType; }
}