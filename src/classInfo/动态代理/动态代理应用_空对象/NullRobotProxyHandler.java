package classInfo.动态代理.动态代理应用_空对象;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * Created by wulei on 16/2/15.
 * 空的Robot类型代理
 */
public class NullRobotProxyHandler implements InvocationHandler {
    private String nullName;
    private Robot proxied=new NRobot();

    NullRobotProxyHandler(Class<? extends Robot> type) {
        nullName = type.getSimpleName() + " NullRobot";
    }

    private class NRobot implements Null, Robot {

        @Override
        public String name() {
            return nullName;
        }

        @Override
        public String model() {
            return nullName;
        }

        @Override
        public List<Operation> operations() {
            return Collections.emptyList();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxied,args);
    }
}
