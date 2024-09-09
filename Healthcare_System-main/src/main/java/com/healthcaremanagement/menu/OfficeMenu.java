package com.healthcaremanagement.menu;

import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Office;
import com.healthcaremanagement.service.DoctorService;
import com.healthcaremanagement.service.OfficeService;

import java.util.List;
import java.util.Scanner;

public class OfficeMenu {
    private final OfficeService officeService;
    private final DoctorService doctorService;
    private final Scanner scanner;

    public OfficeMenu(OfficeService officeService, DoctorService doctorService, Scanner scanner) {
        this.officeService = officeService;
        this.doctorService = doctorService;
        this.scanner = scanner;
    }

    public void handleOfficeOperations() {
        while (true) {
            System.out.println("\n--- Office Operations ---");
            System.out.println("1. Create Office");
            System.out.println("2. Read Office");
            System.out.println("3. Update Office");
            System.out.println("4. Delete Office");
            System.out.println("5. List All Offices");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getValidInput(1, 6);

            switch (choice) {
                case 1:
                    createOffice();
                    break;
                case 2:
                    readOffice();
                    break;
                case 3:
                    updateOffice();
                    break;
                case 4:
                    deleteOffice();
                    break;
                case 5:
                    listAllOffices();
                    break;
                case 6:
                    return;
            }
        }
    }

    public void createOffice() {
        Office newOffice = new Office();
        System.out.print("Enter Office Location: ");
        newOffice.setLocation(scanner.nextLine());
        System.out.println("Enter Office Phone Number: ");
        newOffice.setPhoneNumber(scanner.nextLine());
        System.out.println("Enter doctor ID for the office: ");
        int doctorId = getValidInput(1, Integer.MAX_VALUE);
        Doctor doctor = doctorService.getDoctorById(doctorId);
        if (doctor != null) {
            newOffice.setDoctor(doctor);
            officeService.createOffice(newOffice);
            System.out.println("Office created successfully!");
        } else {
            System.out.println("Doctor not found! Office cannot be created.");
        }
    }

    public void readOffice() {
        System.out.println("Enter Office ID: ");
        int officeId = getValidInput(1, Integer.MAX_VALUE);
        Office office = officeService.getOffice(officeId);
        if (office != null) {
            System.out.println(office);
        } else {
            System.out.println("Office not found.");
        }
    }

    public void updateOffice() {
        System.out.println("Enter Office ID: ");
        int officeId = getValidInput(1, Integer.MAX_VALUE);
        Office office = officeService.getOffice(officeId);
        if (office != null) {
            System.out.print("Enter new location (press enter to keep current): ");
            String newLocation = scanner.nextLine();
            if (!newLocation.isEmpty()) {
                office.setLocation(newLocation);
            }

            System.out.println("Enter new phone number (press enter to keep current): ");
            String newPhoneNumber = scanner.nextLine();
            if (!newPhoneNumber.isEmpty()) {
                office.setPhoneNumber(newPhoneNumber);
            }

            System.out.print("Enter new doctor ID (press enter to keep current): ");
            int doctorId = getValidInput(1, Integer.MAX_VALUE);
            Doctor doctor = doctorService.getDoctorById(doctorId);
            if (doctor != null) {
                office.setDoctor(doctor);
            } else {
                System.out.println("Doctor not found or empty input. Keeping current doctor.");
            }
            officeService.updateOffice(office);
            System.out.println("Office updated successfully!");
        } else {
            System.out.println("Office not found.");
        }
    }

    public void deleteOffice() {
        System.out.println("Enter Office ID: ");
        int officeId = getValidInput(1, Integer.MAX_VALUE);
        officeService.deleteOffice(officeId);
        System.out.println("Office deleted successfully!");
    }

    public void listAllOffices() {
        List<Office> offices = officeService.getAllOffices();
        if (offices.isEmpty()) {
            System.out.println("No offices found.");
        } else {
            for (Office office : offices) {
                System.out.println(office);
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
