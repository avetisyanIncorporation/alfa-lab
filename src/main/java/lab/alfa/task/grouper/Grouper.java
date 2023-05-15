package lab.alfa.task.grouper;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author Pargev A. created on 08.05.2023
 */
public class Grouper {

    public static <T extends NamedObject> Map<String, List<T>> groupByName(List<T> namedObjects) {
        return namedObjects.stream().collect(groupingBy(NamedObject::getName));
    }
}
