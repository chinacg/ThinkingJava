package classInfo.泛化的Class引用;

/**
 * Created by wulei on 16/1/29.
 */
public class BoundedClassReferences {
    public static void main(String[] args){
        Class<? extends Number> bounded=int.class;
        bounded=double.class;
        bounded=Number.class;
        //bounded 可以是任何Number类的扩展类的引用
    }
}
