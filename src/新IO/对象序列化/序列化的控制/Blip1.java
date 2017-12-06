package 新IO.对象序列化.序列化的控制;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by wulei on 16/3/14.
 * 如果不希望对象的某一部分被序列化;或者一个对象被还原后,某
 * 子对象需要重新创建,从而不必将该子对象序列化.这些特殊情况下,可以
 * 通过实现Externalizable接口----替代Serializable接口---来对
 * 序列化进程进行控制.这个Externalizable接口继承了Serializable接口,同时
 * 增添了两个方法:writeExternal()和readExternal().这两个方法会在序列化和
 * 反序列化还原的过程中被自动调用,以便执行一些特殊操作.
 */
public class Blip1 implements Externalizable {
    public Blip1(){//恢复Blips对象时会调用默认的构造器,必须是public否则会报错
        System.out.println("Blip1 Constructor");
    }
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip1.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip1.readExternal");
    }
}
