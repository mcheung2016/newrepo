package com.healthcaremanagement.menu;

import com.healthcaremanagement.model.Patient;
import com.healthcaremanagement.service.PatientService;

import java.util.List;
import java.util.Scanner;

public class PatientMenu {
    private final PatientService patientService;
    private final Scanner scanner;

    public PatientMenu(PatientService patientService, Scanner scanner) {
        this.patientService = patientService;
        this.scanner = scanner;
    }

    public void handlePatientOperations() {
        while (true) {
            System.out.println("\n--- Patient Operations ---");
            System.out.println("1. Create Patient");
            System.out.println("2. Read Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. List All Patients");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getValidInput(1, 6);

            switch (choice) {
                case 1:
                    createPatient();
                    break;
                case 2:
                    readPatient();
                    break;
                case 3:
                    updatePatient();
                    break;
                case 4:
                    deletePatient();
                    break;
                case 5:
                    listAllPatients();
                    break;
                case 6:
                    return;
            }
        }
    }

    private void createPatient() {
        Patient newPatient = new Patient();
        System.out.print("Enter first name: ");
        newPatient.setFirstName(scanner.nextLine());
        System.out.print("Enter last name: ");
        newPatient.setLastName(scanner.nextLine());
        System.out.print("Enter date of birth (YYYY-MM-DD): ");
        newPatient.setDateOfBirth(scanner.nextLine());
        System.out.print("Enter email: ");
        newPatient.setEmail(scanner.nextLine());
        System.out.print("Enter phone number: ");
        newPatient.setPhoneNumber(scanner.nextLine());
        patientService.createPatient(newPatient);
        System.out.println("Patient created successfully.");
    }

    private void readPatient() {
        System.out.print("Enter Patient ID: ");
        int patientId = getValidInput(1, Integer.MAX_VALUE);
        Patient patient = patientService.getPatientById(patientId);
        if (patient != null) {
            System.out.println(patient);
        } else {
            System.out.println("Patient not found.");
        }
    }

    private void updatePatient() {
        System.out.print("Enter Patient ID: ");
        int patientId = getValidInput(1, Integer.MAX_VALUE);
        Patient patient = patientService.getPatientById(patientId);
        if (patient != null) {
            System.out.print("Enter new first name: ");
            patient.setFirstName(scanner.nextLine());
            System.out.print("Enter new last name: ");
            patient.setLastName(scanner.nextLine());
            System.out.print("Enter new date of birth (YYYY-MM-DD): ");
            patient.setDateOfBirth(scanner.nextLine());
            System.out.print("Enter new email: ");
            patient.setEmail(scanner.nextLine());
            System.out.print("Enter new phone number: ");
            patient.setPhoneNumber(scanner.nextLine());
            patientService.updatePatient(patient);
            System.out.println("Patient updated successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    private void deletePatient() {
        System.out.print("Enter Patient ID: ");
        int patientId = getValidInput(1, Integer.MAX_VALUE);
        patientService.deletePatient(patientId);
        System.out.println("Patient deleted successfully.");
    }

    private void listAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            for (Patient patient : patients) {
                System.out.println(patient);
            }
        }
    }

    private int getValidInput(int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.print("Invalid input. Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
}
