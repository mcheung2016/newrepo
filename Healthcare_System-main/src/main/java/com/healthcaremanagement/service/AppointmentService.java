package com.healthcaremanagement.service;

import com.healthcaremanagement.model.Appointment;
import com.healthcaremanagement.repository.AppointmentRepositoryImpl;

import java.util.List;

public class AppointmentService {

    private final AppointmentRepositoryImpl appointmentRepository;

    public AppointmentService(AppointmentRepositoryImpl appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void createAppointment(Appointment appointment) {
        appointmentRepository.createAppointment(appointment);
    }

    public Appointment getAppointment(int appointmentID) {
        return appointmentRepository.getAppointmentById(appointmentID);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getAllAppointments();
    }

    public void updateAppointment(Appointment appointment) {
        appointmentRepository.updateAppointment(appointment);
    }

    public void deleteAppointment(int appointmentID) {
        appointmentRepository.deleteAppointment(appointmentID);
    }
}
