package 泛型.泛型方法;

import 泛型.泛型接口.Coffee;
import 泛型.泛型接口.CoffeeGenerator;
import 泛型.泛型接口.Generator;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by wulei on 16/2/15.
 * 一个基本原则:无论何时,只要能做到,就应该尽量使用泛型方法.而不是将整个类泛型化
 */
public class Generators {
    public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }

    public static void main(String[] args) {
        Collection<Coffee> coffee = fill(
                new ArrayList<Coffee>(), new CoffeeGenerator(), 4);
        for (Coffee c : coffee)
            System.out.println(c);
    }
}
