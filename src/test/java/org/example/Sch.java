package org.example;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import com.google.common.base.Stopwatch;

public class Sch {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sch.class);

    public interface Callable<T> {
        T call();
    }

    public static <T> T assertExecutionTime(Callable<T> runnable, int timeoutInMillis) {
        var stopwatch = Stopwatch.createStarted();
        var result = runnable.call();
        var elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        LOGGER.info(() -> "Execution time: " + elapsed + " ms");
        Assertions.assertTrue(elapsed < timeoutInMillis);
        return result;
    }

    public static void assertExecutionTime(Runnable runnable, int timeoutInMillis) {
        var stopwatch = Stopwatch.createStarted();
        runnable.run();
        Assertions.assertTrue(stopwatch.elapsed(TimeUnit.MILLISECONDS) < timeoutInMillis);
    }
}
