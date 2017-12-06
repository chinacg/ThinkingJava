package classInfo.动态代理.基本代理方式;

/**
 * Created by wulei on 16/2/2.
 */
public class SimpleProxyDemo {
    public static void consumer(Interface iface){
        iface.doSomething();
        iface.somethingElse("bobobo");
    }
    public static void main(String[] args){
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}
