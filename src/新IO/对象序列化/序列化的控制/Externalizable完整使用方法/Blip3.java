package 新IO.对象序列化.序列化的控制.Externalizable完整使用方法;

import java.io.*;

/**
 * Created by wulei on 16/3/17.
 * Externalizable完整序列化例子
 */
public class Blip3 implements Externalizable {
    private int i;
    private String s;//没有初始化
    public Blip3(){
        System.out.println("Blip3 Constructor");
        //s,i 没有初始化
    }
    public Blip3(String x,int a){
        System.out.println("Blip3(String x,int a)");
        s=x;
        i=a;
    }
    public String toString(){return s+i;}
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
       System.out.println("Blip3.writeExternal");
        //必须有以下操作来序列化变量域
        out.writeObject(s);
        out.writeInt(i);
    }
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip3.readExternal");
        //必须以下操作来读取数据
        s=(String)in.readObject();
        i=in.readInt();
    }

    public static void main(String[] args) throws IOException,ClassNotFoundException{
      System.out.println("Constructing objects:");
        Blip3 b3=new Blip3("A String ",47);
        System.out.println(b3);
        ObjectOutputStream o=new ObjectOutputStream(new FileOutputStream("Blip3.out"));
        System.out.println("Saving object:");
        o.writeObject(b3);
        o.close();
        //恢复对象:
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("Blip3.out"));
        System.out.println("Recovering b3:");
        b3=(Blip3)in.readObject();
        System.out.println(b3);
    }
}
