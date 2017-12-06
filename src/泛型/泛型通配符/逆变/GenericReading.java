package 泛型.泛型通配符.逆变;

import 泛型.泛型通配符.item.Apple;
import 泛型.泛型通配符.item.Fruit;
import 泛型.泛型通配符.item.Jonathan;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wulei on 16/2/18.
 */
public class GenericReading {
    static <T> T readExact(List<T> list) {
        return list.get(0);
    }

    static List<Apple> apples = Arrays.asList(new Apple());
    static List<Fruit> fruit = Arrays.asList(new Fruit());
    static List<Jonathan> jonathen=Arrays.asList(new Jonathan());
    static void f1() {
        Apple a = readExact(apples);
        Fruit f = readExact(fruit);
        f = readExact(apples);
    }

    static class Reader<T> {
        T readExact(List<T> list) {
            return list.get(0);
        }
    }

    static void f2() {
        Reader<Fruit> fruitReader = new Reader<Fruit>();
        Fruit f = fruitReader.readExact(fruit);

        //readExact(List<Fruit> 不能接收List<Apple>
        //Fruit a=fruitReader.readExact(apples);
    }

    static class CovariantReader<T> {
        /**
         * 协变规定上界,这个容器只能取出T的类型或者T的父类型,
         * 并且不能保存任何类型的对象
         * @param list
         * @return
         */
        T readCovariant(List<? extends T> list) {
            //list.add(new Object());error,不能写入
            return list.get(0);
        }

        /**
         * 逆变规定下界,这个容器能写入T类型或者T类型的子类型
         * @param list
         * @param item
         */
        void write(List<? super T> list, T item) {
            list.get(0);
            list.add(item);
        }

        /**
         * 逆变规定下界,这个容器能读T类型或者T类型的子类型
         * @param list
         * @return
         */
        T read(List<? super T> list){
            return (T) list.get(0);
        }
    }

    static void f3() {
        CovariantReader<Fruit> fruitReader =
                new CovariantReader<Fruit>();
        Fruit f = fruitReader.readCovariant(fruit);
        Fruit a = fruitReader.readCovariant(apples);
        //Apple c=fruitReader.readCovariant(apples); 错误,只能返回Fruit类型.
        fruitReader.write(fruit, new Apple());fruitReader.write(fruit,new Jonathan());fruitReader.write(fruit,new Fruit()); //逆变类型容器可以写入Fruit类型或者其子类型
        Fruit x=fruitReader.read(fruit);


        CovariantReader<Apple> fruitReader2=
                new CovariantReader<Apple>();
        Fruit d=fruitReader2.readCovariant(apples);
        Apple e=fruitReader2.readCovariant(jonathen);
        fruitReader2.write(apples,new Apple());fruitReader2.write(apples,new Jonathan());
        fruitReader2.write(fruit,new Apple());fruitReader2.write(fruit,new Jonathan());

        Fruit g=fruitReader2.read(apples);
    }

    public static void main(String[] args) {
        f1();
        f2();
        f3();
    }
}
