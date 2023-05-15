package lab.alfa.task.threads.utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Pargev A. created on 12.05.2023
 */
public class FutureUtils {

    public static <T> T getResultOf(Future<T> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
