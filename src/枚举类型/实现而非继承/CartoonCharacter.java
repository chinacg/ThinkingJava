package 枚举类型.实现而非继承;

import 泛型.泛型接口.Generator;

import java.util.Random;

/**
 * Created by wulei on 16/3/24.
 * 所有enum都继承自 java.lang.Enum 类,
 * 由于 java 不支持多重继承,所以 enum 不能再继承自
 * 其他类.
 * 但是可以同时实现一个或者多个接口
 */
public enum CartoonCharacter implements Generator<CartoonCharacter> {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
    private Random rand=new Random(47);
    @Override
    public CartoonCharacter next() {
        return values()[rand.nextInt(values().length)];
    }
}
