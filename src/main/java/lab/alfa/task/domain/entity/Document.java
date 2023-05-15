package lab.alfa.task.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lab.alfa.task.domain.enumeration.DocumentType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Pargev A. created on 07.05.2023
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "type", "number"})
//@ToString(exclude = "patient")
@NoArgsConstructor
@Entity
@Table(name = "DOCUMENT")
public class Document {

    @Id
    @SequenceGenerator(name = "document_id_seq", sequenceName = "document_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_id_seq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "DOCUMENT_TYPE", nullable = false)
    private int type;

    @NotNull
    @Column(name = "DOCUMENT_NUMBER", nullable = false, unique = true)
    private String number;

    @NotNull
    @Column(name = "DATE_OF_ISSUE", nullable = false)
    private LocalDate dateOfIssue;

    @NotNull
    @Column(name = "EXPIRY_DATE", nullable = false)
    private LocalDate expiryDate;

    @NotNull
    @Column(name = "ISSUER_NAME", nullable = false)
    private String issuerName;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean active;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PATIENT_ID", nullable = false)
    private Patient patient;

    @Override
    public String toString() {
        return "Document{" +
                "type=" + DocumentType.valueOf(type) +
                ", number='" + number + '\'' +
                ", active=" + active +
                '}';
    }
}


