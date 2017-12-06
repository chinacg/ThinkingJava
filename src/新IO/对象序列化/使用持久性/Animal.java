package 新IO.对象序列化.使用持久性;

import java.io.Serializable;

/**
 * Created by wulei on 16/3/21.
 */
public class Animal implements Serializable {
    private String name;
    private House preferredHouse;
    Animal(String nm,House h){
        name=nm;
        preferredHouse=h;
    }
    public String toString(){
        return name+"["+super.toString()+"], "+preferredHouse+"\n";
    }
}
