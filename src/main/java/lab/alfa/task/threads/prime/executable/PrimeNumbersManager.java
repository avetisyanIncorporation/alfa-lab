package lab.alfa.task.threads.prime.executable;

import lab.alfa.task.threads.prime.manager.PrimeNumbersManagerAbstract;
import org.springframework.stereotype.Service;

/**
 * @author Pargev A. created on 15.05.2023
 */
@Service("withExecutor")
public class PrimeNumbersManager extends PrimeNumbersManagerAbstract {

    @Override
    public void init(int numbersLimit, int threadsCount) {
        checkConditions(numbersLimit, threadsCount);
        var resource = new PrimeNumbersResource(numbersLimit, threadsCount);
        setPrimes(resource.getAllPrimes());
    }
}
