package classInfo.类的加载准备工作;

import java.util.Random;

/**
 * Created by wulei on 16/1/28.
 */
public class ClassInitialization {
    public static Random rand=new Random(47);
    public static void main(String[] args) throws Exception{
        Class initable=Initable.class;//初始化实现了其惰性,仅使用.class语法来获得对类的引用不会引发初始化

        System.out.println("After creating Initable ref");
        //不会触发初始化:
        System.out.println(Initable.staticFinal);//如果一个static final 值是"编译期常量",不需要对类进行初始化
        //触发初始化:
        System.out.println(Initable.staticFinal2);//如果一个 static final变量不是"编译期常量",类进行初始化
        //触发初始化:
        System.out.println(Initable2.staticNonFinal);//不是 final 的 static 变量会触发链接和初始化操作
        Class initable3=Class.forName("classInfo.类的加载准备工作.Initable3");//为了产生 Class引用,Class.forName()立即触发了类的初始化
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);
    }
}
