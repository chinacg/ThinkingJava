package 枚举类型.使用enum的状态机;

import java.util.Random;

/**
 * Created by wulei on 16/3/29.
 * 自动售货机枚举类
 */
public enum Input {
    NICKEL(5),DIME(20),QUARTER(25),DOLLAR(100),
    TOOTHPASTE(200),SOAP(50),
    ABORT_TRANSACTION{
        public int amount(){//覆写
            throw new RuntimeException("ABORT.amount()");
        }
    },
    STOP{//必须是最后一个实例
        public int amount(){//覆写
            throw new RuntimeException("SHUT_DOWN_amount()");
        }
    };
    int value;
    Input(){}
    Input(int value){this.value=value;}
    int amount(){return value;}
    static Random rand=new Random(47);
    public static Input randomSelection(){
        //不要包含STOP
        return values()[rand.nextInt(values().length-1)];
    }
}
