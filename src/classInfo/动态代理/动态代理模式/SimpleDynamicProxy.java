package classInfo.动态代理.动态代理模式;

import classInfo.动态代理.基本代理方式.Interface;
import classInfo.动态代理.基本代理方式.RealObject;

import java.lang.reflect.Proxy;

/**
 * Created by wulei on 16/2/2.
 */
public class SimpleDynamicProxy {
    public static void consumer(Interface iface){
        iface.doSomething();
        iface.somethingElse("bobobo");
    }
    public static void main(String[] args){
        RealObject real =new RealObject();
        consumer(real);
        //插入一个代理并调用
        Interface proxy=(Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),//传递类加载器
                new Class[]{Interface.class},//传递该代理希望实现的接口列表
                new DynamicProxyHandler(real));//传递自定义代理器
        consumer(proxy);
    }
}
