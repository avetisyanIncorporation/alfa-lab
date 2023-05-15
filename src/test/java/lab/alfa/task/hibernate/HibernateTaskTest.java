package lab.alfa.task.hibernate;

import jakarta.transaction.Transactional;
import lab.alfa.task.domain.entity.Document;
import lab.alfa.task.domain.enumeration.DocumentType;
import lab.alfa.task.service.DocumentService;
import lab.alfa.task.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;


/**
 * @author Pargev A. created on 08.05.2023
 */
@Sql(value = "classpath:/datastore/data.sql", executionPhase = BEFORE_TEST_METHOD)
@SpringBootTest
@Transactional
public class HibernateTaskTest {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private PatientService patientService;

    @Test
    void shouldBeReturnAllPatients() {
        var allPatients = patientService.getAllPatients();
        assertEquals(4, allPatients.size());
        System.out.println(allPatients);
    }

    @Test
    void shouldBeReturnAllDocuments() {
        var allDocuments = documentService.getAllDocuments();
        assertEquals(5, allDocuments.size());
    }

    @Test
    void shouldBePrintAllPatientsWithDocumentNumberLike777Pat() {
        var numberPart = "777";
        var patients777 = patientService.getPatientsByDocumentNumberPart(numberPart);
        for (var patient : patients777) {
            var document = patient.getActiveDocuments().stream()
                    .map(Document::toString)
                    .toList();
            System.out.printf("%s %s %s \n", patient.getFirstName(), patient.getLastName(), document);
        }
        assertEquals(2, patients777.size());
    }

    @Test
    void shouldBePrintAllPatientsWithDocumentNumberLike777Doc() {
        var numberPart = "777";
        var documents777 = documentService.getDocumentsByNumberPart(numberPart);
        for (var document : documents777) {
            var patient = document.getPatient();
            System.out.printf("%s %s %s %s \n", patient.getFirstName(), patient.getLastName(),
                    DocumentType.valueOf(document.getType()), document.getNumber());
        }
        assertEquals(3, documents777.size());
    }
}
