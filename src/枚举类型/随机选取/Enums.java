package 枚举类型.随机选取;

import java.util.Random;

/**
 * Created by wulei on 16/3/24.
 */
public class Enums {
    private static Random rand=new Random(47);
    public static <T extends Enum<T>> T random(Class<T> ec){
        return random(ec.getEnumConstants());
    }
    public static <T> T random(T[] values){
        return values[rand.nextInt(values.length)];
    }
}
