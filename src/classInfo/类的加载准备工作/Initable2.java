package classInfo.类的加载准备工作;

/**
 * Created by wulei on 16/1/28.
 */
public class Initable2 {
    static int staticNonFinal=147;
    static {
        System.out.println("Initializing Initable2");
    }
}
