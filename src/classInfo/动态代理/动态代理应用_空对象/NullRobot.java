package classInfo.动态代理.动态代理应用_空对象;

import java.lang.reflect.Proxy;

/**
 * Created by wulei on 16/2/15.
 */
public class NullRobot {
    public static Robot
    newNullRobot(Class<? extends Robot> type){
        return (Robot) Proxy.newProxyInstance(
                NullRobot.class.getClassLoader(),
                new Class[]{Null.class,Robot.class},
                new NullRobotProxyHandler(type));
    }
    public static void main(String[] args){
        Robot[] robots={
                new SnowRemovalRobot("SnowBee"),
                newNullRobot(SnowRemovalRobot.class)
        };
        for(Robot bot:robots)
            Robot.Test.test(bot);
    }
}
