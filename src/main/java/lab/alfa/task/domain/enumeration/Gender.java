package lab.alfa.task.domain.enumeration;

import java.util.stream.Stream;

/**
 * @author Pargev A. created on 07.05.2023
 */
public enum Gender {

    MAN(1),
    WOMAN(2);

    private final int id;

    Gender(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Gender valueOf(int id) {
        return Stream.of(values())
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No Gender with id=" + id));
    }
}
