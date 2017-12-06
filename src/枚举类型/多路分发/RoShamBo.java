package 枚举类型.多路分发;

import net.mindview.util.Enums;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/3/30.
 * 剪刀石头布游戏类
 */
public class RoShamBo {
    public static <T extends Competitor<T>>//参数类型必须是Competitor类型用来调用其compete方法
    void match(T a, T b) {
        print(a + " vs. " + b + " :" + a.compete(b));
    }

    public static <T extends Enum<T> & Competitor<T>>//类型参数必须同时是Enum<T>类型（Enums.random中使用）和Competitor类型（传递给match方法用)
       void play(Class<T> rsbClass, int size) {
        for (int i = 0; i < size; i++)
            match(Enums.random(rsbClass), Enums.random(rsbClass));
    }
}
