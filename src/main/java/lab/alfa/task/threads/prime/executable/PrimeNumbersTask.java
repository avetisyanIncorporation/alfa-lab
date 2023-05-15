package lab.alfa.task.threads.prime.executable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static org.apache.commons.math3.primes.Primes.nextPrime;


/**
 * @author Pargev A. created on 12.05.2023
 */
class PrimeNumbersTask implements Callable<List<Integer>> {

    private final int startRange;
    private final int endRange;

    PrimeNumbersTask(int startRange, int endRange) {
        this.startRange = startRange;
        this.endRange = endRange;
    }

    public List<Integer> call() {
        var primes = new ArrayList<Integer>();
        var currentPrime = startRange;
        while (currentPrime <= endRange) {
            currentPrime = nextPrime(currentPrime);
            if (currentPrime <= endRange) {
                primes.add(currentPrime++);
            }
        }
        return primes;
    }
}
