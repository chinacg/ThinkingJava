package 泛型.泛型通配符.逆变;

import 泛型.泛型通配符.item.Apple;
import 泛型.泛型通配符.item.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wulei on 16/2/18.
 */
public class GenericWriting {
    static <T> void writeExact(List<T> list,T item){
        list.add(item);
    }
    static List<Apple> apples=new ArrayList<Apple>();
    static List<Fruit> fruits=new ArrayList<Fruit>();
    static void f1(){
        writeExact(apples,new Apple());
        writeExact(fruits,new Apple());
    }

    static <T> void writeWithWildcard(List<? super T>list,T item){
        list.add(item);
    }

    static void f2(){
        writeWithWildcard(apples,new Apple());
        writeWithWildcard(fruits,new Apple());
    }
    public static void main(String[] args){
        f1();f2();
    }
}
