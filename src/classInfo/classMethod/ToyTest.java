package classInfo.classMethod;

/**
 * Created by wulei on 16/1/28.
 */
public class ToyTest {
    static void printInfo(Class cc) {
        System.out.println("Class name:" + cc.getName() + " is " +
                " interface? [" + cc.isInterface() + "]");
        System.out.println("Simple name: " + cc.getSimpleName());
        System.out.println("Canonical name :" + cc.getCanonicalName());
    }

    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("classInfo.classMethod.FancyToy");//创建了一个指向 FancyToy 的 Class引用,必须使用全限定名
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find FancyToy");
            System.exit(1);
        }
        printInfo(c);
        for (Class face : c.getInterfaces())
            printInfo(face);
        Class up = c.getSuperclass();//获取c的父类的 Class 引用
        Object obj = null;
        try {
            //需要默认的构造器
            obj = up.newInstance();
        } catch (InstantiationException e) {
            System.out.println("Cannot instantiate");//newInstance()方法根据 Class 引用创建一个实例,但是这个类必须有默认的构造器
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.out.println("Cannot access");
            System.exit(1);
        }
        printInfo(obj.getClass());

    }
}
