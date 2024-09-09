package com.healthcaremanagement.service;

import com.healthcaremanagement.model.Office;
import com.healthcaremanagement.repository.OfficeRepositoryImpl;

import java.util.List;

public class OfficeService {

    private final OfficeRepositoryImpl officeRepository;

    public OfficeService(OfficeRepositoryImpl officeRepository) {
        this.officeRepository = officeRepository;
    }

    public void createOffice(Office office) { officeRepository.createOffice(office); }

    public Office getOffice(int officeId) { return officeRepository.getOfficeById(officeId); }

    public List<Office> getAllOffices() { return officeRepository.getAllOffices(); }

    public void updateOffice(Office office) { officeRepository.updateOffice(office); }

    public void deleteOffice(int officeId) { officeRepository.deleteOffice(officeId); }
}
