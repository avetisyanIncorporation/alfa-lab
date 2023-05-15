package lab.alfa.task.threads;

import lab.alfa.task.threads.prime.PrimeNumbersManagerAbstract;
import lab.alfa.task.threads.resource.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

import static lab.alfa.task.threads.utils.FutureUtils.getResultOf;

@Service
public record ThreadsManager(@Qualifier("withExecutor") PrimeNumbersManagerAbstract primeNumbersManager) {

    private static final String RESULT_FILE = "Result.txt";
    private static final String THREADS_FILE_PATTERN = "Thread{N}.txt";

    public void manage(int threadsCount, int numbersLimit) {
        var sharedResource = new Resource(RESULT_FILE);
        var lock = new ReentrantLock();
        primeNumbersManager.init(numbersLimit, threadsCount);

        var executor = Executors.newFixedThreadPool(threadsCount);
        var futures = submitFutures(executor, lock, sharedResource, threadsCount);
        closeResources(futures, sharedResource);
        executor.shutdown();
    }

    private List<Future<Resource>> submitFutures(ExecutorService executor, ReentrantLock lock,
                                                 Resource sharedResource, int threadsCount) {
        var futures = new ArrayList<Future<Resource>>(threadsCount);
        for (int i = 1; i <= threadsCount; i++) {
            futures.add(
                    executor.submit(
                            new FilesWriterTask(lock, primeNumbersManager, sharedResource, selfFileName(i))
                    )
            );
        }
        return futures;
    }

    private void closeResources(List<Future<Resource>> resourceFutures, Resource sharedResource) {
        for (var resourceFuture : resourceFutures) {
            getResultOf(resourceFuture).close();
        }
        sharedResource.close();
    }

    private String selfFileName(int threadNumber) {
        return THREADS_FILE_PATTERN.replaceAll("\\{N}", String.valueOf(threadNumber));
    }
}

