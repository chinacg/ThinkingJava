package 枚举类型.基本enum特性;


import static net.mindview.util.Print.*;

/**
 * Created by wulei on 16/3/23.
 * Enum 提供的一些基本功能
 */
public enum Shrubbery {
    GROUND, CRAWLING, HANGING;

    public static void main(String[] args) {
        for (Shrubbery s : Shrubbery.values()) {
            print(s + " ordinal:" + s.ordinal());//ordinal()方法返回一个 int 值,这是每个 enum 实例在声明时的次序.
            printnb(s.compareTo(Shrubbery.CRAWLING) + " ");
            printnb(s.equals(Shrubbery.CRAWLING) + " ");
            print(s == Shrubbery.CRAWLING);
            print(s.getDeclaringClass());
            print(s.name());//name()方法与使用 toString()方法效果相同,返回 enum 实例声明时的名字
            print("---------------------------");
        }
        //根据字符串名称获取枚举实例
        for (String s : "HANGING CRAWLING GROUND".split(" ")) {
            Shrubbery shrub = Enum.valueOf(Shrubbery.class, s);//valueOf()是在 Enum 中定义的 static 方法,根据给定的名字返回相应的实例
            print(shrub);
        }
    }

}
