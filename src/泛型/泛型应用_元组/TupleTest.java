package 泛型.泛型应用_元组;

/**
 * Created by wulei on 16/2/15.
 */
public class TupleTest {
    static class Amphibian{}
    static class Vehicle{}

    static TwoTuple<String,Integer> f(){
        return new TwoTuple<String, Integer>("hi",47);
    }

    static FourTuple<Vehicle,Amphibian,String,Integer> k(){
        return new
                FourTuple<Vehicle, Amphibian, String, Integer>(new Vehicle(),new Amphibian(),"hi",12);
    }

    public static void main(String[] args){
        TwoTuple<String,Integer> ttsi=f();
        System.out.println(ttsi);
        System.out.println(k());
    }
}
