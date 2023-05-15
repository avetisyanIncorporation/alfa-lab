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
public class Address {
    @NotNull
    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "ADDRESS_TYPE", nullable = false)
    private int addressType;
}
