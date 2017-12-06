package classInfo.泛化的Class引用;

import classInfo.classMethod.FancyToy;
import classInfo.classMethod.Toy;

/**
 * Created by wulei on 16/1/31.
 */
public class GenericToyTest {
    public static void main(String[] args) throws Exception{
        Class<FancyToy> ftClass=FancyToy.class;
        FancyToy fancyToy=ftClass.newInstance();
        System.out.println(fancyToy instanceof FancyToy);
        Class<? super FancyToy> up=ftClass.getSuperclass();
        //下面获得FancyToy的Class引用的方法会在编译器报错:
        //Class<Toy> up2=ftClass.getSuperclass();
        //实例化仅仅返回Object类型:
        Object obj=up.newInstance();


        Toy t=new FancyToy();
        Class<FancyToy> fancyToyClass=FancyToy.class;
        FancyToy fancyToy1=fancyToyClass.cast(t);//cast()方法
            //接受参数对象,并将其转型为Class引用类型.


    }
}
