package 泛型.泛型边界;

import java.awt.*;

/**
 * Created by wulei on 16/2/17.
 */
 class Colored<T extends HasColor> {
    T item;
    Colored(T item){this.item=item;}
    T getItem(){return item;}
    Color color(){return item.getColor();}
}
