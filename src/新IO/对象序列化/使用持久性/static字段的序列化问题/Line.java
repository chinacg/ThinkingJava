package 新IO.对象序列化.使用持久性.static字段的序列化问题;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by wulei on 16/3/21.
 */
public class Line extends Shape {
    private static int color = RED;

    public static void
    serializeStaticState(ObjectOutputStream os) throws IOException {
        os.writeInt(color);//手动写入静态变量
    }
    public static void
    deserializeStaticState(ObjectInputStream os) throws IOException {
            color=os.readInt();//手动读取静态变量
    }

    @Override
    public void setColor(int newColor) {
      color=newColor;
    }

    @Override
    public int getColor() {
        return color;
    }

    public Line(int xVal, int yVal, int dim){
        super(xVal,yVal,dim);
    }
}
