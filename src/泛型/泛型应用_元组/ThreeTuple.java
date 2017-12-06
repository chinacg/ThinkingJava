package 泛型.泛型应用_元组;

/**
 * Created by wulei on 16/2/15.
 */
public class ThreeTuple<A,B,C> extends TwoTuple<A,B> {
    public final C third;
    public ThreeTuple(A a,B b,C c){
        super(a,b);
        third=c;
    }
    public String toString(){
        return "("+first+", "+second+", "+third+")";
    }
}
