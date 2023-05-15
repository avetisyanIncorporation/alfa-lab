package lab.alfa.task.threads.prime.atomic;

import java.util.concurrent.atomic.AtomicInteger;

import static org.apache.commons.math3.primes.Primes.nextPrime;

/**
 * @author Pargev A. created on 13.05.2023
 */
public class PrimeNumbersBank {
    private AtomicInteger currentPrime;
    private final int numbersLimit;

    public PrimeNumbersBank(int numbersLimit) {
        this.currentPrime = new AtomicInteger(1);
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
