package classInfo.类的加载准备工作;

/**
 * Created by wulei on 16/1/28.
 */
public class Initable3 {
    static int staticNonFinal=74;
    static {
        System.out.println("Inititalizing Initable3");
    }
}
