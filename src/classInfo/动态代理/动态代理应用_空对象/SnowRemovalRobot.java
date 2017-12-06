package classInfo.动态代理.动态代理应用_空对象;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wulei on 16/2/15.
 * 扫雪机器人
 */
public class SnowRemovalRobot implements Robot{
    private String name;

    public SnowRemovalRobot(String name){
        this.name=name;
    }
    @Override
    public String name() {
        return name;
    }

    @Override
    public String model() {
        return "SnowBot Series 11";
    }

    @Override
    public List<Operation> operations() {
        return Arrays.asList(new Operation() {
            @Override
            public String description() {
                return name+" can shovel snow";
            }

            @Override
            public void command() {
                 System.out.println(name+" shoveling snow");
            }
        },
        new Operation(){
            @Override
            public String description() {
                return name+" can chip ice";
            }

            @Override
            public void command() {
               System.out.println(name+" chipping ice");
            }
        });
    }
    public static void main(String[] args){
        Robot.Test.test(new SnowRemovalRobot("Slusher"));
    }
}
