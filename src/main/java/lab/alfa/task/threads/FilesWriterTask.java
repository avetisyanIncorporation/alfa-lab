package lab.alfa.task.threads;

import lab.alfa.task.threads.prime.manager.PrimeNumbersManagerAbstract;
import lab.alfa.task.threads.resource.Resource;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Pargev A. created on 09.05.2023
 */
public class FilesWriterTask implements Callable<Resource> {

    private final ReentrantLock lock;
    private final PrimeNumbersManagerAbstract primeNumbersManager;
    private final Resource sharedResource;
    private final Resource selfResource;

    FilesWriterTask(ReentrantLock lock, PrimeNumbersManagerAbstract primeNumbersService,
                    Resource sharedResource, String localFileName) {
        this.lock = lock;
        this.primeNumbersManager = primeNumbersService;
        this.sharedResource = sharedResource;
        this.selfResource = new Resource(localFileName);
    }

    @Override
    public Resource call() {
        while (primeNumbersManager.hasNext()) {
            lock.lock();
            var nextPrimeNumber = primeNumbersManager.getNext();
            writeIn(sharedResource, nextPrimeNumber);
            lock.unlock();
            writeIn(selfResource, nextPrimeNumber);
        }
        return selfResource;
    }

    private void writeIn(Resource resource, int nextPrime) {
        if (nextPrime != -1) {
            resource.write(nextPrime);
        }
    }
}
