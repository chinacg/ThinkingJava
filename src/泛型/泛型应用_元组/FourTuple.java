package 泛型.泛型应用_元组;

/**
 * Created by wulei on 16/2/15.
 */
public class FourTuple<A,B,C,D> extends ThreeTuple<A,B,C> {
    public final D fourth;
    public FourTuple(A a,B b,C c,D d){
        super(a,b,c);
        fourth=d;
    }
    public String toString(){
        return "("+first+", "+second+", "+third+", "+fourth+")";
    }
}
