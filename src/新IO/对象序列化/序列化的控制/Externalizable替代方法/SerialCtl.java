package 新IO.对象序列化.序列化的控制.Externalizable替代方法;

import java.io.*;

/**
 * Created by wulei on 16/3/17.
 * Externalizable的替代方法实例.
 *
 * 如果不是特别坚持实现Externalizable接口,那么还有另一种方法.我们可以
 * 实现Serializable接口,并添加名为writeObject()和readObject()的方法.
 * 这样一旦对象被序列化或者被反序列化还原,就会自动地分别调用这两个方法.
 * 也就是说,只要我们提供了这两个方法,就会使用它们而不是默认的序列化机制.
 */
public class SerialCtl implements Serializable{
    private String a;
    private transient String b;
    public SerialCtl(String aa,String bb){
        a="Not Transient:"+aa;
        b="Transient:"+bb;
    }
    public String toString(){return a+"\n"+b;}
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(b);
    }
    private void readObject(ObjectInputStream stream) throws IOException,ClassNotFoundException{
        stream.defaultReadObject();
        b=(String)stream.readObject();
    }
    public static void main(String[] args) throws IOException,ClassNotFoundException{
        SerialCtl sc=new SerialCtl("Test1","Test2");
        System.out.println("Before:\n"+sc);
        ByteArrayOutputStream buf=new ByteArrayOutputStream();//使用缓冲区
        ObjectOutputStream o=new ObjectOutputStream(buf);
        o.writeObject(sc);
        //恢复对象
        ObjectInputStream in=new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
        SerialCtl sc2=(SerialCtl)in.readObject();
        System.out.println("After:\n"+sc2);
    }
}
