package lab.alfa.task.threads.prime.executable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static lab.alfa.task.threads.utils.FutureUtils.getResultOf;

/**
 * @author Pargev A. created on 12.05.2023
 */
public class PrimeNumbersResource {

    private final int numbersLimit;
    private final int threadsCount;
    private final ExecutorService executor;

    public PrimeNumbersResource(int numbersLimit, int threadsCount) {
        this.numbersLimit = numbersLimit;
        this.threadsCount = threadsCount;
        this.executor = Executors.newFixedThreadPool(threadsCount);
    }

    public List<Integer> getAllPrimes() {
        var primeNumbers = new ArrayList<Integer>(numbersLimit);
        List<Future<List<Integer>>> futures = submitFutures();
        for (var future : futures) {
            primeNumbers.addAll(getResultOf(future));
        }
        executor.shutdown();
        return primeNumbers;
    }

    private List<Future<List<Integer>>> submitFutures() {
        List<Future<List<Integer>>> futures = new ArrayList<>(threadsCount);
        var primeNumbersTaskMap = initTaskMap(numbersLimit, threadsCount);
        for (int i = 0; i < threadsCount; i++) {
            futures.add(executor.submit(primeNumbersTaskMap.get(i)));
        }
        return futures;
    }

    private Map<Integer, PrimeNumbersTask> initTaskMap(int numbersLimit, int threadsCount) {
        var numbersTaskMap = new HashMap<Integer, PrimeNumbersTask>(threadsCount);
        int rangeStep = numbersLimit / threadsCount;
        int startRange;
        int endRange = 1;
        for (int i = 0; i < threadsCount; i++) {
            startRange = endRange + 1;
            if (i == threadsCount - 1) {
                endRange = numbersLimit;
            } else {
                endRange += rangeStep;
            }
            numbersTaskMap.put(i, new PrimeNumbersTask(startRange, endRange));
        }
        return numbersTaskMap;
    }
}
