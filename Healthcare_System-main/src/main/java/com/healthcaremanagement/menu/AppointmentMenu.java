package com.healthcaremanagement.menu;

import com.healthcaremanagement.model.Appointment;
import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Patient;
import com.healthcaremanagement.service.AppointmentService;
import com.healthcaremanagement.service.DoctorService;
import com.healthcaremanagement.service.PatientService;

import java.util.List;
import java.util.Scanner;

public class AppointmentMenu {
    private final AppointmentService appointmentService;
    private final Scanner scanner;

    public AppointmentMenu(AppointmentService appointmentService, Scanner scanner) {
        this.appointmentService = appointmentService;
        this.scanner = scanner;
    }

    public void handleAppointmentOperations() {
        while (true) {
            System.out.println("\n--- Appointment Operations ---");
            System.out.println("1. Create Appointment");
            System.out.println("2. Read Appointment");
            System.out.println("3. Update Appointment");
            System.out.println("4. Delete Appointment");
            System.out.println("5. List All Appointments");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getValidInput(1, 6);

            switch (choice) {
                case 1:
                    createAppointment();
                    break;
                case 2:
                    readAppointment();
                    break;
                case 3:
                    updateAppointment();
                    break;
                case 4:
                    deleteAppointment();
                    break;
                case 5:
                    listAllAppointments();
                    break;
                case 6:
                    return;
            }
        }
    }

    private void createAppointment() {
        Appointment newAppointment = new Appointment();
        System.out.print("Enter patient ID: ");
        int patientID = getValidInput(1, Integer.MAX_VALUE);
        Patient patient = new Patient();
        patient.setPatientId(patientID);
        newAppointment.setPatient(patient);
        System.out.print("Enter doctor ID: ");
        int doctorID = getValidInput(1, Integer.MAX_VALUE);
        Doctor doctor  = new Doctor();
        doctor.setDoctorID(doctorID);
        newAppointment.setDoctor(doctor);
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        newAppointment.setAppointmentDate(scanner.nextLine());
        System.out.print("Enter notes: ");
        newAppointment.setNotes(scanner.nextLine());
        appointmentService.createAppointment(newAppointment);
        System.out.println("Appointment created successfully.");
    }

    private void readAppointment() {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = getValidInput(1, Integer.MAX_VALUE);
        Appointment appointment = appointmentService.getAppointment(appointmentId);
        if (appointment != null) {
            System.out.println(appointment);
        } else {
            System.out.println("Appointment not found.");
        }
    }

    private void updateAppointment() {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = getValidInput(1, Integer.MAX_VALUE);
        Appointment appointment = appointmentService.getAppointment(appointmentId);
        if (appointment != null) {
            System.out.print("Enter new patient ID: ");
            int patientID = getValidInput(1, Integer.MAX_VALUE);
            Patient patient = new Patient();
            patient.setPatientId(patientID);
            appointment.setPatient(patient);
            System.out.print("Enter new doctor ID: ");
            int doctorID = getValidInput(1, Integer.MAX_VALUE);
            Doctor doctor  = new Doctor();
            doctor.setDoctorID(doctorID);
            appointment.setDoctor(doctor);
            System.out.print("Enter new appointment date (YYYY-MM-DD): ");
            appointment.setAppointmentDate(scanner.nextLine());
            System.out.print("Enter new notes: ");
            appointment.setNotes(scanner.nextLine());
            appointmentService.updateAppointment(appointment);
            System.out.println("Appointment updated successfully.");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    private void deleteAppointment() {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = getValidInput(1, Integer.MAX_VALUE);
        appointmentService.deleteAppointment(appointmentId);
        System.out.println("Appointment deleted successfully.");
    }

    private void listAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
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
