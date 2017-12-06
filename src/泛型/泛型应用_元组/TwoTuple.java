package 泛型.泛型应用_元组;

/**
 * Created by wulei on 16/2/15.
 * 二维元组
 */
public class TwoTuple<A,B> {
    public final A first;
    public final B second;
    public TwoTuple(A a,B b){first=a;second=b;}
    public String toString(){
        return "("+first+", "+second+")";
    }
}
