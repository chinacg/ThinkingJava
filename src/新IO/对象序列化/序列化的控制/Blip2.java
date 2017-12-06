package 新IO.对象序列化.序列化的控制;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by wulei on 16/3/17.
 */
public class Blip2 implements Externalizable {
    Blip2(){//默认构造方法不是public的这样反序列化操作会报错
        System.out.println("Blip2 Constructor");
    }
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip2.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip2.readExternal");
    }
}
