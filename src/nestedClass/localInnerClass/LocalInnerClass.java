package nestedClass.localInnerClass;

/**
 * Created by wulei on 16/1/14.
 * 比较局部内部类和匿名类的区别
 */
public class LocalInnerClass {
    private int count=0;
    interface Counter{
        int next();
    }

    /**
     * 包含一个局部内部类的方法
     * @param name
     * @return
     */
    Counter getCounter(final String name){
        class LocalCounter implements Counter{
            public LocalCounter(){
                //局部内部类可以拥有一个构造器
                System.out.println("LocalCounter()");
            }
            @Override
            public int next() {
                System.out.print(name);//对本地参数使用需把参数设置为 final 类型的数据
                return count++;
            }
        }
        return new LocalCounter();
    }

    /**
     * 包含一个匿名内部类的方法
     * @param name
     * @return
     */
    Counter getCounter2(final String name){
     return new Counter() {
         //匿名内部类不能拥有一个具有名称的构造器,只能通过实例初始化来进行自身的初始化操作
         {
             System.out.println("Counter()");
         }
         @Override
         public int next() {
             System.out.print(name);//对本地参数使用需把参数设置为 final 类型的数据
             return count++;
         }
     };
    }
    public static void main(String args[]){
        LocalInnerClass lic=new LocalInnerClass();
        Counter
                c1=lic.getCounter("Local inner "),
                c2=lic.getCounter2("Anonymous inner ");
        for(int i=0;i<5;i++)
            System.out.println(c1.next());
        for(int i=0;i<5;i++)
            System.out.println(c2.next());
    }
}/*
LocalCounter()
Counter()
Local inner 0
Local inner 1
Local inner 2
Local inner 3
Local inner 4
Anonymous inner 5
Anonymous inner 6
Anonymous inner 7
Anonymous inner 8
Anonymous inner 9*/



