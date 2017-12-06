package 泛型.泛型边界;

import java.awt.*;

/**
 * Created by wulei on 16/2/17.
 */

//T 的边界类型先扩展class然后才是接口
class ColoredDimension<T extends Dimension & HasColor> {
    T item;
    ColoredDimension(T item){this.item=item;}
    T getItem(){return item;}
    Color color(){return item.getColor();}
    int getX(){return item.x;}
    int getY(){return item.y;}
    int getZ(){return item.z;}
}
