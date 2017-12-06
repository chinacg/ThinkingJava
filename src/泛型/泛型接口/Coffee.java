package 泛型.泛型接口;

/**
 * Created by wulei on 16/2/15.
 */
public class Coffee {
    private static long counter=0;
    private final long id=counter++;
    public String toString(){
        return getClass().getSimpleName()+" "+id;
    }
}
