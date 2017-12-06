package 新IO.对象序列化.对象序列化例子;

import java.io.*;
import java.util.Random;

/**
 * Created by wulei on 16/3/10.
 * 对象序列化的实例
 */
public class Worm implements Serializable {
    private static Random rand=new Random(47);
    private Data[] d={
            new Data(rand.nextInt(10)),
            new Data(rand.nextInt(10)),
            new Data(rand.nextInt(10))
    };

    private Worm next;
    private char c;

    public Worm(int i,char x){
        System.out.println("Worm constructor:"+i);
        c=x;
        if(--i>0)
            next=new Worm(i,(char)(x+1));
    }
    public Worm(){
        System.out.println("Default constructor");
    }
    public String toString(){
        StringBuilder result=new StringBuilder(":");
        result.append(c);
        result.append("(");
        for(Data dat:d)
            result.append(dat);
        result.append(")");
        if(next!=null)
            result.append(next);
        return result.toString();
    }

    public static void main(String[] args) throws ClassNotFoundException,IOException {
        Worm w=new Worm(6,'a');
        System.out.println("w="+w);
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("worm.out"));//创建一个ObjectOutputSteam输出流来写对象
        out.writeObject("Worm storage\n");//可以写入其它对象
        out.writeObject(w);
        out.close();//包括flush操作
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("worm.out"));//创建一个ObjectInputStream输入流来读对象
        String s=(String)in.readObject();//可以读写入的其它对象
        Worm w2=(Worm)in.readObject();
        System.out.println(s+"w2="+w2);
        ByteArrayOutputStream bout=new ByteArrayOutputStream();
        ObjectOutputStream out2=new ObjectOutputStream(bout);
        out2.writeObject("Worm storage\n");
        out2.writeObject(w);
        out2.flush();
        ObjectInputStream in2=new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
        s=(String)in2.readObject();
        Worm w3=(Worm)in2.readObject();
        System.out.println(s+"w3="+w3);
    }
    /*
    Worm constructor:6
Worm constructor:5
Worm constructor:4
Worm constructor:3
Worm constructor:2
Worm constructor:1
w=:a(853):b(119):c(802):d(788):e(199):f(881)
Worm storage
w2=:a(853):b(119):c(802):d(788):e(199):f(881)
Worm storage
w3=:a(853):b(119):c(802):d(788):e(199):f(881)
     */
}
