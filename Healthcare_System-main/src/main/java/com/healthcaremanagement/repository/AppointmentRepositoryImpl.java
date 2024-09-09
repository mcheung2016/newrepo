package com.healthcaremanagement.repository;

import com.healthcaremanagement.model.Appointment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AppointmentRepositoryImpl {

    private SessionFactory sessionFactory;

    public AppointmentRepositoryImpl(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    public void createAppointment(Appointment appointment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(appointment);
            tx.commit();
        }
    }

    public Appointment getAppointmentById(int appointmentID) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            return session.get(Appointment.class, appointmentID);
        }
    }

    public void updateAppointment(Appointment appointment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(appointment);
            tx.commit();
        }
    }

    public void deleteAppointment(int appointmentID) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, appointmentID);
            if (appointment != null) {
                session.delete(appointment);
            }
            tx.commit();
        }
    }

    public List<Appointment> getAllAppointments() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Appointment", Appointment.class).list();
        }
    }
}
