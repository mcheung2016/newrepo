package com.healthcaremanagement.repository;

import com.healthcaremanagement.model.Doctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DoctorRepositoryImpl {

    private SessionFactory sessionFactory;

    public DoctorRepositoryImpl(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    public void createDoctor(Doctor doctor) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(doctor);
            tx.commit();
        }
    }

    public Doctor getDoctorById(int doctorId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Doctor.class, doctorId);
        }
    }

    public void updateDoctor(Doctor doctor) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(doctor);
            tx.commit();
        }
    }

    public void deleteDoctor(int doctorId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, doctorId);
            if (doctor != null) {
                session.delete(doctor);
            }
            tx.commit();
        }
    }

    public List<Doctor> getAllDoctors() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Doctor", Doctor.class).list();
        }
    }
}
