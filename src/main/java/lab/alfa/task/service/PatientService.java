package lab.alfa.task.service;

import lab.alfa.task.domain.entity.Patient;
import lab.alfa.task.domain.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pargev A. created on 07.05.2023
 */
@Service
public record PatientService(PatientRepository patientRepository) {

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> getPatientsByDocumentNumberPart(String numberPart) {
        return patientRepository.findAllByDocumentActiveAndNumberContains(numberPart);
    }
}
