package 枚举类型.使用EnumMap;

import 枚举类型.使用EnumSet替代标志.AlarmPoints;

import java.util.EnumMap;
import java.util.Map;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;
import static 枚举类型.使用EnumSet替代标志.AlarmPoints.BATHROOM;
import static 枚举类型.使用EnumSet替代标志.AlarmPoints.KITCHEN;
import static 枚举类型.使用EnumSet替代标志.AlarmPoints.UTILITY;

/**
 * Created by wulei on 16/3/28.
 * EnumMap是一种特殊的Map,它要求其中的键必须来自一个enum.
 */
public class EnumMaps {
    public static void main(String[] args) {
        EnumMap< AlarmPoints, Command> em = new EnumMap<AlarmPoints, Command>(AlarmPoints.class);
        em.put(KITCHEN, new Command() {
            @Override
            public void action() {
                print("Kitchen fire!");
            }
        });
        em.put(BATHROOM, new Command() {
            @Override
            public void action() {
                print("Bathroom alert!");
            }
        });
        for(Map.Entry<AlarmPoints,Command> e:em.entrySet()){
            printnb(e.getKey()+": ");
            e.getValue().action();
        }
        try {//检查如果没有一个对应特殊键的值
            em.get(UTILITY).action();
        }catch (Exception e){
            print(e);
        }
    }
}
