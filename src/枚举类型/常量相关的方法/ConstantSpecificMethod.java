package 枚举类型.常量相关的方法;

import java.text.DateFormat;
import java.util.Date;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/3/28.
 * enum允许程序员为它的实例编写方法,从而
 * 为每个enum实例赋予各自不同的行为.为了实现
 * 常量相关的方法,需要为enum定义一个或多个abstract
 * 方法,然后为每个enum实例实现该抽象方法.
 */
public enum ConstantSpecificMethod {
    DATE_TIME {
        @Override
        String getInfo() {
            return
                    DateFormat.getDateInstance().format(new Date());
        }
    },
    CLASSPATH{
        @Override
        String getInfo() {
            return System.getenv("CLASSPATH");
        }
    },
    VERSION{
        @Override
        String getInfo() {
            return System.getProperty("java.version");
        }
    };

    abstract String getInfo();//定义的抽象方法,枚举中的每个实例必须实现这个方法
    public static void main(String[] args){
        for(ConstantSpecificMethod csm:values())
            print(csm.getInfo());
    }
}
