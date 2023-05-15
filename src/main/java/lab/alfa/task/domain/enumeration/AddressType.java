package lab.alfa.task.domain.enumeration;

import java.util.stream.Stream;

/**
 * @author Pargev A. created on 07.05.2023
 */
public enum AddressType {

    FACT(1),
    TEMP(2);

    private final int id;

    AddressType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static AddressType valueOf(int id) {
        return Stream.of(values())
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No AddressType with id=" + id));
    }
}
