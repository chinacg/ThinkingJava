package 新IO.对象序列化.使用持久性.static字段的序列化问题;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by wulei on 16/3/21.
 * 恢复对象CADState
 */
public class RecoverCADState {
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("CADState.out"));
         //根据写入的顺序读取
        List<Class<? extends Shape>> shapeTypes=(List<Class<? extends Shape>>) in.readObject();
        Line.deserializeStaticState(in);//手动调用 Line 的静态方法deserializeStaticState()来读取写入的静态变量
        List<Shape> shapes=(List<Shape>)in.readObject();
        System.out.println(shapes);//Line对象通过 Class 调用静态方法的方式来手动的写入和读取静态变量 color,而 Circle和 Squarecolor
                                    //没有这个操作,导致恢复数据时,对象的的静态变量还是初始化时的值.
    }
}
/*

[class 新IO.对象序列化.使用持久性.static字段的序列化问题.Circlecolor[1]xPos[58] yPos[55] dim[93]
, class 新IO.对象序列化.使用持久性.static字段的序列化问题.Squarecolor[0]xPos[61] yPos[61] dim[29]
, class 新IO.对象序列化.使用持久性.static字段的序列化问题.Linecolor[3]xPos[68] yPos[0] dim[22]
, class 新IO.对象序列化.使用持久性.static字段的序列化问题.Circlecolor[1]xPos[7] yPos[88] dim[28]
, class 新IO.对象序列化.使用持久性.static字段的序列化问题.Squarecolor[0]xPos[51] yPos[89] dim[9]
, class 新IO.对象序列化.使用持久性.static字段的序列化问题.Linecolor[3]xPos[78] yPos[98] dim[61]
, class 新IO.对象序列化.使用持久性.static字段的序列化问题.Circlecolor[1]xPos[20] yPos[58] dim[16]
, class 新IO.对象序列化.使用持久性.static字段的序列化问题.Squarecolor[0]xPos[40] yPos[11] dim[22]
, class 新IO.对象序列化.使用持久性.static字段的序列化问题.Linecolor[3]xPos[4] yPos[83] dim[6]
, class 新IO.对象序列化.使用持久性.static字段的序列化问题.Circlecolor[1]xPos[75] yPos[10] dim[42]
]
 */
