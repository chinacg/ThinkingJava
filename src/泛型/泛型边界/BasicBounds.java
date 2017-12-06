package 泛型.泛型边界;

/**
 * Created by wulei on 16/2/17.
 */
public class BasicBounds {
    public static  void main(String[] args){
        Solid<Bounded> solid=new Solid<Bounded>(new Bounded());
        solid.color();
        solid.getY();
        solid.weight();
    }
}
