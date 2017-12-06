package 泛型.泛型边界;

import java.awt.*;

/**
 * Created by wulei on 16/2/17.
 */
//T 类型边界之能扩展自一个class但是可以是多个接口
 class Solid<T extends Dimension & HasColor & Weight> {
    T item;
    Solid(T item){this.item=item;}
    T getItem(){return item;}
    Color color(){return item.getColor();}
    int getX(){return item.x;}
    int getY(){return item.y;}
    int getZ(){return item.z;}
    int weight(){return item.weight();}
}
