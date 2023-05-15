package lab.alfa.task.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lab.alfa.task.domain.entity.embedded.Address;
import lab.alfa.task.domain.entity.embedded.Contact;
import lab.alfa.task.domain.entity.embedded.MedicalInfo;
import lab.alfa.task.grouper.NamedObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Pargev A. created on 13.04.2023
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = "documents")
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient implements NamedObject {

    @Id
    @SequenceGenerator(name = "patient_id_seq", sequenceName = "patient_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_id_seq")
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "GENDER", nullable = false)
    private int gender;

    @Embedded
    private Address address;

    @Embedded
    private Contact contact;

    @Embedded
    private MedicalInfo medicalInfo;

    @NotNull
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
    private List<Document> documents;

    public Patient(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getName() {
        return firstName;
    }

    public List<Document> getActiveDocuments() {
        return documents.stream().filter(Document::isActive).toList();
    }
}
