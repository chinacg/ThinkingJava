package classInfo.动态代理.动态代理模式;

import classInfo.动态代理.基本代理方式.SimpleProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

/**
 * Created by wulei on 16/2/2.
 */
public class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;
    private DynamicProxyHandler(){}//.newInstance()创建实例需要默认构造函数
    public DynamicProxyHandler(Object proxied){
        this.proxied=proxied;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("*** proxy: "+proxy.getClass()+
        ", method: "+method+", args: "+args);
        if(args!=null)
            for(Object arg:args)
            System.out.println(" "+arg);
        Method method1=DynamicProxyHandler.class.getMethod("show",String[].class);
        String[] temp=new String[]{"aa","bb"};
        method1.invoke(DynamicProxyHandler.class.newInstance(),(Object) temp);//动态调用另外一个定义的函数
        return method.invoke(proxied,args);
    }
    public void show(String[] args){
        System.out.print("另加一个调用: ");
        for(String arg:args)
        System.out.println(arg);
    }
}
