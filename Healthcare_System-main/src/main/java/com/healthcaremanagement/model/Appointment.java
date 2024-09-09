package com.healthcaremanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "Appointments")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"patient", "doctor"})
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AppointmentID")
    private int appointmentID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PatientID")
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DoctorID")
    private Doctor doctor;

    @Column(name = "AppointmentDate")
    private String appointmentDate;

    @Column(name = "Notes")
    private String notes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return appointmentID == that.appointmentID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentID);
    }
}
