package 枚举类型.实现而非继承;

import 泛型.泛型接口.Generator;

import java.util.Collection;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/3/24.
 */
public class EnumImplementation {
    public static <T>  void printNext(Generator<T> rg) {
        print(rg.next() + ", ");
    }

    public static void main(String[] args) {
        CartoonCharacter cc = CartoonCharacter.BOB;
        for (int i = 0; i < 10; i++)
            printNext(cc);
    }
}
