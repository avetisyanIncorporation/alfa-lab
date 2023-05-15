package lab.alfa.task.domain.enumeration;

import java.util.stream.Stream;

/**
 * @author Pargev A. created on 07.05.2023
 */
public enum DocumentType {

    PASS(1),
    ID(2);

    private final int id;

    DocumentType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static DocumentType valueOf(int id) {
        return Stream.of(values())
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No DocumentType with id=" + id));
    }

}
