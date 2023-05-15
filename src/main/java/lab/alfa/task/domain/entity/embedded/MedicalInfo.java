package lab.alfa.task.domain.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Pargev A. created on 10.05.2023
 */
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalInfo {
    @NotNull
    @Column(name = "POLICY_NUMBER", nullable = false, unique = true)
    private String policyNumber;

    @NotNull
    @Column(name = "SNILS", nullable = false, unique = true)
    private String snils;
}
