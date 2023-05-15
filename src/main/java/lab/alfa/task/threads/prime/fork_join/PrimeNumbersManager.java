package lab.alfa.task.threads.prime.fork_join;

import lab.alfa.task.threads.prime.manager.PrimeNumbersManagerAbstract;
import org.springframework.stereotype.Service;

import java.util.concurrent.ForkJoinPool;

/**
 * @author Pargev A. created on 15.05.2023
 */
@Service("withForkJoin")
public class PrimeNumbersManager extends PrimeNumbersManagerAbstract {

    @Override
    public void init(int numbersLimit, int threadsCount) {
        checkConditions(numbersLimit, threadsCount);
        ForkJoinPool pool = new ForkJoinPool(threadsCount);
        var resourceTask = new PrimeNumbersResourceTask(numbersLimit);
        setPrimes(pool.invoke(resourceTask));
    }

}
