package 新IO.对象序列化.对象序列化例子;

import java.io.Serializable;

/**
 * Created by wulei on 16/3/10.
 */
public class Data implements Serializable {
    private int n;
    public Data(int n){this.n=n;}
    public String toString(){return Integer.toString(n);}
}
