package healthcaremanagement.services;

import com.healthcaremanagement.model.Appointment;
import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Patient;
import com.healthcaremanagement.repository.AppointmentRepositoryImpl;
import com.healthcaremanagement.repository.DoctorRepositoryImpl;
import com.healthcaremanagement.repository.PatientRepositoryImpl;
import com.healthcaremanagement.service.AppointmentService;
import com.healthcaremanagement.service.DoctorService;
import com.healthcaremanagement.service.PatientService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AppointmentServiceTest {

    private SessionFactory sessionFactory;
    private AppointmentService appointmentService;
    private DoctorService doctorService;
    private PatientService patientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sessionFactory = new Configuration().configure("hibernate-test.cfg.xml").buildSessionFactory();

        // Services
        appointmentService = new AppointmentService(new AppointmentRepositoryImpl(sessionFactory));
        doctorService = new DoctorService(new DoctorRepositoryImpl(sessionFactory));
        patientService = new PatientService(new PatientRepositoryImpl(sessionFactory));
    }

    @AfterEach
    public void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void testCreateAppointment() {
        // Create and save the patient
        Patient patient = new Patient();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patientService.createPatient(patient);

        // Create and save the doctor
        Doctor doctor = new Doctor();
        doctor.setFirstName("Jane");
        doctor.setLastName("Smith");
        doctorService.createDoctor(doctor);

        // Create the appointment and set the patient and doctor
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate("2024-09-01");
        appointment.setNotes("Annual checkup");

        // Save the appointment
        appointmentService.createAppointment(appointment);

        // Assert that the appointment has been saved with an ID
        assertNotNull(appointment.getAppointmentID());
    }

    @Test
    public void testGetAppointmentById() {
        Appointment appointment = appointmentService.getAppointment(1);
        assertNull(appointment);

        // Uncomment this line if you have a real Appointment with ID 1 in the database for testing
        // assertNotNull(appointmentService.getAppointmentById(1));
    }

    @Test
    public void testGetAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        assertNotNull(appointments);
        assertTrue(appointments.isEmpty());
    }

    @Test
    public void testUpdateAppointment() {
        // Create and save the patient
        Patient patient = new Patient();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patientService.createPatient(patient);

        // Create and save the doctor
        Doctor doctor = new Doctor();
        doctor.setFirstName("Jane");
        doctor.setLastName("Smith");
        doctorService.createDoctor(doctor);

        // Create the appointment and set the patient and doctor
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate("2024-09-02");
        appointment.setNotes("Initial notes");

        // Save the appointment
        appointmentService.createAppointment(appointment);

        // Update the appointment
        appointment.setNotes("Updated notes");
        appointmentService.updateAppointment(appointment);

        // Verify the updated notes
        Appointment updatedAppointment = appointmentService.getAppointment(appointment.getAppointmentID());
        assertEquals("Updated notes", updatedAppointment.getNotes());
    }

    @Test
    public void testDeleteAppointment() {
        // Create and save the patient
        Patient patient = new Patient();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patientService.createPatient(patient);

        // Create and save the doctor
        Doctor doctor = new Doctor();
        doctor.setFirstName("Jane");
        doctor.setLastName("Smith");
        doctorService.createDoctor(doctor);

        // Create the appointment and set the patient and doctor
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate("2024-09-03");
        appointment.setNotes("To be deleted");

        // Save the appointment
        appointmentService.createAppointment(appointment);

        // Delete the appointment
        int id = appointment.getAppointmentID();
        appointmentService.deleteAppointment(id);

        // Assert that the appointment has been deleted
        assertNull(appointmentService.getAppointment(id));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2024-09-01", "2024-09-02", "2024-09-03"})
    public void testCreateAppointmentWithDifferentDates(String date) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(date);
        appointment.setNotes("Checkup");

        appointmentService.createAppointment(appointment);
        assertNotNull(appointment.getAppointmentID());
        assertEquals(date, appointment.getAppointmentDate());
    }
}
