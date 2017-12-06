package 枚举类型.使用接口组织枚举;
import static 枚举类型.使用接口组织枚举.Food.*;
/**
 * Created by wulei on 16/3/24.
 */
public class TypeOfFood {
    public static void main(String[] args){
        Food food= Appetizer.SALAD;
        food=MainCourse.LASAGNE;
        food=Dessert.GELATO;
        food=Coffe.CAPPUCCINO;
    }
}
