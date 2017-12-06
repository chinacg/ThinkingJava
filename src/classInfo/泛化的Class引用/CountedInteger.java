package classInfo.泛化的Class引用;

/**
 * Created by wulei on 16/1/31.
 */
public class CountedInteger {
    private static long counter;
    private final long id=counter++;
    public String toString(){return Long.toString(id);}
}
