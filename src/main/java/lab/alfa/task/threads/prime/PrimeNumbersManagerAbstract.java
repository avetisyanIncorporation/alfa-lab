package lab.alfa.task.threads.prime;

import java.util.List;

/**
 * @author Pargev A. created on 03.05.2023
 */
//@Scope(Prototo)
public abstract class PrimeNumbersManagerAbstract {

    private int currentIndex;
    private List<Integer> primes;

    public int getNext() {
        if (hasNext()) {
            var currentPrime = primes.get(currentIndex);
            // TODO Use Stack
            primes.set(currentIndex++, null);
            return currentPrime;
        }
        return -1;
    }

    public boolean hasNext() {
        return currentIndex < primes.size();
    }

    public void init(int numbersLimit) {    }

    public void init(int numbersLimit, int threadsCount) {    }

    protected void setPrimes(List<Integer> primes) {
        resetCurrentIndex();
        this.primes = primes;
    }

    protected void resetCurrentIndex() {
        currentIndex = 0;
    }

    protected void checkConditions(int numbersLimit, int threadsCount) {
        if (numbersLimit <= 1) {
            throw new IllegalArgumentException("Prime numbers limit must be greater than 2");
        }
        if (threadsCount <= 0) {
            throw new IllegalArgumentException("Threads count must be greater than 0");
        }
    }
}
