package classInfo.类的加载准备工作;

/**
 * Created by wulei on 16/1/28.
 */
public class Initable {
    static final int staticFinal=47;
    static final int staticFinal2=ClassInitialization.rand.nextInt(1000);
    static {
        System.out.println("Initializing Initable");
    }
}
