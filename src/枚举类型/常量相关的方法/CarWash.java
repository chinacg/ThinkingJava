package 枚举类型.常量相关的方法;

import java.util.EnumSet;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/3/28.
 * 洗车类,包含一个选择菜单,每个选择对应一个不同的动作.
 */
public class CarWash {
    public enum Cycle {
        UNDERBODY {
            @Override
            void action() {
                print("Spraying the underbody");
            }
        },
        WHEELWASH {
            @Override
            void action() {
                print("Washing the wheels");

            }
        },
        PREWASH {
            @Override
            void action() {
                print("Loosening the dirt");
            }
        },
        BASIC {
            @Override
            void action() {
                print("The basic wash");
            }
        },
        HOTWAX {
            @Override
            void action() {
                print("Applying hot wax");
            }
        },
        RINSE {
            @Override
            void action() {
                print("Rinsing");
            }
        },
        BLOWDRY {
            @Override
            void action() {
                print("Blowing dry");
            }
        };

        abstract void action();
    }

    EnumSet<Cycle> cycles =
            EnumSet.of(Cycle.BASIC, Cycle.RINSE);

    public void add(Cycle cycle) {
        cycles.add(cycle);
    }

    public void washCar() {
        for (Cycle c : cycles)
            c.action();
    }

    @Override
    public String toString() {
        return cycles.toString();
    }

    public static void main(String[] args) {
        CarWash wash = new CarWash();//创建CarWash的实例,cycles常量包含有默认的Cycle实例选项
        print(wash);
        wash.washCar();
        //添加Cycle实例的顺序不重要,因为输出的顺序决定于enum实例定义时的顺序
        wash.add(Cycle.BLOWDRY);
        wash.add(Cycle.BLOWDRY);//重复添加会被忽略,因为EnumSet是不包含重复的枚举实例的
        wash.add(Cycle.RINSE);
        wash.add(Cycle.HOTWAX);
        print(wash);
        wash.washCar();
    }/*output:
    [BASIC, RINSE]
    The basic wash
    Rinsing
    [BASIC, HOTWAX, RINSE, BLOWDRY]
    The basic wash
    Applying hot wax
    Rinsing
    Blowing dry
    */
}
