package lab.alfa.task.domain.enumeration;

import java.util.stream.Stream;

/**
 * @author Pargev A. created on 07.05.2023
 */
public enum ContactType {

    PHONE(1),
    EMAIL(2);

    private final int id;

    ContactType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ContactType valueOf(int id) {
        return Stream.of(values())
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No ContactType with id=" + id));
    }
}
