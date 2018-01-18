package 并发.utils;


/**
 * 适用于future.get 抛出的RuntimeException异常
 */
public class LaunderThrowable {
    public static RuntimeException launderThrowable(Throwable throwable) {
        if (throwable instanceof RuntimeException)
            return (RuntimeException) throwable;
        else if (throwable instanceof Error)
            throw (Error) throwable;
        else
            throw new IllegalStateException("Not unchecked", throwable);
    }
}
