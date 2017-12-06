package classInfo.动态代理.动态代理应用_空对象;

import java.util.List;

/**
 * Created by wulei on 16/2/15.
 * Robot接口
 */
public interface Robot {
    String name();
    String model();
    List<Operation> operations();
    class Test{//嵌套类用来测试
        public static void test(Robot r){
            if(r instanceof Null)
                System.out.println("[Null Robot]");
            System.out.println("Robot name:"+r.name());
            System.out.println("Robot model:"+r.model());
            for(Operation operation:r.operations()){
                System.out.println(operation.description());
                operation.command();
            }

        }
    }
}
