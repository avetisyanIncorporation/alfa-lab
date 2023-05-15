package lab.alfa.task.domain.repository;

import lab.alfa.task.domain.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Pargev A. created on 07.05.2023
 */
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findAllByNumberContainsAndActiveIsTrue(String partOfNumber);
}
