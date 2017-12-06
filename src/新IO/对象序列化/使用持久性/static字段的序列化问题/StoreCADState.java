package 新IO.对象序列化.使用持久性.static字段的序列化问题;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wulei on 16/3/21.
 * 保存对象的状态, Class 是 Serializable 的,因此只需直接对 Class 对象序列化,
 * 就可以很容易的保存 static 字段.
 */
public class StoreCADState {
    public static void main(String[] args) throws IOException {
        List<Class<? extends Shape>> shapeTypes=new ArrayList<Class<? extends Shape>>();
        //添加引用
        shapeTypes.add(Circle.class);
        shapeTypes.add(Square.class);
        shapeTypes.add(Line.class);
        List<Shape> shapes=new ArrayList<Shape>();
        //创建一些 Shape
        for(int i=0;i<10;i++)
            shapes.add(Shape.randomFactory());
        //把对象的所有静态变量 color 设置为 GREEN.
        for(int i=0;i<10;i++)
            ((Shape)shapes.get(i)).setColor(Shape.GREEN);
        //保存状态:
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("CADState.out"));
        out.writeObject(shapeTypes);
        Line.serializeStaticState(out);//显示地调用 Line 的静态方法serializeStaticState()来对静态变量写入同一个输出流
        out.writeObject(shapes);
        //显示 shapes
        System.out.println(shapes);
    }
}
