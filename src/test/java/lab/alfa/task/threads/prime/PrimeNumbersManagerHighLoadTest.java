package lab.alfa.task.threads.prime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Pargev A. created on 15.05.2023
 */
@Disabled
@SpringBootTest
public class PrimeNumbersManagerHighLoadTest {

    private final int NUMBERS_LIMIT = 15_000_000;

    @Autowired
    @Qualifier("withForkJoin")
    private PrimeNumbersManagerAbstract managerWithForkJoin;

    @Autowired
    @Qualifier("withExecutor")
    private PrimeNumbersManagerAbstract managerWithExecutor;

    @Autowired
    @Qualifier("withBank")
    private PrimeNumbersManagerAbstract managerWithBank;

    @Test
    void shouldCreateResourceAndComputeViaForkJoin() {
        var c = System.currentTimeMillis();
        managerWithForkJoin.init(NUMBERS_LIMIT, 2);
        System.out.println("Time via Fork/Join: " + (System.currentTimeMillis() - c));

        assertTrue(managerWithForkJoin.hasNext());
        assertEquals(2, managerWithForkJoin.getNext());
        assertEquals(3, managerWithForkJoin.getNext());
    }

    @Test
    void shouldCreateResourceAndComputeViaExecutors() {
        var c = System.currentTimeMillis();
        managerWithExecutor.init(NUMBERS_LIMIT, 2);
        System.out.println("Time via Executor: " + (System.currentTimeMillis() - c));

        assertTrue(managerWithExecutor.hasNext());
        managerWithExecutor.getNext();
        managerWithExecutor.getNext();
        managerWithExecutor.getNext();
        assertEquals(7, managerWithExecutor.getNext());
    }

    @Test
    @Disabled("Take too much time")
    void shouldCreateResourceAndComputeViaResourceBank() {
        var c = System.currentTimeMillis();
        managerWithBank.init(NUMBERS_LIMIT);
        while (managerWithBank.hasNext()) {
            managerWithBank.getNext();
        }
        System.out.println("bank: " + (System.currentTimeMillis() - c));
    }
}
