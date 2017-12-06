package 泛型.泛型通配符.逆变;

import 泛型.泛型通配符.item.Apple;
import 泛型.泛型通配符.item.Fruit;
import 泛型.泛型通配符.item.Jonathan;

import java.util.List;

/**
 * Created by wulei on 16/2/18.
 * 超类型通配符<? super MyClass>
 */
public class SuperTypeWildcards {
    /**
     * 参数Apple是Apple的某种基类型的List,这样就知道向
     * 其中添加Apple或Apple的子类型是安全的.
     * 既然Apple是下界,那么向这个List中添加Fruit是不安全的
     * 因为这将使这个List敞开口子,从而可以向其中添加非Apple类型的
     * 对象,这是违反静态类型安全的
     * @param apples
     */
    static void writeTo(List<? super Apple> apples){
        apples.add(new Apple());
        apples.add(new Jonathan());

        //apples.add(new Fruit());

        Fruit fruit=(Fruit) apples.get(0);
    }
}
