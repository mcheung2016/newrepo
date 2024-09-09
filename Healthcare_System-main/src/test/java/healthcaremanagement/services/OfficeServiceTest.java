package healthcaremanagement.services;

import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Office;
import com.healthcaremanagement.repository.DoctorRepositoryImpl;
import com.healthcaremanagement.repository.OfficeRepositoryImpl;
import com.healthcaremanagement.service.OfficeService;
import com.healthcaremanagement.service.DoctorService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class OfficeServiceTest {

    private SessionFactory sessionFactory;
    private OfficeService officeService;
    private DoctorService doctorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sessionFactory = new Configuration().configure("hibernate-test.cfg.xml").buildSessionFactory();
        officeService = new OfficeService(new OfficeRepositoryImpl(sessionFactory));
        doctorService = new DoctorService(new DoctorRepositoryImpl(sessionFactory));
    }

    @AfterEach
    public void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void testCreateOffice() {
        // Create Office
        Office office = new Office();
        office.setLocation("123 Example St.");
        office.setPhoneNumber("222-3333");

        // Create the doctor for the office.
        Doctor doctor = new Doctor();
        doctor.setFirstName("Jane");
        doctor.setLastName("Smith");
        doctor.setSpecialty("Cardiology");
        doctor.setEmail("jane.smith@example.com");
        doctorService.createDoctor(doctor);
        assertNotNull(doctor.getDoctorID());

        // Set doctor ID for office.
        office.setDoctor(doctor);

        officeService.createOffice(office);
        assertNotNull(office.getOfficeId());
    }

    @Test
    public void testGetOfficeById() {
        Office office = officeService.getOffice(1);
        assertNull(office);

        // Assuming an office ID #1 exist.
        //assertNotNull(office);
    }

    @Test
    public void testGetAllOffices() {
        List<Office> offices = officeService.getAllOffices();
        assertNotNull(offices);
        assertTrue(offices.isEmpty());
    }

    @Test
    public void testUpdateOffice() {
        // Create Office
        Office office = new Office();
        office.setLocation("123 Example St.");
        office.setPhoneNumber("222-3333");

        // Create the doctor for the office.
        Doctor doctor = new Doctor();
        doctor.setFirstName("Jane");
        doctor.setLastName("Smith");
        doctor.setSpecialty("Cardiology");
        doctor.setEmail("jane.smith@example.com");
        doctorService.createDoctor(doctor);

        // Set doctor ID for office.
        office.setDoctor(doctor);

        // Create and updated office.
        officeService.createOffice(office);
        String newPhoneNumber = "456-3812";
        office.setPhoneNumber(newPhoneNumber);
        officeService.updateOffice(office);

        // Check if office been updated, by matching attributes.
        assertEquals(newPhoneNumber, office.getPhoneNumber());
    }

    @Test
    public void testDeleteOffice() {
        // Create Office
        Office office = new Office();
        office.setLocation("123 Example St.");
        office.setPhoneNumber("222-3333");

        // Create the doctor for the office.
        Doctor doctor = new Doctor();
        doctor.setFirstName("Jane");
        doctor.setLastName("Smith");
        doctor.setSpecialty("Cardiology");
        doctor.setEmail("jane.smith@example.com");
        doctorService.createDoctor(doctor);

        // Set doctor ID for office. Create office.
        office.setDoctor(doctor);
        officeService.createOffice(office);

        // Delete the created office.
        int id = office.getOfficeId();
        officeService.deleteOffice(id);

        // Check if office object still exists.
        assertNull(officeService.getOffice(id));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123 Example St.", "456 Twoshade Rd."})
    public void testCreateOfficeByLocation(String location) {
        // Create office.
        Office office = new Office();
        office.setLocation(location);
        office.setPhoneNumber("222-3333");

        // Create the doctor for the office.
        Doctor doctor = new Doctor();
        doctor.setFirstName("Jane");
        doctor.setLastName("Smith");
        doctor.setSpecialty("Cardiology");
        doctor.setEmail("jane.smith@example.com");
        doctorService.createDoctor(doctor);

        // Set doctor ID for office. Create office.
        office.setDoctor(doctor);
        officeService.createOffice(office);

        // Check if office exists and matching location.
        assertNotNull(office.getOfficeId());
        assertEquals(location, office.getLocation());
    }
}
