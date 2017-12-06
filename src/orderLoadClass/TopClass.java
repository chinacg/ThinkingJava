package orderLoadClass;

/**
 * Created by wulei on 16/1/13.
 */
public class TopClass {
    private int CommonTopIntegerInit=initCommonInteger();
    private int CommonTopIntegerNonInit;
    private Integer commonTopInteger=setAndShowInteger(0);
    private static Integer firstTopInteger=setAndShowInteger(1);
    private static Integer secondTopInteger=setAndShowInteger(2);
    protected static Integer setAndShowInteger(int toSetInteger){
        System.out.println("set the integer to"+toSetInteger);
        return toSetInteger;
    }
    protected static int initCommonInteger(){
        System.out.println("init CommonTopIntegerInit");
        return 0;
    }
    public TopClass(){
        System.out.println("top class constructor called");
        System.out.println("CommonTopIntegerNonInit:" +CommonTopIntegerNonInit);

    }
}
