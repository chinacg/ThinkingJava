package orderLoadClass;

/**
 * Created by wulei on 16/1/13.
 */
public class SubClass extends TopClass {
    private int CommonSubIntegerInit=initCommonIntegerSub();
    private int CommonSubIntegerNonInit;
    private String CommonSubStr;
    private static Integer firstSubInteger=setAndShowInteger(3);
    private static Integer secondSubInteger=setAndShowInteger(4);
    private Integer commonSubInteger=setAndShowInteger(5);

    public SubClass(){//构造函数相当于 static 方法,同样会触发加载器加载 class 文件
        System.out.println("sub class constructor called");
        System.out.println("CommonSubIntegerNonInit:" +CommonSubIntegerNonInit);
        System.out.println("CommonSubStr:"+CommonSubStr);
    }
    protected static int initCommonIntegerSub(){
        System.out.println("init CommonSubIntegerInit");
        return 0;
    }
    public static void main(String[] args){//①执行 main的 static 方法,程序加载 .class文件,并发现 TopClass 类有父类,然后继续寻找父类文件,如果父类
                                           //文件还有父类,就继续加载它的父类直至找到基类.然后开始加载基类的 static 成员变量并初始化,完成后继续加载其子类的 static 成员变量及初始化直到最底部
                                           //的 subClass.这样就完成的类的加载过程.static 成员变量只会加载一次.
        SubClass subClass=new SubClass();  //②程序调用子类的构造函数,首先会调用父类的构造函数,依次调用父类的构造函数直至基类.基类初始化时,首先初始化非 static 的成员变量,如果没有赋值就赋予默认
                                           // 值,例如值类型初始化为默认值,对象默认为 null.
                                          //③程序执行完构造函数中的其它操作后继续执行子类的构造函数重复②中的操作
                                          //④整个类的加载和初始化过程结束
    }

    /** 程序运行结果
     * set the integer to1
     set the integer to2
     set the integer to3
     set the integer to4
     init CommonTopIntegerInit
     set the integer to0
     top class constructor called
     CommonTopIntegerNonInit:0
     init CommonSubIntegerInit
     set the integer to5
     sub class constructor called
     CommonSubIntegerNonInit:0
     CommonSubStr:null
     */
}
