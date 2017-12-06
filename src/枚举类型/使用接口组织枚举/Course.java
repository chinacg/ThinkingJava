package 枚举类型.使用接口组织枚举;

import 枚举类型.随机选取.Enums;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/3/25.
 * 枚举的枚举
 */
public enum Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURS(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffe.class);
    private Food[] values;
    Course(Class<? extends Food> kind){
        values=kind.getEnumConstants();
    }
    public Food randomSelection(){
        return Enums.random(values);
    }
    //创建一份菜单
    public static void main(String[] args){
        for(int i=0;i<10;i++){
            for(Course course:Course.values()){
                Food food=course.randomSelection();
                print(food);
            }
            print("-----");
        }
    }

}
