package lab.alfa.task.threads;

import lab.alfa.task.threads.prime.manager.PrimeNumbersManagerAbstract;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Pargev A. created on 09.05.2023
 */
@Disabled
@SpringBootTest
class ThreadsManagerTest {

    private final int NUMBERS_LIMIT = 15_000_000;

    @Autowired
    @Qualifier("withForkJoin")
    private PrimeNumbersManagerAbstract managerWithForkJoin;

    @Autowired
    @Qualifier("withExecutor")
    private PrimeNumbersManagerAbstract managerWithExecutor;

    @Test
    public void shouldBeRunIn1ThreadViaForkJoin() {
        final int THREADS_COUNT = 1;
        manageAndPrintTime(new ThreadsManager(managerWithForkJoin), THREADS_COUNT, "ForkJoin");
    }

    @Test
    public void shouldBeRunIn2ThreadsViaForkJoin() {
        final int THREADS_COUNT = 2;
        manageAndPrintTime(new ThreadsManager(managerWithForkJoin), THREADS_COUNT, "ForkJoin");
    }

    @Test
    public void shouldBeRunIn4ThreadsViaForkJoin() {
        final int THREADS_COUNT = 4;
        manageAndPrintTime(new ThreadsManager(managerWithForkJoin), THREADS_COUNT, "ForkJoin");
    }

    @Test
    public void shouldBeRunIn1ThreadViaExecutor() {
        final int THREADS_COUNT = 1;
        manageAndPrintTime(new ThreadsManager(managerWithExecutor), THREADS_COUNT, "Executor");
    }

    @Test
    public void shouldBeRunIn2ThreadsViaExecutor() {
        final int THREADS_COUNT = 2;
        manageAndPrintTime(new ThreadsManager(managerWithExecutor), THREADS_COUNT, "Executor");
    }

    @Test
    public void shouldBeRunIn4ThreadsViaExecutor() {
        final int THREADS_COUNT = 4;
        manageAndPrintTime(new ThreadsManager(managerWithExecutor), THREADS_COUNT, "Executor");
    }

    private void manageAndPrintTime(ThreadsManager threadsManager, int threadsCount, String realize) {
        var startTimeMillis = System.currentTimeMillis();
        threadsManager.manage(threadsCount, NUMBERS_LIMIT);
        var endTimeMillis = System.currentTimeMillis();
        System.out.printf("Time to compute %d primes with %s in %d threads: %d\n",
                NUMBERS_LIMIT, realize, threadsCount, (endTimeMillis - startTimeMillis));
        System.gc();
    }
}
