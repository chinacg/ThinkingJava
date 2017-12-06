package 枚举类型.使用接口组织枚举;

/**
 * Created by wulei on 16/3/24.
 * 如果想用 enum 来表示不同类型的食物,
 * 同时还想每个 enum 元素仍然保持 Food 类型
 * 可以这样实现
 */
public interface Food {
    enum Appetizer implements Food{
        SALAD,SOUP,SPRING_ROLLS;
    }
    enum MainCourse implements Food{
        LASAGNE,BURRITO,PAD_THAI,
        LENTILS,HUMMOUS,VINDALOO;
    }
    enum Dessert implements Food{
        TIRAMISU,GELATO,BLACK_FOREST_CAKE,
        FRUIT,CREME_CARAMEL;
    }
    enum Coffe  implements Food{
        BLACK_COFFE,DECAT_COFFEE,ESPRESSO,
        LATTE,CAPPUCCINO,TEA,HERB_TEA;
    }

}
