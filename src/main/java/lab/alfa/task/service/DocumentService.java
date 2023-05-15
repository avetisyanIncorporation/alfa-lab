package lab.alfa.task.service;

import lab.alfa.task.domain.entity.Document;
import lab.alfa.task.domain.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pargev A. created on 07.05.2023
 */
@Service
public record DocumentService(DocumentRepository documentRepository) {

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public List<Document> getDocumentsByNumberPart(String numberPart) {
        return documentRepository.findAllByNumberContainsAndActiveIsTrue(numberPart);
    }
}
