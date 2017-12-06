package 新IO.对象序列化.序列化的控制;

import java.io.*;

/**
 * Created by wulei on 16/3/14.
 * 利用Externalizable接口来序列化对象和Serializable不同,
 * Serializable对象完全以它存储的二进制位为基础来构造,而不调用
 * 构造器.而对于一个Externalizable对象,所有普通的默认构造器都会
 * 被调用,才能使Externalizable对象产生正确的行为.
 *
 */
public class Blips {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Constructing objects");
        Blip1 b1 = new Blip1();
        Blip2 b2 = new Blip2();

        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Blips.out"));
        System.out.println("Saving objects:");
        o.writeObject(b1);
        o.writeObject(b2);
        o.close();
        //恢复对象
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blips.out"));
        System.out.println("Recovering b1:");
        b1=(Blip1)in.readObject();//恢复b1后,会调用Blip1默认的构造器,再执行readExternal
        //以下代码报错,Blip2类型不能恢复,因为缺少默认的构造器
        System.out.println("Recovering b2:");
        b2=(Blip2)in.readObject();

    }

}
