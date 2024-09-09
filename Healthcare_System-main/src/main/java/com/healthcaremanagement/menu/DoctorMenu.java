package com.healthcaremanagement.menu;

import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.service.DoctorService;

import java.util.List;
import java.util.Scanner;

public class DoctorMenu {
    private final DoctorService doctorService;
    private final Scanner scanner;

    public DoctorMenu(DoctorService doctorService, Scanner scanner) {
        this.doctorService = doctorService;
        this.scanner = scanner;
    }

    public void handleDoctorOperations() {
        while (true) {
            System.out.println("\n--- Doctor Operations ---");
            System.out.println("1. Create Doctor");
            System.out.println("2. Read Doctor");
            System.out.println("3. Update Doctor");
            System.out.println("4. Delete Doctor");
            System.out.println("5. List All Doctors");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getValidInput(1, 6);

            switch (choice) {
                case 1:
                    createDoctor();
                    break;
                case 2:
                    readDoctor();
                    break;
                case 3:
                    updateDoctor();
                    break;
                case 4:
                    deleteDoctor();
                    break;
                case 5:
                    listAllDoctors();
                    break;
                case 6:
                    return;
            }
        }
    }

    private void createDoctor() {
        Doctor newDoctor = new Doctor();
        System.out.print("Enter first name: ");
        newDoctor.setFirstName(scanner.nextLine());
        System.out.print("Enter last name: ");
        newDoctor.setLastName(scanner.nextLine());
        System.out.print("Enter specialty: ");
        newDoctor.setSpecialty(scanner.nextLine());
        System.out.print("Enter email: ");
        newDoctor.setEmail(scanner.nextLine());
        doctorService.createDoctor(newDoctor);
        System.out.println("Doctor created successfully.");
    }

    private void readDoctor() {
        System.out.print("Enter Doctor ID: ");
        int doctorId = getValidInput(1, Integer.MAX_VALUE);
        Doctor doctor = doctorService.getDoctorById(doctorId);
        if (doctor != null) {
            System.out.println(doctor);
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private void updateDoctor() {
        System.out.print("Enter Doctor ID: ");
        int doctorId = getValidInput(1, Integer.MAX_VALUE);
        Doctor doctor = doctorService.getDoctorById(doctorId);
        if (doctor != null) {
            System.out.print("Enter new first name: ");
            doctor.setFirstName(scanner.nextLine());
            System.out.print("Enter new last name: ");
            doctor.setLastName(scanner.nextLine());
            System.out.print("Enter new specialty: ");
            doctor.setSpecialty(scanner.nextLine());
            System.out.print("Enter new email: ");
            doctor.setEmail(scanner.nextLine());
            doctorService.updateDoctor(doctor);
            System.out.println("Doctor updated successfully.");
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private void deleteDoctor() {
        System.out.print("Enter Doctor ID: ");
        int doctorId = getValidInput(1, Integer.MAX_VALUE);
        doctorService.deleteDoctor(doctorId);
        System.out.println("Doctor deleted successfully.");
    }

    private void listAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
        } else {
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
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
