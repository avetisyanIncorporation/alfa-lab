package lab.alfa.task.threads.prime.fork_join;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import static org.apache.commons.math3.primes.Primes.nextPrime;

/**
 * @author Pargev A. created on 12.05.2023
 */
public class PrimeNumbersResourceTask extends RecursiveTask<List<Integer>> {

    private final int SEQUENTIAL_THRESHOLD_MIN = 1000;
    private final int sequentialThreshold;
    private final int startRange;
    private final int endRange;

    public PrimeNumbersResourceTask(int numbersLimit) {
        this.startRange = 2;
        this.endRange = numbersLimit;
        this.sequentialThreshold = computeSequentialThreshold(endRange);
    }

    private PrimeNumbersResourceTask(int startRange, int endRange) {
        this.startRange = startRange;
        this.endRange = endRange;
        this.sequentialThreshold = computeSequentialThreshold(endRange);
    }

    @Override
    protected List<Integer> compute() {
        var primeNumbers = new ArrayList<Integer>(endRange);
        if (endRange - startRange < sequentialThreshold) {
            computeNextAndAddTo(primeNumbers);
        } else {
            separateTaskAndAddResultTo(primeNumbers);
        }
        return primeNumbers;
    }

    private void computeNextAndAddTo(List<Integer> primes) {
        var currentPrime = startRange;
        while (currentPrime <= endRange) {
            currentPrime = nextPrime(currentPrime);
            if (currentPrime <= endRange) {
                primes.add(currentPrime++);
            }
        }
    }

    private void separateTaskAndAddResultTo(List<Integer> primes) {
        int middle = (startRange + endRange) / 2;
        var task1 = new PrimeNumbersResourceTask(startRange, middle);
        var task2 = new PrimeNumbersResourceTask(middle, endRange);

        task1.fork();
        task2.fork();
        primes.addAll(task1.join());
        primes.addAll(task2.join());
    }

    private int computeSequentialThreshold(int endRange) {
        return Math.max(SEQUENTIAL_THRESHOLD_MIN, (int) (0.01 * endRange));
    }
}
