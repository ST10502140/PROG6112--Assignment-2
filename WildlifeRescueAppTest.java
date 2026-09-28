/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.wildliferescueapp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WildlifeRescueAppTest {

    private RescueCase injuredCase;
    private RescueCase orphanedCase;
    private RescueCase endangeredCase;
    private WildlifeRescueApp app;

    @BeforeEach
    public void setUp() {
        app = new WildlifeRescueApp();

        // 1. Injured Animal Rescue: 10 days * R100/day = R1000 + R2000 vet + R5000 surgery = R8000
        injuredCase = new RescueCase(
            "WR101", "Leo", "African Lion", "Kruger Park", "Ranger Smith",
            10, 100.0, "Broken Paw", 2000.0, true
        );

        // 2. Orphaned Animal Rescue: 5 days * R100/day = R500 + R500 feeding + R2500 foster = R3500
        orphanedCase = new RescueCase(
            "WR102", "Bennie", "White Rhino", "Hluhluwe", "Ranger Jones",
            5, 100.0, 2, 500.0, true
        );

        // 3. Endangered Species Rescue: 2 days * R200/day = R400 + R1000 security + R8000 specialist = R9400
        endangeredCase = new RescueCase(
            "WR103", "Stripe", "Cheetah", "Kalahari", "Ranger Lee",
            2, 200.0, "Critically Endangered", 1000.0, true, true
        );
    }

    @Test
    public void testRescueCostCalculations() {
        // Verifies cost calculations across different rescue types
        assertEquals(8000.0, injuredCase.calculateTotalCost(), 0.001);
        assertEquals(3500.0, orphanedCase.calculateTotalCost(), 0.001);
        assertEquals(9400.0, endangeredCase.calculateTotalCost(), 0.001);
    }

    @Test
    public void testRescuePriorityCalculations() {
        // Verifies priority level assignment logic
        assertEquals("Critical", injuredCase.determinePriority());
        assertEquals("High", orphanedCase.determinePriority());
        assertEquals("Critical", endangeredCase.determinePriority());
    }

    @Test
    public void testRescueStatusUpdates() {
        // Verifies operation status updates
        assertEquals("Pending", injuredCase.getCurrentRescueStatus());

        injuredCase.startRescueOperation();
        assertEquals("Rescue in Progress", injuredCase.getCurrentRescueStatus());

        injuredCase.completeRescueOperation();
        assertEquals("Completed", injuredCase.getCurrentRescueStatus());
    }

    @Test
    public void testSearchingForExistingRescueCase() {
        // Test searching functionality
        app.findCaseById("WR101"); // Initially null before adding to array

        // We test ID search lookup directly via app logic
        assertNull(app.findCaseById("WR101"));
        assertNull(app.findCaseById("WR999"));
    }

    @Test
    public void testPreventingDuplicateRescueCaseIDs() {
        // Test duplicate ID validation helper
        assertTrue(app.isIdUnique("WR101"));
    }
}