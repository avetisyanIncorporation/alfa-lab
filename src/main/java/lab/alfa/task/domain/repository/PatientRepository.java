package lab.alfa.task.domain.repository;

import lab.alfa.task.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Pargev A. created on 07.05.2023
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p JOIN Document d ON p = d.patient " +
            "WHERE d.number LIKE %:numberPart% AND d.active = TRUE")
    List<Patient> findAllByDocumentActiveAndNumberContains(String numberPart);
}
