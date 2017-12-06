package 新IO.对象序列化.序列化的控制.transient关键字;

import java.io.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by wulei on 16/3/17.
 * transient（瞬时）关键字会关闭序列化,它的意思是
 * 不用麻烦保存或恢复数据,我会自己处理的.transient关键字只能和
 * Serializable组合使用
 */
public class Logon implements Serializable {
    private Date date = new Date();
    private String username;
    private transient String password;

    public Logon(String name, String pwd) {
        username = name;
        password = pwd;
    }

    public String toString() {
        return "logon info:\n username:" + username +
                "\n date: " + date + "\n password: " + password;
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Logon a = new Logon("Hulk", "myLittlePony");
        System.out.print("logon a=" + a);
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Logon.out"));
        o.writeObject(a);
        o.close();
        TimeUnit.SECONDS.sleep(1);//延迟
        //恢复数据
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Logon.out"));
        System.out.println("Recovering object at " + new Date());
        a = (Logon) in.readObject();
        System.out.println("logon a= " + a);
    }
}/* output:
logon a=logon info:
 username:Hulk
 date: Thu Mar 17 16:30:05 CST 2016
 password: myLittlePonyRecovering object at Thu Mar 17 16:30:06 CST 2016
logon a= logon info:
 username:Hulk
 date: Thu Mar 17 16:30:05 CST 2016
 password: null
*/
