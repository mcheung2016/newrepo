package com.healthcaremanagement;

import com.healthcaremanagement.menu.AppointmentMenu;
import com.healthcaremanagement.menu.DoctorMenu;
import com.healthcaremanagement.menu.OfficeMenu;
import com.healthcaremanagement.menu.PatientMenu;
import com.healthcaremanagement.repository.AppointmentRepositoryImpl;
import com.healthcaremanagement.repository.DoctorRepositoryImpl;
import com.healthcaremanagement.repository.OfficeRepositoryImpl;
import com.healthcaremanagement.repository.PatientRepositoryImpl;
import com.healthcaremanagement.service.AppointmentService;
import com.healthcaremanagement.service.DoctorService;
import com.healthcaremanagement.service.OfficeService;
import com.healthcaremanagement.service.PatientService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class MainRunner {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        PatientService patientService = new PatientService(new PatientRepositoryImpl(sessionFactory));
        DoctorService doctorService = new DoctorService(new DoctorRepositoryImpl(sessionFactory));
        AppointmentService appointmentService = new AppointmentService(new AppointmentRepositoryImpl(sessionFactory));
        OfficeService officeService = new OfficeService(new OfficeRepositoryImpl(sessionFactory));
        Scanner scanner = new Scanner(System.in);

        // Menu for each operation.
        AppointmentMenu appointmentOps = new AppointmentMenu(appointmentService, scanner);
        DoctorMenu doctorOps = new DoctorMenu(doctorService, scanner);
        PatientMenu patientOps = new PatientMenu(patientService, scanner);
        OfficeMenu officeOps = new OfficeMenu(officeService, doctorService, scanner);

        while (true) {
            System.out.println("\n--- Healthcare Management System ---");
            System.out.println("1. Patient Operations");
            System.out.println("2. Doctor Operations");
            System.out.println("3. Appointment Operations");
            System.out.println("4. Office Operations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = getValidInput(scanner, 1, 5);

            switch (choice) {
                case 1:
                    patientOps.handlePatientOperations();
                    break;
                case 2:
                    doctorOps.handleDoctorOperations();
                    break;
                case 3:
                    appointmentOps.handleAppointmentOperations();
                    break;
                case 4:
                    officeOps.handleOfficeOperations();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
            }
        }
    }

    private static int getValidInput(Scanner scanner, int min, int max) {
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
