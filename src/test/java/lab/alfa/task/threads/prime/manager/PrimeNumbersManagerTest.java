package lab.alfa.task.threads.prime.manager;

import lab.alfa.task.threads.prime.atomic.PrimeNumbersBank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.apache.commons.math3.primes.Primes.nextPrime;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Pargev A. created on 12.05.2023
 */
@SpringBootTest
class PrimeNumbersManagerTest {

    @Autowired
    @Qualifier("withForkJoin")
    private PrimeNumbersManagerAbstract managerWithForkJoin;

    @Autowired
    @Qualifier("withExecutor")
    private PrimeNumbersManagerAbstract managerWithExecutor;

    @Test
    void shouldGetNextPrimeViaApachePrimes() {
        var prime = nextPrime(110);
        assertEquals(113, prime);
    }

    @Test
    void shouldGetAllPrimesIn2ThreadsViaForkJoin() {
        managerWithForkJoin.init(40, 2);
        var i = 0;
        while (managerWithForkJoin.hasNext()) {
            managerWithForkJoin.getNext();
            i++;
        }
        assertEquals(12, i);
    }

    @Test
    void shouldGetAllPrimesIn4ThreadsViaForkJoin() {
        managerWithForkJoin.init(110, 4);
        var i = 0;
        while (managerWithForkJoin.hasNext()) {
            managerWithForkJoin.getNext();
            i++;
        }
        assertEquals(29, i);
    }

    @Test
    void shouldGetAllPrimesIn2ThreadsViaExecutor() {
        managerWithExecutor.init(40, 2);
        var i = 0;
        while (managerWithExecutor.hasNext()) {
            managerWithExecutor.getNext();
            i++;
        }
        assertEquals(12, i);
    }

    @Test
    void shouldGetAllPrimesIn4ThreadsViaExecutor() {
        managerWithExecutor.init(110, 4);
        var i = 0;
        while (managerWithExecutor.hasNext()) {
            managerWithExecutor.getNext();
            i++;
        }
        assertEquals(29, i);
    }

    @Test
    void shouldGetAllPrimesUntil110ViaResourceBank() {
        var pnm = new PrimeNumbersBank(110);
        var i = 0;
        while (pnm.hasNext()) {
            pnm.getNext();
            i++;
        }
        assertEquals(29, i);
    }
}
