package 泛型.泛型通配符.基本的概念和要点;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wulei on 16/2/18.
 * 容器拒绝向上转型,意思是Apple的List不是Fruit的List,
 * Apple的List将持有Apple和Apple的子类型,而Fruit的List
 * 将持有任何类型的Fruit,诚然,这包括Apple在内,但是它不是一个
 * Apple的List,它仍旧是Fruit的List.Apple的List在类型上不等价于Fruit
 * 的List,即使Apple是一种Fruit类型.
 */
public class NonCovariantGenerics {
    //以下代码编译错误:不能转型
    //List<Fruit> flist=new ArrayList<Apple>();
}
