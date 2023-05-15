package lab.alfa.task.grouper;

import jakarta.transaction.Transactional;
import lab.alfa.task.domain.entity.Patient;
import lab.alfa.task.service.PatientService;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

/**
 * @author Pargev A. created on 08.05.2023
 */
@Sql(value = "classpath:/datastore/data.sql", executionPhase = BEFORE_TEST_METHOD)
@SpringBootTest
@Transactional
public class GrouperIntegrationTest {

    @Autowired
    private PatientService patientService;

    @Test
    void shouldBeGroupAllPatientsByName() {
        var allPatients = patientService.getAllPatients();
        var groupedNameMap = Grouper.groupByName(allPatients);

        assertEquals(2, groupedNameMap.get("Alex").size());
        assertEquals(1, groupedNameMap.get("Max").size());
        assertEquals(1, groupedNameMap.get("Jon").size());
    }

    @Test
    void shouldBeGroupAllVipPatientsByName() {
        var allPatients = patientService.getAllPatients()
                .stream()
                .map(p -> new VIPPatient(p.getFirstName(), "vip"))
                .collect(Collectors.toList());
        var groupedNameMap = Grouper.groupByName(allPatients);

        assertEquals(2, groupedNameMap.get("Alex").size());
        assertEquals(1, groupedNameMap.get("Max").size());
        assertEquals(1, groupedNameMap.get("Jon").size());
    }

    @ToString
    static class VIPPatient extends Patient {
        String subscription;

        VIPPatient(String firstName, String subscription) {
            super(firstName);
            this.subscription = subscription;
        }
    }

}
