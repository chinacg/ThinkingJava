package 泛型.泛型通配符.基本的概念和要点;

import 泛型.泛型通配符.item.Apple;
import 泛型.泛型通配符.item.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wulei on 16/2/18.
 * 容器不支持协变的部分特性
 */
public class GenericsAndCovariance {
    public static void main(String[] args){
        List<? extends Fruit> flist=new ArrayList<Apple>();

        //flist的唯一限制是必须持有某种具体的Fruit或Fruit的子类型.
        //但是并不知道确切的类型,所以不能安全的向其中添加对象.编译器会阻止这样的转型,
        // 甚至是加入Object类型也是不允许的

        //flist.add(new Apple());
        //flist.add(new Fruit());
        //flist.add(new Object());
        flist.add(null);//没有什么意义

        Fruit f=flist.get(0); //至少编译器可以认为这个容器可以取出Fruit类型

        //Apple apple=flist.get(0); 编译错误,flist容器不能保存非Fruit类型
    }
}
