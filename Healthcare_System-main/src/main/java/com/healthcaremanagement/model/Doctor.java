package com.healthcaremanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Doctors")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"patients", "appointments"})
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DoctorID")
    private int doctorID;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Specialty")
    private String specialty;

    @Column(name = "Email")
    private String email;

    // Figure out a way to display without FetchType.EAGER, if not excluded.
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Appointment> appointments = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Doctor_Patient",
            joinColumns = @JoinColumn(name = "DoctorID"),
            inverseJoinColumns = @JoinColumn(name = "PatientID")
    )
    private Set<Patient> patients = new HashSet<>();

    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Office office;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return doctorID == doctor.doctorID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorID);
    }
}
