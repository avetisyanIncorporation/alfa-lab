package lab.alfa.task.threads.prime.atomic;

import lab.alfa.task.threads.prime.PrimeNumbersManagerAbstract;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

import static org.apache.commons.math3.primes.Primes.nextPrime;

/**
 * @author Pargev A. created on 13.05.2023
 */
@Service("withBank")
public class PrimeNumbersBank extends PrimeNumbersManagerAbstract {
    private AtomicInteger currentPrime;
    private int numbersLimit;

    @Override
    public void init(int numbersLimit) {
        checkConditions(numbersLimit, 1);
        currentPrime = new AtomicInteger(1);
        this.numbersLimit = numbersLimit;
    }

    public int getNext() {
        currentPrime = new AtomicInteger(nextPrime(currentPrime.get()));
        if (hasNext()) {
            return currentPrime.getAndIncrement();
        }
        return currentPrime.get();
    }

    public boolean hasNext() {
        return nextPrime(currentPrime.get()) <= numbersLimit;
    }
}
